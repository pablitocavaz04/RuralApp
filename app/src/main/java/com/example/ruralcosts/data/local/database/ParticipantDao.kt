package com.example.ruralcosts.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ParticipantDao {

    @Insert
    suspend fun create(participant: ParticipantEntity)

    @Delete
    suspend fun delete(participant: ParticipantEntity)

    @Upsert
    suspend fun update(participant: ParticipantEntity)

    @Query("SELECT * FROM participant WHERE id = :id")
    suspend fun readById(id: String): ParticipantEntity

    @Query("SELECT * FROM participant")
    suspend fun readAll(): List<ParticipantEntity>

    @Query("SELECT * FROM participant")
    suspend fun observeAll(): StateFlow<List<ParticipantEntity>>

}