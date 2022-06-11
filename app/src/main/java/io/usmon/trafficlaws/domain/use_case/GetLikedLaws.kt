package io.usmon.trafficlaws.domain.use_case

import io.usmon.trafficlaws.domain.model.Law
import io.usmon.trafficlaws.domain.model.mapper.toLaw
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Created by Usmon Abdurakhmanv on 6/7/2022.

class GetLikedLaws(
    private val repository: TrafficLawsRepository
) {

    operator fun invoke(): Flow<List<Law>> {
        return repository
            .getLikedLaws()
            .map { list ->
                list.map { lawEntity ->
                    lawEntity.toLaw()
                }
            }
    }
}