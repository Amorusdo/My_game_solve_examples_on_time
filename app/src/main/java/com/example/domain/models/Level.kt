package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Level : Parcelable {
    TEST,
    EASY,
    STANDARD,
    HARD
}