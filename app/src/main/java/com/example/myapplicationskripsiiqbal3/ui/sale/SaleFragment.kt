package com.example.myapplicationskripsiiqbal3.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.databinding.FragmentSaleBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment


class SaleFragment : BaseFragment<FragmentSaleBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }


    override fun FragmentSaleBinding.initUI() {

    }

    override fun FragmentSaleBinding.initEvent() {

    }

    override fun FragmentSaleBinding.initObserve() {

    }
}