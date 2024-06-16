package com.example.tastetrove.common.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast


inline fun <reified ClassActivity> Context.startActivityExt(onIntent: (intent: Intent) -> Unit) {
    startActivity(Intent(this, ClassActivity::class.java).apply {
        onIntent(this)
    })
}

inline fun <reified Model> Activity.getExtraExt(key: String): Model {
    return when (Model::class) {
        String::class -> intent.getStringExtra(key) as Model
        Int::class -> intent.getIntExtra(key, 0) as Model
        Boolean::class -> intent.getBooleanExtra(key, false) as Model
        Double::class -> intent.getDoubleExtra(key, 0.0) as Model
        Float::class -> intent.getFloatExtra(key, 0.0f) as Model
        Long::class -> intent.getLongExtra(key, 0L) as Model
        Model::class -> intent.getIntentParcelableExtraExt(key) ?: Model::class.java as Model
        else -> throw Exception("Type not found")
    }
}

inline fun <reified T> Intent.getIntentParcelableExtraExt(key: String): T? {
    if (hasExtra(key)) {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelableExtra(key, T::class.java)
        } else {
            @Suppress("DEPRECATION") getParcelableExtra(key)
        }
        return data
    }
    return null
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}