package io.usmon.trafficlaws.presentation.add_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.usmon.trafficlaws.core.Constants.ADD_EDIT
import io.usmon.trafficlaws.core.Constants.EDIT_LAW
import io.usmon.trafficlaws.coroutine.DefaultDispatchers
import io.usmon.trafficlaws.domain.model.Law
import io.usmon.trafficlaws.domain.use_case.InsertLaw
import io.usmon.trafficlaws.domain.use_case.UseCases
import io.usmon.trafficlaws.presentation.add_edit.util.AddEdit
import io.usmon.trafficlaws.presentation.add_edit.util.AddEditChannel
import io.usmon.trafficlaws.presentation.add_edit.util.AddEditEvent
import io.usmon.trafficlaws.presentation.add_edit.util.AddEditState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val useCases: UseCases,
    private val dispatchers: DefaultDispatchers,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _addEditState = MutableStateFlow(AddEditState())
    val addEditState: StateFlow<AddEditState> = _addEditState.asStateFlow()

    private val _addEditChannel = Channel<AddEditChannel>()
    val addEditChannel: Flow<AddEditChannel> = _addEditChannel.receiveAsFlow()


    init {
        savedStateHandle.get<AddEdit>(ADD_EDIT)?.let { addEdit ->
            if (addEdit is AddEdit.Add) return@let
            val law = savedStateHandle.get<Law>(EDIT_LAW)
            law?.let {
                _addEditState.value = AddEditState(
                    title = it.title,
                    desc = it.description,
                    image = it.image,
                    isLiked = it.isLiked,
                    category = it.category,
                    id = it.id
                )
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.ImageChanged -> {
                _addEditState.value = addEditState.value.copy(
                    image = event.image
                )
            }
            is AddEditEvent.TitleChanged -> {
                _addEditState.value = addEditState.value.copy(
                    title = event.title
                )
            }
            is AddEditEvent.DescriptionChanged -> {
                _addEditState.value = addEditState.value.copy(
                    desc = event.desc
                )
            }
            is AddEditEvent.CategoryChanged -> {
                _addEditState.value = addEditState.value.copy(
                    category = event.category
                )
            }
            is AddEditEvent.Save -> {
                saveLaw()
            }
        }
    }

    private fun saveLaw() {
        viewModelScope.launch(dispatchers.io) {
            val law = Law(
                title = addEditState.value.title,
                description = addEditState.value.desc,
                image = addEditState.value.image,
                isLiked = addEditState.value.isLiked,
                category = addEditState.value.category,
                id = addEditState.value.id
            )
            when (val result = useCases.insertLaw(law)) {
                is InsertLaw.Result.Error -> {
                    _addEditChannel.send(
                        AddEditChannel.ShowSnackbar(
                            message = result.message
                        )
                    )
                }
                is InsertLaw.Result.Success -> {
                    _addEditChannel.send(
                        AddEditChannel.Success
                    )
                }
            }
        }
    }


}