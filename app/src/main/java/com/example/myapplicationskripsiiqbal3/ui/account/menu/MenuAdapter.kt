package com.example.myapplicationskripsiiqbal3.ui.account.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.menu.MenuModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentMenuItemBinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentProductItemBinding
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency

class MenuAdapter : ListAdapter<MenuModel, MenuAdapter.MenuViewHolder>(diffCallback) {

    lateinit var onCardListener: ((MenuModel) -> Unit)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MenuModel>() {
            override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
                return oldItem.menuId == newItem.menuId
            }

            override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = FragmentMenuItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MenuViewHolder(private val binding: FragmentMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuModel) {

            with(binding) {
                itemView = item

                clMenu.setOnClickListener {
                    onCardListener.invoke(item)
                }
            }
        }
    }
}