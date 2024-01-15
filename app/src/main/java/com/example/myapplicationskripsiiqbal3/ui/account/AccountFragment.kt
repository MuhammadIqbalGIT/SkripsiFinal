package com.example.myapplicationskripsiiqbal3.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.FragmentAccountBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAccountBinding
        get() = FragmentAccountBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }

}