package com.chetan_pawar.obvious_assignment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chetan_pawar.obvious_assignment.data.DataSource
import com.chetan_pawar.obvious_assignment.data.ImageData
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset

private const val TAG = "Repository"

class Repository {

    val imagesLiveData : MutableLiveData<DataSource> = MutableLiveData()

    fun loadJson(application: Application) {
        var json = ""
        try {
            val open = application.assets.open(Constants.DATA_FILE)
            val size: Int = open.available()
            val buffer = ByteArray(size)
            open.read(buffer)
            open.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {

        }
        Log.d(TAG, "loadJson: json $json")
        loadDataSource(json)
    }

    fun loadDataSource(json: String) {
        val dataSource = DataSource()

        val jsonArray = JSONArray(json)

        for (i in 0 until jsonArray.length()) {
            jsonArray.getJSONObject(i).run {
                dataSource.addImage(
                    ImageData(
                        id = i,
                        copyright = if(has("copyright")) getString("copyright") else null,
                        date = if(has("date")) getString("date") else null,
                        explanation = if(has("explanation")) getString("explanation") else null,
                        hdurl = if(has("hdurl")) getString("hdurl") else null,
                        media_type = if(has("media_type")) getString("media_type") else null,
                        service_version = if(has("service_version")) getString("service_version") else null,
                        title = if(has("title")) getString("title") else null,
                        url = if(has("url")) getString("url") else null
                    )
                )
            }
        }
        imagesLiveData.value = dataSource
    }

    fun getAllImages() : LiveData<DataSource> {
        return imagesLiveData
    }

}