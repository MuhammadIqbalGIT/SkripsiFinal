package com.example.myapplicationskripsiiqbal3.utils.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.myapplicationskripsiiqbal3.databinding.FragmentCustomAlertDialogOkBinding
import com.example.myapplicationskripsiiqbal3.ui.base.BaseDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_MESSAGE = "ARG_MESSAGE"
private const val ARG_RES_ICON = "ARG_RES_ICON"
private const val ARG_POSITIVE_TEXT = "ARG_POSITIVE_TEXT"
private const val ARG_CANCELABLE = "ARG_CANCELABLE"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomAlertDialogOkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomAlertDialogOkFragment : BaseDialogFragment<FragmentCustomAlertDialogOkBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCustomAlertDialogOkBinding
        get() = FragmentCustomAlertDialogOkBinding::inflate

    var onPositiveClick: (() -> Unit)? = null

    private lateinit var title: String
    private lateinit var message: String
    private var resIcon: Int = -1
    private lateinit var positiveButtonText: String
    private var cancelableView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            title = it.getString(ARG_TITLE, "")
            message = it.getString(ARG_MESSAGE, "")
            resIcon = it.getInt(ARG_RES_ICON)
            positiveButtonText = it.getString(ARG_POSITIVE_TEXT, "")
            cancelableView = it.getBoolean(ARG_CANCELABLE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentCustomAlertDialogOkBinding.initUI() {
        isCancelable = cancelableView

        imgIcon.setImageResource(resIcon)
        txtTitle.text = title
        txtMessage.text = message
        btnPositive.text = positiveButtonText

        if (title.isEmpty())
            txtTitle.isVisible = false

        if (message.isEmpty())
            txtMessage.isVisible = false

    }

    override fun FragmentCustomAlertDialogOkBinding.initEvent() {

        btnPositive.setOnClickListener {
            dismiss()
            onPositiveClick?.invoke()
        }
    }

    companion object {

        const val TAG = "CustomAlertDialogOkFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomAlertDialogOkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            title: String = "",
            message: String,
            resIcon: Int,
            positiveButtonText: String,
            cancelable: Boolean = true
        ) =
            CustomAlertDialogOkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_MESSAGE, message)
                    putInt(ARG_RES_ICON, resIcon)
                    putString(ARG_POSITIVE_TEXT, positiveButtonText)
                    putBoolean(ARG_CANCELABLE, cancelable)
                }
            }
    }
}