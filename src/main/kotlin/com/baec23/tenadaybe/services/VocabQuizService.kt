package com.baec23.tenadaybe.services

import com.baec23.tenadaybe.model.EnglishVocabQuizQuestion
import com.baec23.tenadaybe.repositories.EnglishWordRepository
import com.baec23.tenadaybe.repositories.KoreanWordRepository
import kotlinx.coroutines.delay
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class VocabQuizService(
    private val englishWordRepository: EnglishWordRepository,
    private val koreanWordRepository: KoreanWordRepository
) {
    suspend fun generateEnglishVocabQuizQuestion(numIncorrectAnswers: Int): EnglishVocabQuizQuestion {
        val allShuffledEnglishWords = englishWordRepository.findAll().shuffled()
        val baseWord = allShuffledEnglishWords.first()
        val baseWordDefs = baseWord.definitions
        val incorrectAnswers =
            koreanWordRepository.findAll().filter { word -> !baseWordDefs.contains(word.text) }.shuffled().take(numIncorrectAnswers)
        val randomDelay = Random.nextLong(2000)
        delay(randomDelay)
        return EnglishVocabQuizQuestion(baseWord = baseWord, incorrectAnswers = incorrectAnswers)
    }
}