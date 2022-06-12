package io.usmon.trafficlaws.core.extensions

import java.util.*

// Created by Usmon Abdurakhmanv on 6/12/2022.

/**
 * I created my own capitalize function,
 * because default capitalize function deprecated with some reasons
 */


fun String.capitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}