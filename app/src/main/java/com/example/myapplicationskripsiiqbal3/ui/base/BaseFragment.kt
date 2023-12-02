package com.example.myapplicationskripsiiqbal3.ui.base

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.utils.widget.CustomAlertDialogOkFragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    val TAG = this.javaClass.simpleName

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    var alertDialog: AlertDialog? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    private var destroyBinding = true

    var loadingDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root

    }

    override fun onResume() {
        super.onResume()

        if (!destroyBinding)
            destroyBinding = true

    }

    fun setDestroyBinding(destroy: Boolean = true) {
        destroyBinding = destroy
    }

    open fun VB.initUI() {

    }

    open fun VB.initEvent() {

    }

    open fun VB.initObserve() {

    }


    override fun onDestroyView() {
        super.onDestroyView()

        if (loadingDialog != null) {
            if (loadingDialog!!.isShowing)
                loadingDialog?.dismiss()

            loadingDialog = null
        }


        if (destroyBinding)
            _binding = null
    }

    fun showDialogErrorNoAction(pesan: String) {
        val alert = CustomAlertDialogOkFragment.newInstance(
            "Pesan Pemberitahuan",
            pesan,
            R.drawable.ic_alert_warning,
            "Tutup",
            false
        )

        alert.show(childFragmentManager, alert.TAG)
    }

    fun showDialogErrorWithAction(pesan: String, onClick: (() -> Unit)) {
        val alert = CustomAlertDialogOkFragment.newInstance(
            "Pesan Pemberitahuan",
            pesan,
            R.drawable.ic_alert_warning,
            "Tutup",
            false
        )

        alert.onPositiveClick = onClick

        alert.show(childFragmentManager, alert.TAG)
    }


    fun showLoading(cancelable: Boolean = false) {

        if (loadingDialog == null) {
            loadingDialog = Dialog(requireContext())
            loadingDialog?.setContentView(R.layout.loading_dialog)
            loadingDialog?.setCancelable(false)
        }

        loadingDialog?.setCancelable(cancelable)
        loadingDialog?.show()
    }

    fun dismissLoading() {
        if (loadingDialog?.isShowing == true)
            loadingDialog?.dismiss()
    }

    fun showSnackbar(
        message: String,
        backgroundColor: String,
        actionColor: String,
        textColor: String,
        anchorView: View,
        duration: Int
    ) {
        Snackbar.make(binding.root, message, duration)
            .setActionTextColor(Color.parseColor(actionColor))
            .setBackgroundTint(Color.parseColor((backgroundColor)))
            .setTextColor(Color.parseColor(textColor))
            .setAnchorView(anchorView)
            .show()
    }

    fun showSnackbarWithAction(
        message: String,
        backgroundColor: String,
        textColor: String,
        anchorView: View,
        actionColor: String,
        actionText: String,
        actionListener: ((View) -> Unit),
        duration: Int
    ) {
        Snackbar.make(binding.root, message, duration)
            .setBackgroundTint(Color.parseColor((backgroundColor)))
            .setTextColor(Color.parseColor(textColor))
            .setAnchorView(anchorView)
            .setAction(actionText, actionListener)
            .setActionTextColor(Color.parseColor(actionColor))
            .show()
    }
}