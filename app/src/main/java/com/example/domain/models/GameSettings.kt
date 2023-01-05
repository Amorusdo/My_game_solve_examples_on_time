package com.example.domain.models

import java.io.Serializable

data class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswer :Int,
    val minPercentOfRightAnswer : Double,
    val gameTimeInSecond: Int
        ): Serializable
