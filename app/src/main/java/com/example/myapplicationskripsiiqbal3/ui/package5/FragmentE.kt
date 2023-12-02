package com.example.myapplicationskripsiiqbal3.ui.package5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.FragmentDBinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentEBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class FragmentE : BaseFragment<FragmentEBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEBinding
        get() = FragmentEBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentEBinding.initUI() {

    }

    override fun FragmentEBinding.initEvent() {
    }

    override fun FragmentEBinding.initObserve() {
    }

}