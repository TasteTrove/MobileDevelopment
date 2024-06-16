package com.example.tastetrove.common.ext

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type


fun Any?.toJson() : String {
    return Gson().toJson(this)
}

inline fun <reified T> String.toModel(typeToken: Type? = null): T {
    val gson = GsonBuilder().create()
    return if (typeToken == null) {
        gson.fromJson(this, T::class.java)
    } else {
        gson.fromJson(this, typeToken)
    }
}