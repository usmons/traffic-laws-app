package io.usmon.trafficlaws.core

import android.content.Context

// Created by Usmon Abdurakhmanv on 6/6/2022.

sealed class UiText {
    data class DynamicString(val text: String) : UiText()
    data class StringResource(val resId: Int) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> text
            is StringResource -> context.getString(resId)
        }
    }
}