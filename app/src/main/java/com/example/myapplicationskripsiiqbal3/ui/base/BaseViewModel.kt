package com.example.myapplicationskripsiiqbal3.ui.base

import androidx.lifecycle.ViewModel
import com.example.core.data.remote.response.Origin

abstract class BaseViewModel(

) : ViewModel() {
    val TAG = this::class.java.simpleName
    val origin = Origin("app_mitra", "1")


}