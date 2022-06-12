package io.usmon.trafficlaws.presentation.add_edit.util

import io.usmon.trafficlaws.core.UiText

// Created by Usmon Abdurakhmanv on 6/12/2022.

sealed class AddEditChannel {
    data class ShowSnackbar(val message: UiText) : AddEditChannel()
    object Success : AddEditChannel()
}
