package com.chetan_pawar.obvious_assignment.data

class DataSource {


    private var MOVIES_REMOTE_DATA = LinkedHashMap<Int, ImageData>()


    fun getImages(): List<ImageData> {
        return ArrayList(MOVIES_REMOTE_DATA.values)
    }

    fun getMovie(imageId: Int): ImageData? {
        return MOVIES_REMOTE_DATA[imageId]
    }

    fun addImage(
        imageData: ImageData
    ){
        MOVIES_REMOTE_DATA.put(imageData.id, imageData)
    }
}