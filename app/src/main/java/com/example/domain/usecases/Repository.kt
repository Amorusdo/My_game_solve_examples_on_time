package com.example.domain.usecases

import com.example.domain.models.*

interface Repository {
    fun generateQuestion(maxSumValue:Int, countOfOption: Int, type: Type): Question
    fun getGameSettings(level: Level): GameSettings
}