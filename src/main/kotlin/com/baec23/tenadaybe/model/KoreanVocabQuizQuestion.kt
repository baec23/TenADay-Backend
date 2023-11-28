package com.baec23.tenadaybe.model

data class KoreanVocabQuizQuestion(
    val baseWord: KoreanWord,
    val incorrectAnswers: List<EnglishWord>
)
