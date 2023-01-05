package com.example.domain.usecases

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
enum class Type: Parcelable{
    PLUS,
    MINUS,
    MULTIPLY,
    DELETE

}