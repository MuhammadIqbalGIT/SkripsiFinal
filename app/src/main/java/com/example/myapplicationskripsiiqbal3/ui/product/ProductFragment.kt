package com.example.myapplicationskripsiiqbal3.ui.product

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.remote.request.DeleteProductRequest
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.deleteProduct.ApiResponse
import com.example.core.domain.model.product.ApiResponseProduct
import com.example.myapplicationskripsiiqbal3.databinding.FragmentProductBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : BaseFragment<FragmentProductBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductBinding
        get() = FragmentProductBinding::inflate

    private lateinit var adapterProduct: ProductAdapter
    private var isSearchOpen = false
    private var isFromSearch = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentProductBinding.initUI() {
        binding.appbar.tvScreen.text = "List Produk"
        adapterProduct = ProductAdapter()
        rvProduct.adapter = adapterProduct
        rvProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (isSearchOpen) {
            appbar.ivSearch.isVisible = false
            appbar.tvScreen.isVisible = false
            appbar.tilSearch.isVisible = true
        }
        getListProduct("")
    }

    override fun FragmentProductBinding.initEvent() {

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
                    getListProduct(query)
                }
            })
        }
        adapterProduct.onCardListener = {
            val action =
                ProductFragmentDirections.actionProductFragmentToProductDetailFragment(it.id?:0)
            findNavController().navigate(action)
        }
    }


    private fun getListProduct(search: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                binding.rvProduct.apply {
                    adapter = adapterProduct
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    val apiService = RetrofitClient.instance
                    // Replace "Brand A" with the actual brand name you want to query
                    val call: Call<ApiResponseProduct> = apiService.getProduct(search)

                    call.enqueue(object : Callback<ApiResponseProduct> {
                        override fun onResponse(
                            call: Call<ApiResponseProduct>,
                            response: Response<ApiResponseProduct>
                        ) {
                            if (response.isSuccessful) {
                                val apiResponse = response.body()
                                if (apiResponse?.success == true) {
                                    val brandList = apiResponse.data?.products
                                    brandList?.let {
                                        if (it.isEmpty()) {
                                            //    showSnackbar("Tidak ada data")
                                            binding.llNoData.isVisible = true
                                            adapterProduct.submitList(null) // Clear the RecyclerView
                                        } else {
                                            binding.llNoData.isVisible = false
                                            adapterProduct.submitList(it)
                                        }
                                    }
                                } else {
                                    // Handle unsuccessful response
                                    val errorMessage = apiResponse?.message ?: "Unknown error"
                                    //   showSnackbar(errorMessage)
                                }
                            } else {
                                // Handle unsuccessful HTTP response
//                    showSnackbar("Gagal mengambil data dari server")
                            }
                        }

                        override fun onFailure(call: Call<ApiResponseProduct>, t: Throwable) {
                            // Handle network failure
                            // showSnackbar("Terdapat masalah jaringan")
                        }

                    })
                }
            }
        }
    }

    fun deleteProduct(productId: Int) {
        val productService = RetrofitClient.instance
        val request = DeleteProductRequest(
            id = productId
        )


        val call = productService.deleteProduct(productId)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                val rawResponse = response.raw().toString()
                println("Raw Response: $rawResponse")
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse?.success == true) {
                        val deletedProduct = apiResponse.data?.product
                        if (deletedProduct != null) {
                            // showToast("Product deleted successfully: ${deletedProduct.nama}")
                            // Handle the deleted product details as needed
                        } else {
                            //showToast("Deleted product details are null.")
                        }
                    } else {
                        // handleDeleteFailure(apiResponse?.message)
                    }
                } else {
                    //  handleDeleteFailure("Server error. Please try again.")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // handleDeleteFailure("Network error. Please check your connection.")
            }
        })
    }
}