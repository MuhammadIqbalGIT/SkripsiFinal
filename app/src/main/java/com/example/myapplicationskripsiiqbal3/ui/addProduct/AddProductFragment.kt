package com.example.myapplicationskripsiiqbal3.ui.addProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.core.data.remote.request.AddNewProductRequest
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.addNewProduct.ProductData
import com.example.myapplicationskripsiiqbal3.databinding.FragmentAddProductBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddProductBinding
        get() = FragmentAddProductBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }


    override fun FragmentAddProductBinding.initUI() {

    }

    override fun FragmentAddProductBinding.initEvent() {
        btnAddProduct.setOnClickListener {
            addProduct()
        }
    }


    override fun FragmentAddProductBinding.initObserve() {

    }

    private fun FragmentAddProductBinding.addProduct() {
        val etNameProduct = editTextNama.text.toString()
        val etUnit = editTextSatuan.text.toString()
        val etStock = editTextStok.text.toString()
        val etPrice = editTextHarga.text.toString()
        if (etNameProduct.isNotEmpty() && etUnit.isNotEmpty() && etStock.isNotEmpty() && etPrice.isNotEmpty()) {
            val productRequest = AddNewProductRequest(etNameProduct, etUnit, etStock, etPrice)
            val productService = RetrofitClient.instance
            val call: Call<ProductData> = productService.addProduct(productRequest)
            call.enqueue(object : Callback<ProductData> {
                override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                    val rawResponse = response.raw().toString()
                    println("Raw Response: $rawResponse")
                    if (response.isSuccessful) {
                        val productResponse: ProductData? = response.body()
                        productResponse?.let {
                            Toast.makeText(requireContext(), "Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Gagal ditambahkan", Toast.LENGTH_SHORT).show()                    }
                }

                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                }
            })
        }
    }
}