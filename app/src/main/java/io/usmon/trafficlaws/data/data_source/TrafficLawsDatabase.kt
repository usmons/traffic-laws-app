package io.usmon.trafficlaws.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.usmon.trafficlaws.core.Constants.DB_VERSION
import io.usmon.trafficlaws.data.data_source.entity.LawEntity
import io.usmon.trafficlaws.data.data_source.type_converter.TrafficLawsConverter

// Created by Usmon Abdurakhmanv on 6/6/2022.

@Database(
    entities = [LawEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(TrafficLawsConverter::class)
abstract class TrafficLawsDatabase : RoomDatabase() {

    abstract val dao: TrafficLawsDao
}