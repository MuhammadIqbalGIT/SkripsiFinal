package com.example.myapplicationskripsiiqbal3.utils.widget

import android.graphics.Paint
import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.utils.DateTimeFormatter
import com.example.myapplicationskripsiiqbal3.utils.FormatCurrency


@BindingAdapter("imageProductUrl")
fun loadImageProduct(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
//        Glide.with(view.context)
//            .load(url)
//            .error(R.drawable.ic_product_detault)
//            .into(view)

        view.load(url){
            error(R.drawable.ic_product_default)
        }
    } else {
        view.load(R.drawable.ic_product_default)
//        Glide.with(view.context)
//            .load(R.drawable.ic_product_detault)
//            .into(view)
    }
}

@BindingAdapter("imageBrandUrl")
fun loadImageBrand(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(url){
            error(R.drawable.ic_product_default)
        }
    } else {
        view.load(R.drawable.ic_product_default)
    }
}

@BindingAdapter("imageCustomerUrl")
fun loadImageCustomer(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .into(view)
    } else {
        Glide.with(view.context)
            .load(R.drawable.ic_product_default)
            .centerCrop()
            .into(view)
    }
}

@BindingAdapter("doubleToRp")
fun doubleToRp(view: TextView, double: Double?) {
    val nominal = FormatCurrency.formatRp(amount = double ?: 0.0)
    view.text = nominal
}

@BindingAdapter("bindingGetDateNow")
fun bindingGetDateNow(view: TextView, pattern: String?) {
    view.text = DateTimeFormatter.getDateNow(pattern) ?: ""
}

@BindingAdapter("bindingFormatDateToTimeOnly")
fun bindingFormatDateToTimeOnly(view: TextView, dateString: String) {
    view.text = DateTimeFormatter.formatDate(
        "HH:mm",
        currentFormat = "yyyy-MM-dd'T'HH:mm:ss.SS",
        dateString
    )
}

@BindingAdapter("datePattern", "dateString", requireAll = false)
fun bindingDateTimeFormatter(view: TextView, datePattern: String, dateString: String?) {
    if (!dateString.isNullOrEmpty())
        view.text = DateTimeFormatter.formatDate(
            datePattern,
            currentFormat = "yyyy-MM-dd'T'HH:mm:ss",
            dateString
        )
}

@BindingAdapter("bindSpannableString")
fun bindSpannableString(view: TextView, span: SpannableStringBuilder) {
    view.text = span
}

@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView, strikeThrough: Boolean) {
    if (strikeThrough) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}