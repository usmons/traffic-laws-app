package io.usmon.trafficlaws.data.data_source.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.usmon.trafficlaws.domain.model.Category

// Created by Usmon Abdurakhmanv on 6/6/2022.

@Entity(
    tableName = "laws_table"
)
data class LawEntity(
    val title: String,
    val description: String,
    val image: Bitmap,
    val isLiked: Boolean,
    val category: Category,
    @PrimaryKey val id: Long? = null
)
