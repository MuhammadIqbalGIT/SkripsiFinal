package com.example.myapplicationskripsiiqbal3.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.product.ProductModel
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentProductBinding.initUI() {
        adapterProduct = ProductAdapter()
    }

    override fun FragmentProductBinding.initObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                binding.rvProduct.apply {
                    adapter = adapterProduct
                    layoutManager = GridLayoutManager(requireContext(), 2)

                }

                RetrofitClient.instance.getProduct()
                    .enqueue(object : Callback<ArrayList<ProductModel>> {
                        override fun onResponse(
                            call: Call<ArrayList<ProductModel>>,
                            response: Response<ArrayList<ProductModel>>

                        ) {
                            val rawResponse = response.raw().toString()
                            println("Raw Response: $rawResponse")
                         //   val responseCode: String = response.code().toString()
                            // tvResponseCode.text = responseCode
                            response.body()?.let {
                                adapterProduct.submitList(it)
                            }
                        }

                        override fun onFailure(call: Call<ArrayList<ProductModel>>, t: Throwable) {
                        }
                    })
            }
        }
    }

    override fun FragmentProductBinding.initEvent() {
        adapterProduct.onCardListener = {
            val action = ProductFragmentDirections.actionProductFragmentToProductDetailFragment(it.id)
            findNavController().navigate(action)
        }

    }

}