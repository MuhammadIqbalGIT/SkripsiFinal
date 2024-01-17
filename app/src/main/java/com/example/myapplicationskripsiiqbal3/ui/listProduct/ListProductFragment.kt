package com.example.myapplicationskripsiiqbal3.ui.listProduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentListProductBinding.initUI() {
        adapterProduct = ListProductAdapter()
        rvProduct.adapter = adapterProduct
        rvProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun FragmentListProductBinding.initEvent() {
        adapterProduct.onButtonDetailClick = {
            val action =
                ListProductFragmentDirections.actionListProductFragmentToProductDetailFragment(it.id)
            findNavController().navigate(action)
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
//                            val responseCode: String = response.code().toString()
//                            tvResponseCode.text = responseCode
                            response.body()?.let {
                                adapterProduct.submitList(it)
                                val textCount = "Total Produk Anda"
                                val count = adapterProduct.itemCount
                                tvCount.text = "$textCount $count"
                            }
                        }

                        override fun onFailure(call: Call<ArrayList<ProductModel>>, t: Throwable) {
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






