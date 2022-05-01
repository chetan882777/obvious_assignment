package com.chetan_pawar.obvious_assignment.ui.detail

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
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

        // View name of the header image. Used for activity scene transitions
        const val VIEW_NAME_HEADER_IMAGE = "VIEW_NAME_HEADER_IMAGE"

        // View name of the header title. Used for activity scene transitions
        const val VIEW_NAME_HEADER_DESC = "VIEW_NAME_HEADER_DESC"
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


        ViewCompat.setTransitionName(image_image, VIEW_NAME_HEADER_IMAGE)
        ViewCompat.setTransitionName(image_description, VIEW_NAME_HEADER_DESC)

        if(imageData != null) {
            showDetails()
        } else showError()
    }

    private fun showDetails() {
        imageLoader.loadImageUrl(imageData!!.hdurl, image_image)
        toolbar.title = imageData!!.title
        image_description.text = imageData!!.explanation
        image_copyright_data.text = imageData!!.copyright
        image_version_data.text = imageData!!.service_version
        image_date.text = imageData!!.date
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

    }

    private fun showError() {

    }
}