package io.usmon.trafficlaws.domain.use_case

import io.usmon.trafficlaws.domain.model.Law
import io.usmon.trafficlaws.domain.model.mapper.toLawEntity
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository

// Created by Usmon Abdurakhmanv on 6/6/2022.

class DeleteLaw(
    private val repository: TrafficLawsRepository
) {

    suspend operator fun invoke(law: Law) {
        repository.deleteLaw(
            lawEntity = law.toLawEntity()
        )
    }

}