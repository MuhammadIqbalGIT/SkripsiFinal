package com.example.myapplicationskripsiiqbal3.ui.base

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

    val TAG = this.javaClass.simpleName

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    var loadingDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)

        dialog?.window?.apply {
            val backgroundDrawable = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(backgroundDrawable, 16.px)
            setBackgroundDrawable(inset)
            requestFeature(Window.FEATURE_NO_TITLE)
        }

        return requireNotNull(_binding).root
    }

    override fun onStart() {
        super.onStart()
//        val width = (resources.displayMetrics.widthPixels * 0.98).toInt()
//        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.marginStart = 16.px
        layoutParams.marginEnd = 16.px

        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    open fun VB.initUI() {

    }

    open fun VB.initEvent() {

    }

    open fun VB.initObserve() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        if (loadingDialog != null) {
            if (loadingDialog!!.isShowing)
                loadingDialog?.dismiss()

            loadingDialog = null
        }
    }
}