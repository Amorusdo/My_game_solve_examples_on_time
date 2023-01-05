package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResult(
    val isWinner:Boolean,
    val countOfRightAnswer: Int,
    val percentOfRightAnswer:Int,
    val countOfQuestion:Int,
    val gameSettings: GameSettings,
): Parcelable