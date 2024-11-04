package com.example.ruralcosts.data.local.database

import com.example.ruralcosts.data.Participant

interface IParticipantLocalDataStorage {

    suspend fun create(participant: Participant)

    suspend fun readAll(): List<Participant>

}