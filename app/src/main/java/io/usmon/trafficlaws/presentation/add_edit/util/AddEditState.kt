package io.usmon.trafficlaws.presentation.add_edit.util

import android.graphics.Bitmap
import io.usmon.trafficlaws.domain.model.Category

// Created by Usmon Abdurakhmanv on 6/12/2022.

data class AddEditState(
    val image: Bitmap? = null,
    val title: String = "",
    val desc: String = "",
    val category: Category? = null,
    val isLiked: Boolean = false,
    val id: Long? = null
)
