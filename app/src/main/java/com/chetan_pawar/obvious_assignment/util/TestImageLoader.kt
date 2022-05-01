package com.chetan_pawar.obvious_assignment.util

import android.widget.ImageView

class TestImageLoader : ImageLoader {

    var imageUrl: String? = null

    override fun loadImageUrl(url: String?, imageView: ImageView) {
        imageUrl = url
    }

}