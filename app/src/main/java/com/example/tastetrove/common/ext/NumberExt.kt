package com.example.tastetrove.common.ext

import java.text.NumberFormat

fun Float.toPercent() : String {
    return NumberFormat.getPercentInstance().format(this).trim()
}