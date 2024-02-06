package com.example.core.domain.model.menu

import androidx.annotation.DrawableRes
import java.io.Serializable


data class MenuModel(
    val menuId: String,
    val menuNama: String,
    val menuDesc: String?,
    @DrawableRes
    val menuIcon: Int?,
    val isVisible: Boolean
) : Serializable


