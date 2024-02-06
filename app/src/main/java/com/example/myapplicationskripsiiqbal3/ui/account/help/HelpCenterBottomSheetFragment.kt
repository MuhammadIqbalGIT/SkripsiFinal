package com.example.myapplicationskripsiiqbal3.ui.account.help

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplicationskripsiiqbal3.databinding.FragmentHelpCenterBottomSheetBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseBottomSheetFragment


class HelpCenterBottomSheetFragment :
    BaseBottomSheetFragment<FragmentHelpCenterBottomSheetBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHelpCenterBottomSheetBinding
        get() = FragmentHelpCenterBottomSheetBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initEvent()
        }
    }


    override fun FragmentHelpCenterBottomSheetBinding.initEvent() {
        clWA.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=+6289665107636"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        clEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:iqbalvzr@gmail.com")

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Tidak ada aplikasi email", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        btnBack.setOnClickListener {
            dismiss()
        }
    }
}