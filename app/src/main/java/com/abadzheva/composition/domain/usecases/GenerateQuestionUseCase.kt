package com.abadzheva.composition.domain.usecases

import com.abadzheva.composition.domain.entity.Question
import com.abadzheva.composition.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(): Question =
        repository.generateQuestion(
            maxSumValue = 100,
            countOfOptions = COUNT_OF_OPTIONS,
        )

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}
