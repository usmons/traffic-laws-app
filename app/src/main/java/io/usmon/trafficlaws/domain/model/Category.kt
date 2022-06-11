package io.usmon.trafficlaws.domain.model

import java.io.Serializable
import java.util.*

// Created by Usmon Abdurakhmanv on 6/11/2022.

enum class Category : Serializable {
    Warning,
    Privileged,
    Prohibition,
    Commander;

    companion object {
        fun getCapitalizedNameByPosition(position: Int): String {
            return when (position) {
                0 -> Warning.name
                1 -> Privileged.name
                2 -> Prohibition.name
                3 -> Commander.name
                else -> Warning.name
            }.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                else it.toString()
            }
        }
    }
}