package com.triad.mvvmlearning.view.attraction.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.AttractionCardBinding
import com.triad.mvvmlearning.model.AttractionModelV

class AttractionAdapter(
    private val attractions: MutableList<AttractionModelV>,
    private val listener: ((AttractionModelV) -> Unit)? = null
) : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {

    inner class AttractionViewHolder(private val binding: AttractionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AttractionModelV) {
            binding.attraction = data

            // Load image from url with Glide. Also setup a placeholder and error image
            Glide.with(binding.attractionImage.context)
                .load(data.getFirstImageLink())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(binding.attractionImage)

            binding.root.setOnClickListener { listener?.invoke(data) }
        }
    }

    /// Clear the list and add all items
    @SuppressLint("NotifyDataSetChanged")
    fun addAllItems(attractions: List<AttractionModelV>) {
        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding =
            AttractionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bind(attractions[position])
    }

    override fun getItemCount() = attractions.size
}
