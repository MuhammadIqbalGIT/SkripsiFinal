package com.example.myapplicationskripsiiqbal3.ui.product.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.core.data.remote.network.ApiService
import com.example.core.data.remote.response.ApiResponseNew
import com.example.core.data.remote.response.ProductApiResponse
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentProductDetailBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductDetailBinding
        get() = FragmentProductDetailBinding::inflate


    private val args: ProductDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }


    override fun FragmentProductDetailBinding.initUI() {
        Log.d("atgsdsa", args.productId.toString())
        loadProductDetail(args.productId)
    }

    override fun FragmentProductDetailBinding.initEvent() {

    }

    override fun FragmentProductDetailBinding.initObserve() {

    }
    private fun FragmentProductDetailBinding.loadProductDetail(productId: Int) {
        val productService = RetrofitClient.instance
        productService.getProductDetail(productId)
            .enqueue(object : Callback<ApiResponseNew<ProductApiResponse>> {
                override fun onResponse(
                    call: Call<ApiResponseNew<ProductApiResponse>>,
                    response: Response<ApiResponseNew<ProductApiResponse>>
                ) {
                    if (response.isSuccessful) {
                        val productApiResponse = response.body()?.data?.product

                        tvNamaProduk.text = productApiResponse?.nama
                        tvStock.text = productApiResponse?.stok.toString()
                        tvHargaDiskon.text = productApiResponse?.harga.toString()
                        tvBrandName.text = productApiResponse?.namaBrand


                    } else {
                        // Handle the error
                        // For example, show an error message
                    }
                }

                override fun onFailure(call: Call<ApiResponseNew<ProductApiResponse>>, t: Throwable) {
                    // Handle the failure
                    // For example, show an error message
                }
            })
    }

}