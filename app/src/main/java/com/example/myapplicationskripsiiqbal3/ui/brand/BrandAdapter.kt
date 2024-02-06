package com.example.myapplicationskripsiiqbal3.ui.brand

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.brand.BrandModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentBrandItemBinding

class BrandAdapter : ListAdapter<BrandModel, BrandAdapter.BrandViewHolder>(diffCallback) {

    lateinit var onCardListener: ((BrandModel) -> Unit)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BrandModel>() {
            override fun areItemsTheSame(oldItem: BrandModel, newItem: BrandModel): Boolean {
                return oldItem.BrandID == newItem.BrandID
            }

            override fun areContentsTheSame(oldItem: BrandModel, newItem: BrandModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = FragmentBrandItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class BrandViewHolder(private val binding: FragmentBrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: BrandModel) {
            with(binding) {
                tvNameBrand.text= item.NamaBrand
            }
        }
    }

}