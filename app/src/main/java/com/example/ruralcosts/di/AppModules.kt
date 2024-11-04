package com.example.ruralcosts.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.ruralcosts.data.IParticipantDefaultRepository
import com.example.ruralcosts.data.ParticipantDefaultRepository
import com.example.ruralcosts.data.local.ILocalSettingsDataStore
import com.example.ruralcosts.data.local.LocalSettingsDataStore
import com.example.ruralcosts.data.local.database.AppDataBase
import com.example.ruralcosts.data.local.database.ParticipantDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideLocalSettingsDataStore(localSettingsDataStore: LocalSettingsDataStore): ILocalSettingsDataStore

    @Binds
    abstract fun provideParticipantDefaultRepository(participantDefaultRepository: ParticipantDefaultRepository): IParticipantDefaultRepository

}

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("settings") }
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context,
            AppDataBase::class.java,
            "app-db"
        ).build()
    }

    @Provides
    fun provideParticipantDao(dataBase: AppDataBase): ParticipantDao {
        return dataBase.participantDao()
    }

}