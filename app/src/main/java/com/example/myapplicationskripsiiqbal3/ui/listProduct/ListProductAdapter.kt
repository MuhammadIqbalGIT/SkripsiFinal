package com.example.myapplicationskripsiiqbal3.ui.listProduct

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentListProductItemBinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentProductItemBinding
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency

class ListProductAdapter : ListAdapter<ProductModel, ListProductAdapter.ListProductViewHolder>(diffCallback) {

    lateinit var onButtonDetailClick: ((ProductModel) -> Unit)
    lateinit var onIconDeleteClick :((ProductModel)-> Unit)
    lateinit var onButtonChangeClick : ((ProductModel)-> Unit)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val binding = FragmentListProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ListProductViewHolder(private val binding: FragmentListProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ProductModel) {

            with(binding) {
                tvItemHargaProduk.text = FormatCurrency.formatRp(item.harga)
                tvItemNameProduk.text = item.nama
                tvStock.text = item.stok.toInt().toString() +" "+ item.satuan

                itemView = item


                btnToDetailProduct.setOnClickListener {
                    onButtonDetailClick.invoke(item)
                }
                ivDeleteProduct.setOnClickListener {
                    onIconDeleteClick.invoke(item)
                }

                btnUbah.setOnClickListener {
                    onButtonChangeClick.invoke(item)
                }
            }
        }
    }

}