package com.chetan_pawar.obvious_assignment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.chetan_pawar.obvious_assignment.R
import com.chetan_pawar.obvious_assignment.data.ImageData
import com.chetan_pawar.obvious_assignment.util.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImagesAdapter.Interaction {

    private lateinit var listAdapter: ImagesAdapter
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObservers()
        initRecyclerView()
    }

    private fun setObservers() {
        viewModel.images.observe(this, {
            listAdapter.submitList(it.getImages())
        })
    }


    private fun initRecyclerView() {
        val spanCount = 2
        val spacing = 50
        val includeEdge = true

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            listAdapter = ImagesAdapter(this@MainActivity)
            adapter = listAdapter
        }
    }

    override fun onItemSelected(position: Int, item: ImageData) {

    }
}