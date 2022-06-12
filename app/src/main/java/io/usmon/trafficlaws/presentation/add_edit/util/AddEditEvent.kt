package io.usmon.trafficlaws.presentation.add_edit.util

import android.graphics.Bitmap
import io.usmon.trafficlaws.domain.model.Category

// Created by Usmon Abdurakhmanv on 6/12/2022.

sealed class AddEditEvent {
    data class ImageChanged(val image: Bitmap) : AddEditEvent()
    data class TitleChanged(val title: String) : AddEditEvent()
    data class DescriptionChanged(val desc: String) : AddEditEvent()
    data class CategoryChanged(val category: Category?) : AddEditEvent()
    object Save : AddEditEvent()
}
