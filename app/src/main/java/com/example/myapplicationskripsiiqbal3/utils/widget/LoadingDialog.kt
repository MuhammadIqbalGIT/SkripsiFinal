package com.example.myapplicationskripsiiqbal3.utils.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.myapplicationskripsiiqbal3.R

object LoadingDialog {

    fun default(context: Context, cancelable: Boolean = true): Dialog {
        val dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(inflate)
        dialog.setCancelable(cancelable)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}