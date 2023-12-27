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
import com.example.myapplicationskripsiiqbal3.databinding.FragmentTestItemBinding

class TestAdapter :
    ListAdapter<EmployeeModel, TestAdapter.TestAdapterViewHolder>(
        MyDiffUtil()
    ) {


    lateinit var onDeleteClick: ((EmployeeModel) -> Unit)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestAdapterViewHolder {
        val binding = FragmentTestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TestAdapterViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: TestAdapterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class TestAdapterViewHolder(
        private val binding: FragmentTestItemBinding,
        viewType: Int
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmployeeModel) {

            with(binding) {
                itemView = item
            }
        }
    }

    private class MyDiffUtil : DiffUtil.ItemCallback<EmployeeModel>() {
        override fun areItemsTheSame(
            oldItem: EmployeeModel,
            newItem: EmployeeModel
        ): Boolean {
            return oldItem.empId == newItem.empId

        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: EmployeeModel,
            newItem: EmployeeModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}