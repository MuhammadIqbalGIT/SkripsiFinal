package com.example.myapplicationskripsiiqbal3.ui.listProduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.core.data.remote.request.AddNewProductRequest
import com.example.core.data.remote.request.UpdateProductRequest
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.addNewProduct.ProductData
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentAddProductBinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentListProductEditProductBottomSheetBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseBottomSheetFragment
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListProductEditProductBottomSheetFragment :
    BaseBottomSheetFragment<FragmentListProductEditProductBottomSheetBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListProductEditProductBottomSheetBinding
        get() = FragmentListProductEditProductBottomSheetBinding::inflate


    private lateinit var productModel : ProductModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productModel = requireArguments().getSerializable("item") as ProductModel

        Log.d("dsahdgstsa6d789saod",productModel.toString())
    }


    override fun FragmentListProductEditProductBottomSheetBinding.initUI() {
        setEditTextDefaultFromData()
    }


    override fun FragmentListProductEditProductBottomSheetBinding.initEvent() {
        btnBack.setOnClickListener {
            dialog?.dismiss()
        }
        btnSimpan.setOnClickListener {
            updateProduct()
        }
    }

    override fun FragmentListProductEditProductBottomSheetBinding.initObserve() {

    }

    private fun FragmentListProductEditProductBottomSheetBinding.setEditTextDefaultFromData(){
        etNamaProduk.setText(productModel.nama)
        etHarga.setText( FormatCurrency.formatRp(productModel.harga))
        etSatuan.setText(productModel.satuan)
        etStock.setText(productModel.stok.toString())
        tvNameProductData.text = productModel.nama
    }


    private fun FragmentListProductEditProductBottomSheetBinding.updateProduct() {
        val etNameProduct = etNamaProduk.text.toString()
        val etUnit = etSatuan.text.toString()
        val etStock = etStock.text.toString()
        val etPrice = etHarga.text.toString()
        if (etNameProduct.isNotEmpty() && etUnit.isNotEmpty() && etStock.isNotEmpty() && etPrice.isNotEmpty()) {
            val productRequest = UpdateProductRequest(productModel.id,etNameProduct, etUnit, etStock, etPrice)
            val productService = RetrofitClient.instance
            val call: Call<ProductData> = productService.updateProduct(productRequest)

            Log.d("dsadar45235431",productRequest.toString())
            call.enqueue(object : Callback<ProductData> {
                override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                    val rawResponse = response.raw().toString()
                    println("Raw Response: $rawResponse")
                    if (response.isSuccessful) {
                        val productResponse: ProductData? = response.body()
                        productResponse?.let {
                            Toast.makeText(requireContext(), "Berhasil di update", Toast.LENGTH_SHORT).show()
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Gagal diupdate", Toast.LENGTH_SHORT).show()                    }
                }

                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                }
            })
        }
    }


    companion object {
        fun newInstance(item: ProductModel) = ListProductEditProductBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putSerializable("item", item)
            }
        }
    }
}