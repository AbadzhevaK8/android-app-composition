package com.abadzheva.composition.domain.repository

import com.abadzheva.composition.domain.entity.GameSettings
import com.abadzheva.composition.domain.entity.Level
import com.abadzheva.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question

    fun getGameSettings(level: Level): GameSettings
}
