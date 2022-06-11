package io.usmon.trafficlaws.domain.use_case

import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.domain.model.Law
import io.usmon.trafficlaws.domain.model.mapper.toLaw
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Created by Usmon Abdurakhmanv on 6/6/2022.

class GetAllLawByCategory(
    private val repository: TrafficLawsRepository
) {
    operator fun invoke(category: Category): Flow<List<Law>> {
        return repository
            .getAllLawsByCategory(category = category.name)
            .map { list ->
                list.map { law ->
                    law.toLaw()
                }
            }
    }
}