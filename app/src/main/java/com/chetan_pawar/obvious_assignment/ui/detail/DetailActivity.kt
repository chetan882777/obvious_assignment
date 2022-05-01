package com.chetan_pawar.obvious_assignment.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chetan_pawar.obvious_assignment.R
import com.chetan_pawar.obvious_assignment.data.ImageData
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_DETAIL_DATA = "INTENT_DETAIL_DATA"
    }

    private var imageData : ImageData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageData = intent.getParcelableExtra(INTENT_DETAIL_DATA)

        if(imageData != null) {
            showDetails()
        } else showError()
    }

    private fun showDetails() {
        val requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        Glide.with(this@DetailActivity)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageData!!.hdurl)
            .into(image_image)
        supportActionBar?.title = imageData!!.title
        image_description.text = imageData!!.explanation
        image_copyright_data.text = imageData!!.copyright
        image_version_data.text = imageData!!.service_version
        image_date.text = imageData!!.date
    }

    private fun showError() {

    }
}