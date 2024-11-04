package com.example.ruralcosts.data.local.database

import androidx.room.Entity

@Entity(tableName = "participant")
data class ParticipantEntity(
    val id: String,
    val name: String,
    val surname1: String,
    val surname2: String?
)
