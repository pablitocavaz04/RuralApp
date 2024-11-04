package com.example.ruralcosts.data.local.database

import com.example.ruralcosts.data.Participant
import com.example.ruralcosts.data.toEntity
import com.example.ruralcosts.data.toParticipant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ParticipantLocalDataStorage @Inject constructor (
    private val participantDao: ParticipantDao
): IParticipantLocalDataStorage {

    override suspend fun create(participant: Participant) {
        withContext(Dispatchers.IO) {
            participantDao.create(participant.toEntity())
        }
    }

    override suspend fun readAll(): List<Participant> {
        return withContext(Dispatchers.IO) {
            participantDao.readAll().toParticipant()
        }
    }
}