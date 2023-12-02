package com.example.myapplicationskripsiiqbal3.ui.base

import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import com.example.myapplicationskripsiiqbal3.MyApplication
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    val app = MyApplication.instance
    val TAG = this.javaClass.simpleName


    var loadingDialog: Dialog? = null
    var alertDialog: AlertDialog? = null

    override fun onDestroy() {
        super.onDestroy()

        if (loadingDialog != null) {
            if (loadingDialog!!.isShowing)
                loadingDialog!!.dismiss()
            loadingDialog = null
        }

        if (alertDialog != null) {
            if (alertDialog!!.isShowing)
                alertDialog!!.dismiss()
        }
    }
}