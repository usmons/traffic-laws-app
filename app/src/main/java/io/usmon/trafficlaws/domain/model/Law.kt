package io.usmon.trafficlaws.domain.model

import android.graphics.Bitmap
import java.io.Serializable

// Created by Usmon Abdurakhmanv on 6/6/2022.

data class Law(
    val title: String,
    val description: String,
    val image: Bitmap?,
    val isLiked: Boolean,
    val category: Category?,
    val id: Long? = null
) : Serializable