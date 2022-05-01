package com.chetan_pawar.obvious_assignment.util

import com.bumptech.glide.request.RequestOptions
import com.chetan_pawar.obvious_assignment.R


object ImageUtils {
    val requestOptions
        get() = RequestOptions()
            .error(R.drawable.default_image)

    val requestOptionsWithPlaceholder
        get() =
            requestOptions.placeholder(R.drawable.loading_animation)
}