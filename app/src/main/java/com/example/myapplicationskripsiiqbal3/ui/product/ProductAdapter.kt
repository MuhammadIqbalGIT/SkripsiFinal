package com.example.myapplicationskripsiiqbal3.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentProductItemBinding
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency

class ProductAdapter : ListAdapter<ProductModel, ProductAdapter.ProductViewHolder>(diffCallback) {

    lateinit var onCardListener: ((ProductModel) -> Unit)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = FragmentProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ProductViewHolder(private val binding: FragmentProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ProductModel) {

            with(binding) {
                tvItemHargaProduk.text = FormatCurrency.formatRp(item.harga?:0.0)
                tvItemNamaProduk.text = item.nama
                tvItemStock.text = item.stok?.toInt().toString() +" "+ item.satuan

                itemView = item




                llProduk.setOnClickListener {
                    onCardListener.invoke(item)
                }
            }
        }
    }

}