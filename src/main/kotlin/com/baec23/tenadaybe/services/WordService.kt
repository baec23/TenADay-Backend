package com.baec23.tenadaybe.services

import com.baec23.tenadaybe.model.EnglishWord
import com.baec23.tenadaybe.model.KoreanWord
import com.baec23.tenadaybe.model.PartOfSpeech
import com.baec23.tenadaybe.repositories.EnglishWordRepository
import com.baec23.tenadaybe.repositories.KoreanWordRepository
import org.springframework.stereotype.Service

@Service
class WordService(
    private val englishWordRepository: EnglishWordRepository,
    private val koreanWordRepository: KoreanWordRepository
) {
    fun saveEnglishWord(text: String, partOfSpeech: PartOfSpeech, definitions: List<String>): EnglishWord {
        val word = englishWordRepository.findByTextAndPartOfSpeech(text, partOfSpeech)
            .orElse(
                EnglishWord(
                    text = text,
                    partOfSpeech = partOfSpeech,
                    definitions = listOf()
                )
            )
        val currDefinitions = word.definitions.toMutableList()
        definitions.forEach { definition ->
            if (!currDefinitions.contains(definition)) {
                currDefinitions.add(definition)
                saveKoreanWord(text = definition, partOfSpeech = partOfSpeech, listOf(word.text))
            }
        }
        return englishWordRepository.save(
            EnglishWord(
                text = word.text,
                partOfSpeech = partOfSpeech,
                definitions = currDefinitions.toList()
            )
        )
    }

    private fun saveKoreanWord(text: String, partOfSpeech: PartOfSpeech, definitions: List<String>) {
        val word = koreanWordRepository.findByTextAndPartOfSpeech(text, partOfSpeech)
            .orElse(
                KoreanWord(
                    text = text,
                    partOfSpeech = partOfSpeech,
                    definitions = listOf()
                )
            )
        val currDefinitions = word.definitions.toMutableList()
        definitions.forEach { definition ->
            if (!currDefinitions.contains(definition)) {
                currDefinitions.add(definition)
            }
        }
        koreanWordRepository.save(
            KoreanWord(
                text = word.text,
                partOfSpeech = partOfSpeech,
                definitions = currDefinitions.toList()
            )
        )

    }
}