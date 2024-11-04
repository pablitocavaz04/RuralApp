package com.example.ruralcosts.data

import com.example.ruralcosts.data.local.database.IParticipantLocalDataStorage
import javax.inject.Inject

class ParticipantDefaultRepository @Inject constructor(
    private val participantLocalDataStorage: IParticipantLocalDataStorage
): IParticipantDefaultRepository {

    override suspend fun create(participant: Participant) {
        participantLocalDataStorage.create(participant)
    }

    override suspend fun readAll(): List<Participant> {
        return participantLocalDataStorage.readAll()
    }

}