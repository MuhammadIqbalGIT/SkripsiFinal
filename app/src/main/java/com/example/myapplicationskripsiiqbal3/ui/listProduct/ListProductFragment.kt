package com.example.myapplicationskripsiiqbal3.ui.listProduct

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.remote.request.DeleteProductRequest
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.deleteProduct.ApiResponse
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentListProductBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListProductFragment : BaseFragment<FragmentListProductBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListProductBinding
        get() = FragmentListProductBinding::inflate

    private lateinit var adapterProduct: ListProductAdapter
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

    override fun FragmentListProductBinding.initUI() {
        binding.appbar.tvScreen.text = "List Produk"
        adapterProduct = ListProductAdapter()
        rvProduct.adapter = adapterProduct
        rvProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (isSearchOpen) {
            appbar.ivSearch.isVisible = false
            appbar.tvScreen.isVisible = false
            appbar.tilSearch.isVisible = true
        }
    }

    override fun FragmentListProductBinding.initEvent() {

        with(appbar) {
            ivSearch.setOnClickListener {
                ivSearch.isVisible = false
                tvScreen.isVisible = false
                tilSearch.isVisible = true
                etSearch.requestFocus()
            }
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val query = s.toString().trim()
                }
            })
        }

        adapterProduct.onButtonDetailClick = {
            val action =
                ListProductFragmentDirections.actionListProductFragmentToProductDetailFragment(it.id)
            findNavController().navigate(action)
        }

        appbar.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        adapterProduct.onIconDeleteClick = {
            deleteProduct(it.id)
        }
        adapterProduct.onButtonChangeClick = {
            showBottomSheetProduct(it)
        }
    }


    private fun showBottomSheetProduct(item : ProductModel) {
        val sheetEditProduct = ListProductEditProductBottomSheetFragment.newInstance(item)
        sheetEditProduct.show(parentFragmentManager, "ListProductEditProductBottomSheetFragment")
    }


    override fun FragmentListProductBinding.initObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                RetrofitClient.instance.getProduct()
                    .enqueue(object : Callback<ArrayList<ProductModel>> {
                        override fun onResponse(
                            call: Call<ArrayList<ProductModel>>,
                            response: Response<ArrayList<ProductModel>>
                        ) {
                            response.body()?.let {
                                adapterProduct.submitList(it)
                                val textCount = "Total Produk Anda"
                                val count = adapterProduct.itemCount
                                binding.tvCount.text = "$textCount $count"
                            }
                        }

                        override fun onFailure(call: Call<ArrayList<ProductModel>>, t: Throwable) {
                            // Handle failure
                        }
                    })
            }
        }
    }

    fun deleteProduct(productId: Int) {
        val productService = RetrofitClient.instance
        val request =  DeleteProductRequest(
            id = productId
        )


        val call = productService.deleteProduct(productId)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
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






