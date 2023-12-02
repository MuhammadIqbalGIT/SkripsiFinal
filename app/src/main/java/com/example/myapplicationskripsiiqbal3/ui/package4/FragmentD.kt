package com.example.myapplicationskripsiiqbal3.ui.package4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.FragmentCBinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentDBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class FragmentD : BaseFragment<FragmentDBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDBinding
        get() = FragmentDBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentDBinding.initUI() {

    }

    override fun FragmentDBinding.initEvent() {
    }

    override fun FragmentDBinding.initObserve() {
    }

}