package com.chetan_pawar.obvious_assignment.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.chetan_pawar.obvious_assignment.R
import com.chetan_pawar.obvious_assignment.data.ImageData
import com.chetan_pawar.obvious_assignment.ui.detail.DetailActivity
import com.chetan_pawar.obvious_assignment.util.GridSpacingItemDecoration
import com.chetan_pawar.obvious_assignment.util.ImageLoader
import com.chetan_pawar.obvious_assignment.util.ImageUtils.requestOptions
import com.chetan_pawar.obvious_assignment.util.ProductionImageLoader
import com.chetan_pawar.obvious_assignment.util.TestImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImagesAdapter.Interaction {

    private lateinit var listAdapter: ImagesAdapter
    lateinit var viewModel : MainViewModel
    lateinit var imageLoader : ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        imageLoader = ProductionImageLoader(this, requestOptions = requestOptions)

        if(intent.hasExtra("value") && intent.getIntExtra("value", 0) == 1) {
            imageLoader = TestImageLoader()
        }

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
            listAdapter = ImagesAdapter(this@MainActivity, imageLoader)
            adapter = listAdapter
        }
    }

    override fun onItemSelected(position: Int, item: ImageData) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.INTENT_DETAIL_DATA, item)
        startActivity(intent)
    }
}