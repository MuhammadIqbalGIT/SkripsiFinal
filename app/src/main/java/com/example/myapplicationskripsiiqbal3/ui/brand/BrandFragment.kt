package com.example.myapplicationskripsiiqbal3.ui.brand

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.brand.ApiResponse
import com.example.myapplicationskripsiiqbal3.databinding.FragmentBrandBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandFragment : BaseFragment<FragmentBrandBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBrandBinding
        get() = FragmentBrandBinding::inflate

    private lateinit var adapter: BrandAdapter
    private var isSearchOpen = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentBrandBinding.initUI() {
        binding.appbar.tvScreen.text = "List Brand"
        adapter = BrandAdapter()
        rvBrand.adapter = adapter
        rvBrand.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        if (isSearchOpen) {
            appbar.ivSearch.isVisible = false
            appbar.tvScreen.isVisible = false
            appbar.tilSearch.isVisible = true
        }
        getListBrand("")
    }

    override fun FragmentBrandBinding.initEvent() {
        with(appbar) {
            ivSearch.setOnClickListener {
                ivSearch.isVisible = false
                tvScreen.isVisible = false
                tilSearch.isVisible = true
                etSearch.requestFocus()
            }
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val query = s.toString().trim()
                    getListBrand(query)
                }
            })

            appbar.ivBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }







        }
    }

    override fun FragmentBrandBinding.initObserve() {


    }

    private fun getListBrand(nameBrand: String) {
        val apiService = RetrofitClient.instance

        // Replace "Brand A" with the actual brand name you want to query
        val call: Call<ApiResponse> = apiService.getBrand(nameBrand)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse?.success == true) {
                        val brandList = apiResponse.data?.brands
                        brandList?.let {
                            if (it.isEmpty()) {
                                showSnackbar("Tidak ada data")
                                binding.llNoData.isVisible = true
                                adapter.submitList(null) // Clear the RecyclerView
                            } else {
                                binding.llNoData.isVisible = false
                                adapter.submitList(it)
                            }
                        }
                    } else {
                        // Handle unsuccessful response
                        val errorMessage = apiResponse?.message ?: "Unknown error"
                        showSnackbar(errorMessage)
                    }
                } else {
                    // Handle unsuccessful HTTP response
                    showSnackbar("Gagal mengambil data dari server")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle network failure
                showSnackbar("Terdapat masalah jaringan")
            }
        })
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }


}
