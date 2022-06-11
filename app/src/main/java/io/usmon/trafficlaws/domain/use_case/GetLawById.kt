package io.usmon.trafficlaws.domain.use_case

import io.usmon.trafficlaws.data.data_source.entity.LawEntity
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository

// Created by Usmon Abdurakhmanv on 6/6/2022.

class GetLawById(
    private val repository: TrafficLawsRepository
) {

    suspend operator fun invoke(id: Long): LawEntity? {
        return repository.getLawById(id)
    }
}