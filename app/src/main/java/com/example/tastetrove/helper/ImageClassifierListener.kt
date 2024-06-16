package com.example.tastetrove.helper

import org.tensorflow.lite.task.vision.classifier.Classifications

interface ImageClassifierListener {
    fun onClassifierError(error: String)

    fun onClassifierResults(results: List<Classifications>?, inferenceTime: Long)
}