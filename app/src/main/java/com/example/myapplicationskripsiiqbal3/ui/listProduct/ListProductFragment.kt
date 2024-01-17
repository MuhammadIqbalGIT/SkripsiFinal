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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentListProductBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import com.example.myapplicationskripsiiqbal3.ui.product.ProductAdapter
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
            val action = ListProductFragmentDirections.actionListProductFragmentToProductDetailFragment(it.id)
            findNavController().navigate(action)
        }
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

}