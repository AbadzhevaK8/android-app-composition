package com.abadzheva.composition.domain.usecases

import com.abadzheva.composition.domain.entity.GameSettings
import com.abadzheva.composition.domain.entity.Level
import com.abadzheva.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(level: Level): GameSettings = repository.getGameSettings(level)
}
