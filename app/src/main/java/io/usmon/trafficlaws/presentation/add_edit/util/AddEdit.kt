package io.usmon.trafficlaws.presentation.add_edit.util

import io.usmon.trafficlaws.domain.model.Law
import java.io.Serializable

// Created by Usmon Abdurakhmanv on 6/12/2022.

sealed class AddEdit : Serializable {
    object Add : AddEdit()
    data class Edit(val law: Law) : AddEdit()
}
