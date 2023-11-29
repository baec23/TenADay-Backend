package com.baec23.tenadaybe.controllers

import com.baec23.tenadaybe.model.EnglishVocabQuizQuestion
import com.baec23.tenadaybe.services.VocabQuizService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz")
class VocabQuizController(private val vocabQuizService: VocabQuizService) {
    @GetMapping("/english")
    suspend fun getRandomEnglishVocabQuizQuestion(): ResponseEntity<EnglishVocabQuizQuestion> {
        return ResponseEntity.ok(vocabQuizService.generateEnglishVocabQuizQuestion(3))
    }

}