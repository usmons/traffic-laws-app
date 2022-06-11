package io.usmon.trafficlaws.domain.use_case

// Created by Usmon Abdurakhmanv on 6/6/2022.

data class UseCases(
    val insertLaw: InsertLaw,
    val deleteLaw: DeleteLaw,
    val getLawById: GetLawById,
    val getAllLawByCategory: GetAllLawByCategory,
    val getLikedLaws: GetLikedLaws
)
