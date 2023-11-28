package com.baec23.tenadaybe.controllers

import com.baec23.tenadaybe.model.EnglishWord
import com.baec23.tenadaybe.model.PartOfSpeech
import com.baec23.tenadaybe.services.WordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/word")
class WordController(private val wordService: WordService) {
    @PostMapping("/english")
    fun saveEnglishWord(
        @RequestBody request: SaveEnglishWordRequest
    ): ResponseEntity<EnglishWord> {
        return ResponseEntity.ok(
            wordService.saveEnglishWord(
                text = request.word,
                partOfSpeech = request.pos,
                definitions = request.definitions
            )
        )
    }
}

data class SaveEnglishWordRequest(
    val word: String,
    val pos: PartOfSpeech,
    val definitions: List<String>
)