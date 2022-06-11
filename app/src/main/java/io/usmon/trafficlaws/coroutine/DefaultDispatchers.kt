package io.usmon.trafficlaws.coroutine

import kotlinx.coroutines.CoroutineDispatcher

// Created by Usmon Abdurakhmanv on 6/7/2022.

interface DefaultDispatchers {

    val default: CoroutineDispatcher

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val unconfined: CoroutineDispatcher


}