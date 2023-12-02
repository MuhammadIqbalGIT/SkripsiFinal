package com.example.myapplicationskripsiiqbal3.ui.package3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.FragmentABinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentCBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class FragmentC : BaseFragment<FragmentCBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCBinding
        get() = FragmentCBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentCBinding.initUI() {

    }

    override fun FragmentCBinding.initEvent() {
    }

    override fun FragmentCBinding.initObserve() {
    }

}