package com.baec23.tenadaybe.model

data class EnglishVocabQuizQuestion(
    val baseWord: EnglishWord,
    val incorrectAnswers: List<KoreanWord>
)
