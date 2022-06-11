package io.usmon.trafficlaws.domain.repository

import io.usmon.trafficlaws.data.data_source.entity.LawEntity
import kotlinx.coroutines.flow.Flow

// Created by Usmon Abdurakhmanv on 6/6/2022.

interface TrafficLawsRepository {

    suspend fun insertLaw(lawEntity: LawEntity): Long

    suspend fun deleteLaw(lawEntity: LawEntity)

    suspend fun getLawById(id: Long): LawEntity?

    fun getAllLawsByCategory(category: String): Flow<List<LawEntity>>

    fun getLikedLaws(): Flow<List<LawEntity>>

}