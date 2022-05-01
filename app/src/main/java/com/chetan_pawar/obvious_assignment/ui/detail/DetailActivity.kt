package com.chetan_pawar.obvious_assignment.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chetan_pawar.obvious_assignment.R
import com.chetan_pawar.obvious_assignment.data.ImageData
import com.chetan_pawar.obvious_assignment.util.ImageLoader
import com.chetan_pawar.obvious_assignment.util.ImageUtils.requestOptionsWithPlaceholder
import com.chetan_pawar.obvious_assignment.util.ProductionImageLoader
import com.chetan_pawar.obvious_assignment.util.TestImageLoader
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_DETAIL_DATA = "INTENT_DETAIL_DATA"
        const val INTENT_IMAGE_LOADER = "INTENT_IMAGE_LOADER"
    }

    private var imageData : ImageData? = null
    lateinit var imageLoader : ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageData = intent.getParcelableExtra(INTENT_DETAIL_DATA)

        imageLoader = ProductionImageLoader(this, requestOptions = requestOptionsWithPlaceholder)
        intent.getParcelableExtra<TestImageLoader>(INTENT_IMAGE_LOADER)?.let {
            imageLoader = it
        }


        if(imageData != null) {
            showDetails()
        } else showError()
    }

    private fun showDetails() {
        imageLoader.loadImageUrl(imageData!!.hdurl, image_image)
        supportActionBar?.title = imageData!!.title
        image_description.text = imageData!!.explanation
        image_copyright_data.text = imageData!!.copyright
        image_version_data.text = imageData!!.service_version
        image_date.text = imageData!!.date
    }

    private fun showError() {

    }
}