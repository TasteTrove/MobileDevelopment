package com.example.tastetrove.data.pref

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food (
    val food: String,
    val description: String,
    val photo: Int
) : Parcelable
