package com.baec23.tenadaybe.model

import jakarta.persistence.*

@Entity
class EnglishWord(
    @Id
    val text: String,
    val partOfSpeech: PartOfSpeech,
    @ElementCollection
    val definitions: List<String>
)