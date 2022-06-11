package io.usmon.trafficlaws.domain.use_case

import io.usmon.trafficlaws.R
import io.usmon.trafficlaws.core.UiText
import io.usmon.trafficlaws.domain.model.Law
import io.usmon.trafficlaws.domain.model.mapper.toLawEntity
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository

// Created by Usmon Abdurakhmanv on 6/6/2022.

class InsertLaw(
    private val repository: TrafficLawsRepository
) {

    suspend operator fun invoke(law: Law): Result {

        if (law.title.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.title_could_not_be_empty)
            )
        }

        if (law.description.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.desc_could_not_be_empty)
            )
        }


        if (law.category == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.category_has_not_chosen_yet)
            )
        }

        if (law.image == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.image_has_not_chosen_yet)
            )
        }

        return Result.Success(
            id = repository.insertLaw(
                lawEntity = law.toLawEntity()
            )
        )
    }

    sealed class Result {
        data class Success(val id: Long? = null) : Result()
        data class Error(val message: UiText) : Result()
    }
}