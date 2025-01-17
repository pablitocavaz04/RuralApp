package com.example.ruralcosts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ParticipantEntity::class,

               ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun participantDao(): ParticipantDao
}