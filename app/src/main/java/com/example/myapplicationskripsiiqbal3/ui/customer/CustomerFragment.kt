package com.example.myapplicationskripsiiqbal3.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.databinding.FragmentCustomerBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment


class CustomerFragment : BaseFragment<FragmentCustomerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCustomerBinding
        get() = FragmentCustomerBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }


    override fun FragmentCustomerBinding.initUI() {

    }

    override fun FragmentCustomerBinding.initEvent() {

    }

    override fun FragmentCustomerBinding.initObserve() {

    }

}