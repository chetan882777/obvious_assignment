package com.chetan_pawar.obvious_assignment.util

import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

private const val TAG = "TestImageLoader"
@Parcelize
class TestImageLoader : ImageLoader, Parcelable {

    var imageUrl: String? = null

    override fun loadImageUrl(url: String?, imageView: ImageView) {
        Log.d(TAG, "loadImageUrl: setting url ============= $url")
        imageUrl = url
    }

}