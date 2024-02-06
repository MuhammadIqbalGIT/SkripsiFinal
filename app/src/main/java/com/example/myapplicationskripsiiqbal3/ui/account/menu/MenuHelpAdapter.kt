package com.example.myapplicationskripsiiqbal3.ui.account.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.menu.MenuModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentMenuItemBinding

class MenuHelpAdapter : ListAdapter<MenuModel, MenuHelpAdapter.MenuHelpViewHolder>(diffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHelpViewHolder {
        val binding = FragmentMenuItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuHelpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHelpViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MenuHelpViewHolder(private val binding: FragmentMenuItemBinding) :
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