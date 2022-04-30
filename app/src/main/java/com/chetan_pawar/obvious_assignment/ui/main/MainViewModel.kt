package com.chetan_pawar.obvious_assignment.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.chetan_pawar.obvious_assignment.Repository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository()

    init {
        repository.loadJson(application)
    }

    val images get() = repository.getAllImages()
}