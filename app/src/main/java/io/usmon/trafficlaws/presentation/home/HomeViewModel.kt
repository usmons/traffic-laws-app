package io.usmon.trafficlaws.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.usmon.trafficlaws.coroutine.DefaultDispatchers
import io.usmon.trafficlaws.presentation.home.util.HomeChannel
import io.usmon.trafficlaws.presentation.home.util.HomeEvent
import io.usmon.trafficlaws.presentation.home.util.HomeState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchers: DefaultDispatchers
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private val _homeChannel = Channel<HomeChannel>()
    val homeChannel: Flow<HomeChannel> = _homeChannel.receiveAsFlow()


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.AddLaw -> {
                viewModelScope.launch(dispatchers.default) {
                    _homeChannel.send(HomeChannel.AddLaw)
                }
            }
            is HomeEvent.PagerChanged -> {
                _homeState.value = homeState.value.copy(
                    pagerPosition = event.position
                )
            }
        }
    }
}