package com.example.myapplicationskripsiiqbal3.ui.sale

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentSaleProductItemBinding
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency

class SaleProductAdapter : ListAdapter<ProductModel, SaleProductAdapter.SaleProductViewHolder>(diffCallback) {


    private val checkedItems = mutableListOf<ProductModel>()
    var onItemClickListener: ((ProductModel) -> Unit)? = null

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductViewHolder {
        val binding = FragmentSaleProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SaleProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class SaleProductViewHolder(private val binding: FragmentSaleProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ProductModel) {

            with(binding) {
                tvItemHargaProduk.text = FormatCurrency.formatRp(item.harga?:0.0)
                tvItemNameProduk.text = item.nama
                tvStock.text = item.stok?.toInt().toString() +" "+ item.satuan

                itemView = item


                if (item.stok?:0.0 <= 0.0) {
                    cvProdukTidakTersedia.isVisible = true
                    tvProdukTidakTersedia.text = "Produk Tidak Tersedia silahkan isi stock terlebih dahulu"
                    cbProduct.isEnabled = false
                } else {
                    cvProdukTidakTersedia.isVisible = false
                }

                item.checkbox = cbProduct
                cbProduct.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        item.isChecked = true
                        onItemClickListener?.invoke(item)
                        updateCheckedItems()

                    } else {
                        item.isChecked= false
                        onItemClickListener?.invoke(item)
                        updateCheckedItems()
                    }

                }

            }
        }
    }
    private fun updateCheckedItems() {
        checkedItems.clear()
        currentList.let { list ->
            checkedItems.addAll(list.filter { it?.isChecked == true })
        }
    }

}