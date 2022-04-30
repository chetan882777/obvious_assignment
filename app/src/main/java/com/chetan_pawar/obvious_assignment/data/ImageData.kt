package com.chetan_pawar.obvious_assignment.data

data class ImageData(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
) {

    override fun toString(): String {
        return "$copyright, $date, $explanation, $hdurl, $media_type, $service_version, $title, $url"
    }
}