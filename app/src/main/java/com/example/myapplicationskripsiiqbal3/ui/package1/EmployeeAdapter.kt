package com.example.myapplicationskripsiiqbal3.ui.package1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.employee.EmployeeModel
import com.example.core.domain.model.staff.ListStaffModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentEmployeeItemBinding

class EmployeeAdapter :
    ListAdapter<ListStaffModel, EmployeeAdapter.EmployeeViewHolder>(
        MyDiffUtil()
    ) {


    lateinit var onDeleteClick: ((EmployeeModel) -> Unit)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeViewHolder {
        val binding = FragmentEmployeeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmployeeViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class EmployeeViewHolder(
        private val binding: FragmentEmployeeItemBinding,
        viewType: Int
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListStaffModel) {

            binding.tvNameEmployee.text = item.staffName
            binding.tvAddress.text = item.staffAlamat
            binding.tvContact.text = item.staffHp


        }
    }

    private class MyDiffUtil : DiffUtil.ItemCallback<ListStaffModel>() {
        override fun areItemsTheSame(
            oldItem: ListStaffModel,
            newItem: ListStaffModel
        ): Boolean {
            return oldItem.staffId == newItem.staffId

        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ListStaffModel,
            newItem: ListStaffModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}