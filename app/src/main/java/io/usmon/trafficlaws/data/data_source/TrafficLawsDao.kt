package io.usmon.trafficlaws.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.usmon.trafficlaws.data.data_source.entity.LawEntity
import kotlinx.coroutines.flow.Flow

// Created by Usmon Abdurakhmanv on 6/6/2022.

@Dao
interface TrafficLawsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertLaw(roadSign: LawEntity): Long

    @Delete
    suspend fun deleteLaw(roadSign: LawEntity)

    @Query("SELECT * FROM laws_table WHERE id = :id")
    suspend fun getLawById(id: Long): LawEntity?

    @Query("SELECT * FROM laws_table WHERE category = :category")
    fun getAllLawsByCategory(category: String): Flow<List<LawEntity>>


    @Query("SELECT * FROM laws_table WHERE isLiked = 1")
    fun getLikedLaws(): Flow<List<LawEntity>>

}