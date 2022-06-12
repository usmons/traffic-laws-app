package io.usmon.trafficlaws.presentation.home_pager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.usmon.trafficlaws.R
import io.usmon.trafficlaws.core.Constants.CATEGORY_STATE
import io.usmon.trafficlaws.core.UiText
import io.usmon.trafficlaws.coroutine.DefaultDispatchers
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.domain.model.Law
import io.usmon.trafficlaws.domain.use_case.UseCases
import io.usmon.trafficlaws.presentation.home_pager.util.HomePagerChannel
import io.usmon.trafficlaws.presentation.home_pager.util.HomePagerEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePagerViewModel @Inject constructor(
    private val useCases: UseCases,
    private val dispatchers: DefaultDispatchers,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _homePagerState = MutableStateFlow<List<Law>>(emptyList())
    val homePagerState: StateFlow<List<Law>> = _homePagerState.asStateFlow()

    private val _homePagerChannel = Channel<HomePagerChannel>()
    val homePagerChannel: Flow<HomePagerChannel> = _homePagerChannel.receiveAsFlow()


    private var deletedLaw: Law? = null

    init {
        val category = savedStateHandle.get<Category>(CATEGORY_STATE)

        category?.let {
            useCases.getAllLawByCategory(
                category = it
            ).onEach { laws ->
                _homePagerState.value = laws
            }.launchIn(viewModelScope)
        }
    }


    fun onEvent(event: HomePagerEvent) {
        when (event) {
            is HomePagerEvent.LawClicked -> {
                viewModelScope.launch(dispatchers.default) {
                    _homePagerChannel.send(
                        HomePagerChannel.Details(
                            law = event.law
                        )
                    )
                }
            }
            is HomePagerEvent.LikeLawClicked -> {
                viewModelScope.launch(dispatchers.io) {
                    val likedLaw = event.law.copy(isLiked = !event.law.isLiked)
                    useCases.insertLaw(likedLaw)
                }
            }
            is HomePagerEvent.EditLawClicked -> {
                viewModelScope.launch(dispatchers.default) {
                    _homePagerChannel.send(
                        HomePagerChannel.Edit(
                            law = event.law
                        )
                    )
                }
            }
            is HomePagerEvent.DeleteLawClicked -> {
                viewModelScope.launch(dispatchers.io) {
                    useCases.deleteLaw(event.law)
                    deletedLaw = event.law
                    _homePagerChannel.send(
                        HomePagerChannel.ShowSnackbar(
                            message = UiText.StringResource(
                                resId = R.string.law_deleted
                            )
                        )
                    )
                }
            }
            is HomePagerEvent.UndoClicked -> {
                viewModelScope.launch(dispatchers.io) {
                    deletedLaw?.let { law ->
                        useCases.insertLaw(law)
                        deletedLaw = null
                    }
                }
            }
        }
    }


}