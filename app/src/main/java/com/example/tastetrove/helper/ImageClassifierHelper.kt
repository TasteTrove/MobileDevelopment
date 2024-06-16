package com.example.tastetrove.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import com.example.tastetrove.R
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.CastOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class ImageClassifierHelper(private val listener: ImageClassifierListener?) {

    companion object {
        private const val TAG = "ImageClassifierHelper"
        const val THRESHOLD = 0.1f
        const val MAX_RESULTS = 3
        const val MODEL_NAME = "model.tflite"
    }

    private var imageClassifier: ImageClassifier? = null
    private lateinit var tensorImage: TensorImage

    private fun setupImageClassifier(context: Context) {
        // TODO: Menyiapkan Image Classifier untuk memproses gambar.
        val baseOptionsBuilder = BaseOptions.builder().setNumThreads(4)

        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(THRESHOLD)
            .setMaxResults(MAX_RESULTS)
            .setBaseOptions(baseOptionsBuilder.build())

        try {
            imageClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                MODEL_NAME,
                optionsBuilder.build()
            )
        } catch (e: IllegalStateException) {
            listener?.onClassifierError(context.getString(R.string.classifier_failed))
            Log.e(TAG, e.message.toString())
        }
    }


    fun classifyStaticImage(context: Context, imageUri: Uri) {
        // TODO: mengklasifikasikan imageUri dari gambar statis.

        if (imageClassifier == null) {
            setupImageClassifier(context)
        }

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(CastOp(DataType.UINT8))
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        }.copy(Bitmap.Config.ARGB_8888, true)?.let { bitmap ->
            tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))
        }

        val imageProcessingOptions = ImageProcessingOptions.builder().build()

        var inferenceTime = SystemClock.uptimeMillis()
        val results = imageClassifier?.classify(tensorImage, imageProcessingOptions)
        inferenceTime = SystemClock.uptimeMillis() - inferenceTime
        listener?.onClassifierResults(results, inferenceTime)
    }

}