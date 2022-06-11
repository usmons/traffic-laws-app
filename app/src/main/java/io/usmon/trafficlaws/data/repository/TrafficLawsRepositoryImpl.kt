package io.usmon.trafficlaws.data.repository

import io.usmon.trafficlaws.data.data_source.TrafficLawsDao
import io.usmon.trafficlaws.data.data_source.entity.LawEntity
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository
import kotlinx.coroutines.flow.Flow

// Created by Usmon Abdurakhmanv on 6/6/2022.

class TrafficLawsRepositoryImpl(
    private val dao: TrafficLawsDao
) : TrafficLawsRepository {

    override suspend fun insertLaw(lawEntity: LawEntity): Long {
        return dao.insertLaw(lawEntity)
    }

    override suspend fun deleteLaw(lawEntity: LawEntity) {
        dao.deleteLaw(lawEntity)
    }

    override suspend fun getLawById(id: Long): LawEntity? {
        return dao.getLawById(id)
    }

    override fun getAllLawsByCategory(category: String): Flow<List<LawEntity>> {
        return dao.getAllLawsByCategory(category)
    }

    override fun getLikedLaws(): Flow<List<LawEntity>> {
        return dao.getLikedLaws()
    }
}