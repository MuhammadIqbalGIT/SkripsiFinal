package com.example.myapplicationskripsiiqbal3.ui.package1

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.product.ProductModel
import com.example.myapplicationskripsiiqbal3.databinding.FragmentABinding
import com.example.myapplicationskripsiiqbal3.databinding.FragmentBBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment
import com.example.myapplicationskripsiiqbal3.utils.widget.RecyclerViewGridSpace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class FragmentA : BaseFragment<FragmentABinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentABinding
        get() = FragmentABinding::inflate

    private lateinit var adapterProduct: ProductAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
        }
    }


    override fun FragmentABinding.initUI() {
        binding.getUserPengguna()

    }
    private val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun FragmentABinding.getUserPengguna() {
        viewLifecycleOwner.lifecycleScope.launch {

            // Inisialisasi adapterProduct di luar lifecycleScope.launch yang tidak perlu
            adapterProduct = ProductAdapter()

            // Inisialisasi dan konfigurasi rvProduct di dalam lifecycleScope.launch
            binding.rvProduct.apply {
                adapter = adapterProduct

                // Ganti layoutManager ke GridLayoutManager untuk tampilan grid
                layoutManager = GridLayoutManager(requireContext(), 2)

                // Hapus addItemDecoration jika tidak ingin menggunakan grid space
                // val decorGrid = RecyclerViewGridSpace(2, 16.px, false)
                // addItemDecoration(decorGrid)
            }

            RetrofitClient.instance.getProduct()
                .enqueue(object : Callback<ArrayList<ProductModel>> {
                    override fun onResponse(
                        call: Call<ArrayList<ProductModel>>,
                        response: Response<ArrayList<ProductModel>>
                    ) {
                        val responseCode: String = response.code().toString()
                        // tvResponseCode.text = responseCode
                        response.body()?.let {
                            adapterProduct.submitList(it)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<ProductModel>>, t: Throwable) {
                        // Handle failure
                    }
                })
        }
    }



    override fun FragmentABinding.initEvent() {

    }

    override fun FragmentABinding.initObserve() {
    }

}