package com.chetan_pawar.obvious_assignment

import android.app.Application
import android.util.Log
import com.chetan_pawar.obvious_assignment.data.DataSource
import com.chetan_pawar.obvious_assignment.data.ImageData
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

private const val TAG = "Repository"

class Repository {


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
        val list = ArrayList<ImageData>()

        val jsonArray = JSONArray(json)

        for (i in 0 until jsonArray.length()) {
            jsonArray.getJSONObject(i).run {
                list.add(
                    ImageData(
                        copyright = getString("copyright"),
                        date = getString("date"),
                        explanation = getString("explanation"),
                        hdurl = getString("hdurl"),
                        media_type = getString("media_type"),
                        service_version = getString("service_version"),
                        title = getString("title"),
                        url = getString("url")
                    )
                )
            }
        }
        DataSource.images = list
    }
}