package com.example.myapplicationskripsiiqbal3.ui.sale

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.product.ApiResponseProduct
import com.example.core.domain.model.product.Data
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentSaleBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SaleFragment : BaseFragment<FragmentSaleBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate

    private lateinit var adapterProduct: SaleProductAdapter
    private lateinit var productModel: Data
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

    override fun FragmentSaleBinding.initUI() {
        binding.appbar.tvScreen.text = "List Produk"
        adapterProduct = SaleProductAdapter()
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

    override fun FragmentSaleBinding.initEvent() {

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
        appbar.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        adapterProduct.onItemClickListener = {

            enableButton()
        }
        btnChoose.setOnClickListener {
            val selectedProducts = mutableListOf<ProductModel>()
            adapterProduct.currentList.forEach { item ->
                if (item.isChecked == true) {
                    selectedProducts.add(item)
                }
            }
            productModel.products = selectedProducts
            val action =
                SaleFragmentDirections.actionSaleFragmentToCheckOutFragment(productModel)
            findNavController().navigate(action)
        }

    }


    private fun enableButton() {
        val isAllUnchecked = adapterProduct.currentList.all { !it?.checkbox!!.isChecked }
        binding.btnChoose.isEnabled = !isAllUnchecked
    }


    private fun getListProduct(search: String) {
        val apiService = RetrofitClient.instance
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
                        productModel = apiResponse.data!!
                        brandList?.let {
                            productModel = apiResponse.data!!
                            if (it.isEmpty()) {
                                binding.llNoData.isVisible = true
                                adapterProduct.submitList(null)
                            } else {
                                binding.llNoData.isVisible = false
                                adapterProduct.submitList(it)
                            }
                        }
                    } else {
                        val errorMessage = apiResponse?.message ?: "Unknown error"
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponseProduct>, t: Throwable) {}
        })
    }
}