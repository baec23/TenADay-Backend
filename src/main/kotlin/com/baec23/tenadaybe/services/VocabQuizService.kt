package com.baec23.tenadaybe.services

import com.baec23.tenadaybe.model.EnglishVocabQuizQuestion
import com.baec23.tenadaybe.repositories.EnglishWordRepository
import com.baec23.tenadaybe.repositories.KoreanWordRepository
import org.springframework.stereotype.Service

@Service
class VocabQuizService(
    private val englishWordRepository: EnglishWordRepository,
    private val koreanWordRepository: KoreanWordRepository
) {
    fun generateEnglishVocabQuizQuestion(numIncorrectAnswers: Int): EnglishVocabQuizQuestion {
        val allShuffledEnglishWords = englishWordRepository.findAll().shuffled()
        val baseWord = allShuffledEnglishWords.first()
        val baseWordDefs = baseWord.definitions
        val incorrectAnswers =
            koreanWordRepository.findAll().filter { word -> !baseWordDefs.contains(word.text) }.shuffled().take(numIncorrectAnswers)
        return EnglishVocabQuizQuestion(baseWord = baseWord, incorrectAnswers = incorrectAnswers)
    }
}