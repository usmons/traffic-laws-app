package io.usmon.trafficlaws.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.usmon.trafficlaws.core.Constants.DB_NAME
import io.usmon.trafficlaws.coroutine.DefaultDispatchers
import io.usmon.trafficlaws.coroutine.DefaultDispatchersImpl
import io.usmon.trafficlaws.data.data_source.TrafficLawsDatabase
import io.usmon.trafficlaws.data.repository.TrafficLawsRepositoryImpl
import io.usmon.trafficlaws.domain.repository.TrafficLawsRepository
import io.usmon.trafficlaws.domain.use_case.*
import javax.inject.Singleton

// Created by Usmon Abdurakhmanv on 6/7/2022.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): TrafficLawsDatabase {
        return Room.databaseBuilder(
            app,
            TrafficLawsDatabase::class.java,
            DB_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideRepository(database: TrafficLawsDatabase): TrafficLawsRepository {
        return TrafficLawsRepositoryImpl(dao = database.dao)
    }

    @Provides
    @Singleton
    fun provideDefaultDispatchers(): DefaultDispatchers {
        return DefaultDispatchersImpl()
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: TrafficLawsRepository): UseCases {
        return UseCases(
            insertLaw = InsertLaw(repository),
            deleteLaw = DeleteLaw(repository),
            getLawById = GetLawById(repository),
            getAllLawByCategory = GetAllLawByCategory(repository),
            getLikedLaws = GetLikedLaws(repository),
        )
    }

}