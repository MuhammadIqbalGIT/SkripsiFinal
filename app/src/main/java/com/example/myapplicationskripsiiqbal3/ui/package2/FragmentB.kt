package com.example.myapplicationskripsiiqbal3.ui.package2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.PenggunaModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentBBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import com.example.myapplicationskripsiiqbal3.ui.package1.PenggunaAdapter
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        getUserPengguna()
    }

    override fun FragmentBBinding.initEvent() {
    }

    override fun FragmentBBinding.initObserve() {
    }


    private fun FragmentBBinding.getUserPengguna() {
        viewLifecycleOwner.lifecycleScope.launch {
            val adapter = PenggunaAdapter()
            rvPengguna.setHasFixedSize(true)
            rvPengguna.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvPengguna.adapter = adapter

            RetrofitClient.instance.getPenggunaCall()
                .enqueue(object : Callback<ArrayList<PenggunaModel>> {
                    override fun onResponse(
                        call: Call<ArrayList<PenggunaModel>>,
                        response: Response<ArrayList<PenggunaModel>>
                    ) {
                        val responseCode: String = response.code().toString()
                        tvResponseCode.text = responseCode
                        response.body()?.let {
                            adapter.submitList(it)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<PenggunaModel>>, t: Throwable) {
                        // Handle failure
                    }
                })
        }

    }

}