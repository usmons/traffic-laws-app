package io.usmon.trafficlaws.domain.model

import io.usmon.trafficlaws.core.extensions.capitalize
import java.io.Serializable

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
            }.capitalize()
        }

        fun getPosition(category: Category): Int {
            return when (category) {
                Warning -> 0
                Privileged -> 1
                Prohibition -> 2
                else -> 3
            }
        }

    }
}