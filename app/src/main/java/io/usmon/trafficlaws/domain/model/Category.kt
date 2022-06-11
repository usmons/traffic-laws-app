package io.usmon.trafficlaws.domain.model

import java.io.Serializable

// Created by Usmon Abdurakhmanv on 6/11/2022.

enum class Category : Serializable {
    Warning,
    Privileged,
    Prohibition,
    Commander
}