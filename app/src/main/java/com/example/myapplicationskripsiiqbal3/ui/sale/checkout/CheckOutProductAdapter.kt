package com.example.myapplicationskripsiiqbal3.ui.sale.checkout

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentCheckOutProductItemBinding
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency

class CheckOutProductAdapter :
    ListAdapter<ProductModel, CheckOutProductAdapter.CheckOutProductViewHolder>(diffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutProductViewHolder {
        val binding = FragmentCheckOutProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CheckOutProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckOutProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CheckOutProductViewHolder(private val binding: FragmentCheckOutProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ProductModel) {
            with(binding) {
                tvNameProduct.text = item.nama
                tvItemStock.text = item.stok.toString()
                tvPrice.text = FormatCurrency.formatRp(item.harga ?: 0.0)
                tvUnit.text = item.satuan
                val totalPrice = (item.harga ?: 0.0) * (item.stok ?: 0.0)
                tvTotalSubPrice.text = FormatCurrency.formatRp(totalPrice)
            }
        }
    }
}