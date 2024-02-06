package com.example.myapplicationskripsiiqbal3.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.menu.MenuModel
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.FragmentAccountBinding
import com.example.myapplicationskripsiiqbal3.ui.account.help.HelpCenterBottomSheetFragment
import com.example.myapplicationskripsiiqbal3.ui.account.menu.MenuAdapter
import com.example.myapplicationskripsiiqbal3.ui.account.menu.MenuHelpAdapter
import com.example.myapplicationskripsiiqbal3.ui.base.BaseFragment

class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAccountBinding
        get() = FragmentAccountBinding::inflate

    private lateinit var adapterMenu: MenuAdapter
    private lateinit var adapterHelp: MenuHelpAdapter
    private val listMenu: MutableList<MenuModel> = mutableListOf()
    private val listHelp: MutableList<MenuModel> = mutableListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
        }
    }

    override fun FragmentAccountBinding.initUI() {
        listMenu.clear()
        adapterMenu = MenuAdapter()
        rvMenu.adapter = adapterMenu
        rvMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        menu()


        listHelp.clear()
        adapterHelp = MenuHelpAdapter()
        rvHelp.adapter = adapterHelp
        rvHelp.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        menuHelp()
    }


    private fun menu() {
        val local1 = MenuModel(
            menuId = MENU_ADD_PRODUCT,
            menuNama = "Tambah Produk",
            menuDesc = "Tambah Produk Anda Disini",
            menuIcon = R.drawable.ic_add,
            isVisible = true
        )
        val local2 = MenuModel(
            menuId = MENU_LIST_PRODUCT,
            menuNama = "List Produk",
            menuDesc = "Lihat Produk Anda Disini",
            menuIcon = R.drawable.ic_add,
            isVisible = true
        )

        val local3 = MenuModel(
            menuId = MENU_LIST_BRAND,
            menuNama = "List Brand",
            menuDesc = "Lihat Brand Anda Disini",
            menuIcon = R.drawable.ic_add,
            isVisible = true
        )
        val data = listOf(local1, local2, local3)
        adapterMenu.submitList(data)
    }

    private fun menuHelp() {
        val local1 = MenuModel(
            menuId = MENU_HELP,
            menuNama = "Bantuan",
            menuDesc = "Butuh Bantuan, Klik Disini",
            menuIcon = R.drawable.ic_add,
            isVisible = true
        )
        val dataHelp = listOf(local1)
        adapterHelp.submitList(dataHelp)
    }

    override fun FragmentAccountBinding.initEvent() {

        adapterMenu.onCardListener = {
            when (it.menuId) {
                MENU_ADD_PRODUCT -> {
                    val action =
                        AccountFragmentDirections.actionAccountFragmentToAddProductFragment()
                    findNavController().navigate(action)
                }

                MENU_LIST_PRODUCT -> {
                    val action =
                        AccountFragmentDirections.actionAccountFragmentToListProductFragment()
                    findNavController().navigate(action)
                }

                MENU_LIST_BRAND -> {
                    val action =
                        AccountFragmentDirections.actionAccountFragmentToBrandFragment()
                    findNavController().navigate(action)
                }
            }
        }
        adapterHelp.onCardListener = {
            when (it.menuId) {
                MENU_HELP -> {
                    showBottomSheetHelp()
                }
            }
        }
    }

    private fun showBottomSheetHelp() {
        val sheet = HelpCenterBottomSheetFragment()
        sheet.show(childFragmentManager, "HelpCenterBottomSheetFragment")
    }


    companion object {
        const val MENU_ADD_PRODUCT = "local1"
        const val MENU_LIST_PRODUCT = "local2"
        const val MENU_LIST_BRAND = "local3"
        const val MENU_HELP = "local4"
    }
}

