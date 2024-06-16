package com.example.tastetrove.common.ext

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageExt(uri: Any?, placeHolder: Int? = null) {
    if (uri != null) {
        when (uri) {
            is String,
            is Int,
            is ByteArray,
            is Uri,
            -> {
                if (placeHolder != null) {
                    Glide.with(context)
                        .load(uri)
                        .placeholder(placeHolder)
                        .into(this)
                } else {
                    Glide.with(context)
                        .load(uri)
                        .into(this)
                }
            }
        }
    } else {
        if (placeHolder != null) {
            Glide.with(context)
                .load("")
                .placeholder(placeHolder)
                .into(this)
        } else {
            Glide.with(context)
                .load("")
                .into(this)
        }
    }
}