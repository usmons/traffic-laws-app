package io.usmon.trafficlaws.presentation.home.util

// Created by Usmon Abdurakhmanv on 6/11/2022.

sealed class HomeEvent {
    object AddLaw : HomeEvent()
    data class PagerChanged(val position: Int) : HomeEvent()
}
