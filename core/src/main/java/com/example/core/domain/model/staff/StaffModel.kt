package com.example.core.domain.model.staff

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StaffModel(
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("staff")
    val staff: List<ListStaffModel>? = null,

    @field:SerializedName("status")
    val status: Int? = null
) : Serializable


data class ListStaffModel(
    @field:SerializedName("staff_name")
    val staffName: String? = null,

    @field:SerializedName("staff_id")
    val staffId: String? = null,

    @field:SerializedName("staff_hp")
    val staffHp: String? = null,

    @field:SerializedName("staff_alamat")
    val staffAlamat: String? = null
)