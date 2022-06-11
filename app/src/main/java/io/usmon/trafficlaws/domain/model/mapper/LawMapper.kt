package io.usmon.trafficlaws.domain.model.mapper

import io.usmon.trafficlaws.data.data_source.entity.LawEntity
import io.usmon.trafficlaws.domain.model.Law

// Created by Usmon Abdurakhmanv on 6/6/2022.

fun Law.toLawEntity(): LawEntity {
    return LawEntity(
        title = title,
        description = description,
        image = image!!,
        isLiked = isLiked,
        category = category!!,
        id = id
    )
}

fun LawEntity.toLaw(): Law {
    return Law(
        title = title,
        description = description,
        image = image,
        isLiked = isLiked,
        category = category,
        id = id
    )
}