package com.example.myapplicationskripsiiqbal3.ui.package2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.FragmentABinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentBBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class FragmentB : BaseFragment<FragmentBBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBBinding
        get() = FragmentBBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentBBinding.initUI() {

    }

    override fun FragmentBBinding.initEvent() {
    }

    override fun FragmentBBinding.initObserve() {
    }

}