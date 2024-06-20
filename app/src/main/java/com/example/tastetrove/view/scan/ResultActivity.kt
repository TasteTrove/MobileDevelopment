package com.example.tastetrove.view.scan

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.tastetrove.common.base.BaseActivity
import com.example.tastetrove.common.ext.getExtraExt
import com.example.tastetrove.common.ext.gone
import com.example.tastetrove.common.ext.setImageExt
import com.example.tastetrove.common.ext.showToast
import com.example.tastetrove.common.ext.toPercent
import com.example.tastetrove.common.ext.visible
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.model.api.ml.MLFoodResponse
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.databinding.ActivityScanResultBinding
import com.example.tastetrove.helper.ImageClassifierListener
import com.example.tastetrove.util.reduceFileImage
import com.example.tastetrove.util.uriToFile
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.task.vision.classifier.Classifications

@AndroidEntryPoint
class ResultActivity : BaseActivity<ActivityScanResultBinding>(), ImageClassifierListener {

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_DETAIL = "extra_detail"
    }

    private val viewModel: ResultViewModel by viewModels()

    private val imageUri: Uri by lazy {
        Uri.parse(getExtraExt<String>(EXTRA_IMAGE_URI))
    }

    private val mlFoodResponse: MLFoodResponse by lazy {
        getExtraExt<MLFoodResponse>(EXTRA_DETAIL)
    }

    private var label = ""
    private var score = ""

    private var dataScan = HistoryModel()
    private var hasilApi = MLFoodResponse()

    override fun setupViewBinding(): ActivityScanResultBinding {
        return ActivityScanResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.

        if (intent.hasExtra(EXTRA_DETAIL)){
            binding.titleFood.text = mlFoodResponse.nama
            binding.detailFood.text = mlFoodResponse.deskripsi
            binding.imageFood.setImageExt(mlFoodResponse.image)
            binding.lokasiFood.text = mlFoodResponse.lokasi
            hasilApi=mlFoodResponse
        } else {
            classify()
        }

        binding.apply {
            favoriteIcon.setOnClickListener {
                viewModel.insertFav(hasilApi.toFavoriteModel())
            }
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.dbState.observe(this) {
            when (it) {
                is Resource.Error -> {
                    // showToast("Error Menyimpan History")
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    // showToast("Success Menyimpan History")
                }
            }
        }

        viewModel.dbFavState.observe(this) {
            when (it) {
                is Resource.Error -> {
                    showToast("Error Menyimpan Favorite")

                    Log.d("nama",it.message.toString())
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    showToast("Success Menyimpan Favorite")
                    binding.favoriteIcon.hide()
                }
            }
        }

        viewModel.analyzeState.observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.loading.gone()
                    showToast(it.message.toString())
                    Log.d("Error Upload File", it.message.toString())
                }

                is Resource.Loading -> {
                    binding.loading.visible()
                }
                is Resource.Success -> {
                    binding.loading.gone()
                    it.data?.let { food ->
                        binding.titleFood.text = food.nama
                        binding.detailFood.text = food.deskripsi
                        hasilApi.nama = food.nama
                        hasilApi.lokasi = food.lokasi
                        hasilApi.deskripsi = food.deskripsi
                        dataScan.nama = food.nama
                        dataScan.lokasi = food.lokasi
                        dataScan.deskripsi = food.lokasi
                        viewModel.insertData(dataScan)
                    }
                }
            }
        }

    }

    private fun classify() {
        imageUri.let {
            Log.d("Image URI", "showImage: $it")
            binding.imageFood.setImageURI(it)
            hasilApi.image = it.toString()
            dataScan.image = it.toString()

            val imageFile = uriToFile(it, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")

            viewModel.analyze(imageFile)
            // ImageClassifierHelper(this).classifyStaticImage(this, imageUri)
        }
    }


    override fun onClassifierError(error: String) {
        showToast(error)
    }

    override fun onClassifierResults(results: List<Classifications>?, inferenceTime: Long) {
        results?.let { it ->
            var resultText = ""
            if (it.isNotEmpty() && it[0].categories.isNotEmpty()) {
                val highestScoreCategory = it[0].categories.maxByOrNull { it.score }
                if (highestScoreCategory != null) {
                    resultText =
                        "${highestScoreCategory.label} " + highestScoreCategory.score.toPercent()
                    label = highestScoreCategory.label
                    score = highestScoreCategory.score.toPercent()
                } else {
                    resultText = "No category with high enough score found."
                }
            }
            binding.titleFood.text = resultText

        }
    }

}