package io.usmon.trafficlaws.data.data_source.type_converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import io.usmon.trafficlaws.domain.model.Category
import java.io.ByteArrayOutputStream

// Created by Usmon Abdurakhmanv on 6/6/2022.

class TrafficLawsConverter {

    //Bitmap converters

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    //Category converters

    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        return when (category) {
            Category.Warning.name -> Category.Warning
            Category.Privileged.name -> Category.Privileged
            Category.Prohibition.name -> Category.Prohibition
            Category.Commander.name -> Category.Commander
            else -> Category.Warning
        }
    }
}