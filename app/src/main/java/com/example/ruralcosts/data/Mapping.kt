package com.example.ruralcosts.data

import com.example.ruralcosts.data.local.database.ParticipantEntity

fun ParticipantEntity.toParticipant(): Participant {
    return Participant(
        id,
        name,
        surname1,
        surname2
    )
}

fun List<ParticipantEntity>.toParticipant(): List<Participant> = map(ParticipantEntity::toParticipant)

fun Participant.toEntity(): ParticipantEntity {
    return ParticipantEntity(
        id,
        name,
        surname1,
        surname2
    )
}

fun List<Participant>.toEntity(): List<ParticipantEntity> = map(Participant::toEntity)