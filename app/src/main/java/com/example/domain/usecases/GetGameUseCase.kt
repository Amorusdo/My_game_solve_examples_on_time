package com.example.domain.usecases

import com.example.domain.models.GameSettings
import com.example.domain.models.Level

class GetGameUseCase(private val repository: Repository) {
    fun execute(level: Level): GameSettings = repository.getGameSettings(level)

}