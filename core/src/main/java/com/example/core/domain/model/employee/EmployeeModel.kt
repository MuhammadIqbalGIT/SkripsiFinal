package com.example.core.domain.model.employee

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EmployeeModel(
    @SerializedName("empId")
    val empId : Int,
    @SerializedName("empName")
    val empName : String?,
    @SerializedName("empContract")
    val empContract : String?,
    @SerializedName("empEmail")
    val empEmail : String?,
    @SerializedName("empAddress")
    val empAddress : String?
) : Serializable
