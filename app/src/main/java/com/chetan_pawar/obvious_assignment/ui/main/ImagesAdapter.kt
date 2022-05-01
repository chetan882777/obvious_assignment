package com.chetan_pawar.obvious_assignment.ui.main

import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chetan_pawar.obvious_assignment.R
import com.chetan_pawar.obvious_assignment.data.ImageData
import com.chetan_pawar.obvious_assignment.util.ImageLoader
import kotlinx.android.synthetic.main.layout_image_list_item.view.*

class ImagesAdapter(private val interaction: Interaction? = null, private val imageLoader: ImageLoader) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageData>() {

        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_image_list_item,
                parent,
                false
            ),
            interaction,
            imageLoader
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ImageData>) {
        differ.submitList(list)
    }

    class ImageViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ImageData) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            image_title.text = item.title

            imageLoader.loadImageUrl(item.url, image_container)


            val animationDrawable = image_container.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(400)
            animationDrawable.setExitFadeDuration(400)
            animationDrawable.start()

            image_discription.text = item.copyright

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ImageData)
    }
}
