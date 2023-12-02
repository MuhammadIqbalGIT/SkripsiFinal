package com.example.myapplicationskripsiiqbal3.ui.package1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.databinding.FragmentABinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment


class FragmentA : BaseFragment<FragmentABinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentABinding
        get() = FragmentABinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentABinding.initUI() {

    }

    override fun FragmentABinding.initEvent() {
    }

    override fun FragmentABinding.initObserve() {
    }

}