package com.baec23.tenadaybe.repositories

import com.baec23.tenadaybe.model.KoreanWord
import com.baec23.tenadaybe.model.PartOfSpeech
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface KoreanWordRepository : JpaRepository<KoreanWord, String> {
    fun findByTextAndPartOfSpeech(text: String, partOfSpeech: PartOfSpeech): Optional<KoreanWord>

}