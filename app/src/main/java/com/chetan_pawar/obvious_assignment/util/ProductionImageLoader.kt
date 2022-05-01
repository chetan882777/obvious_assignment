package com.chetan_pawar.obvious_assignment.util

import android.content.Context
import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.chetan_pawar.obvious_assignment.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class ProductionImageLoader(
    context:@RawValue Context,

    val requestOptions: @RawValue RequestOptions
) : ImageLoader, Parcelable {

    private val requestManager: RequestManager = Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)

    override fun loadImageUrl(url: String?, imageView: ImageView) {
        if(url != null) {
            loadImage(url, imageView)
        } else {
            showErrorImage(imageView)
        }
    }

    private fun loadImage(resource: Any, imageView: ImageView){
        requestManager
            .load(resource)
            .into(imageView)
    }

    private fun showErrorImage(imageView: ImageView){
        requestManager
            .load(R.drawable.default_image)
            .into(imageView)
    }
}













