package com.example.myapplicationskripsiiqbal3.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentProductItemBinding

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
        fun bind(item: ProductModel) {

            with(binding) {
                tvItemHargaProduk.text = item.harga.toInt().toString()
                tvItemNamaProduk.text = item.nama
                tvItemStock.text = item.stok.toInt().toString()
                tvNameBrand.text = item.namaBrand

                llProduk.setOnClickListener {
                    onCardListener.invoke(item)
                }
            }
        }
    }

}