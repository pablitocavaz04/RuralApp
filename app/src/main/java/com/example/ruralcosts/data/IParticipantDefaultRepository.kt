package com.example.ruralcosts.data

interface IParticipantDefaultRepository {

    suspend fun create(participant: Participant)

    suspend fun readAll(): List<Participant>

}