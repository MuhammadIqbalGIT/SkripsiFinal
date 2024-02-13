package com.example.myapplicationskripsiiqbal3.ui.sale.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationskripsiiqbal3.databinding.FragmentCheckOutBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class CheckOutFragment : BaseFragment<FragmentCheckOutBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCheckOutBinding
        get() = FragmentCheckOutBinding::inflate


    private val args: CheckOutFragmentArgs by navArgs()
    private lateinit var adapterProduct: CheckOutProductAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentCheckOutBinding.initUI() {
        adapterProduct = CheckOutProductAdapter()
        rvProductCheckout.adapter = adapterProduct
        rvProductCheckout.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val temp = args.product.products?.filter {
            it.isChecked
        }
        adapterProduct.submitList(temp)
    }

    override fun FragmentCheckOutBinding.initEvent() {
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun FragmentCheckOutBinding.initObserve() {

    }

}