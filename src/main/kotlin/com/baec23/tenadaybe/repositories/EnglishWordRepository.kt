package com.baec23.tenadaybe.repositories

import com.baec23.tenadaybe.model.EnglishWord
import com.baec23.tenadaybe.model.PartOfSpeech
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EnglishWordRepository : JpaRepository<EnglishWord, String> {
    fun findByTextAndPartOfSpeech(text: String, partOfSpeech: PartOfSpeech): Optional<EnglishWord>

}