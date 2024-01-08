package com.example.myapplicationskripsiiqbal3.ui.addProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.databinding.FragmentAddProductBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment


class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddProductBinding
        get() = FragmentAddProductBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }


    override fun FragmentAddProductBinding.initUI() {

    }

    override fun FragmentAddProductBinding.initEvent() {


    }


    override fun FragmentAddProductBinding.initObserve() {


    }


}