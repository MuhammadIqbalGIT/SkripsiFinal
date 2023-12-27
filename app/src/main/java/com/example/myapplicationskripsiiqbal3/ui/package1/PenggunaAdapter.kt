package com.example.myapplicationskripsiiqbal3.ui.package1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.PenggunaModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentEmployeeItemBinding

class PenggunaAdapter : ListAdapter<PenggunaModel, PenggunaAdapter.PenggunaViewHolder>(diffCallback) {

    lateinit var onDeleteClick: ((PenggunaModel) -> Unit)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PenggunaModel>() {
            override fun areItemsTheSame(oldItem: PenggunaModel, newItem: PenggunaModel): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(oldItem: PenggunaModel, newItem: PenggunaModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenggunaViewHolder {
        val binding = FragmentEmployeeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PenggunaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PenggunaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PenggunaViewHolder(private val binding: FragmentEmployeeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PenggunaModel) {
            binding.tvNameEmployee.text = item.nama
            binding.tvAddress.text = item.level
            binding.tvContact.text = item.password
        }
    }

}

