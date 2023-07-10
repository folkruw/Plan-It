package com.example.planit.dtos

import com.google.gson.annotations.SerializedName

data class DtoOutputEvent(
    @SerializedName("idEventsEmployee")
    val idEventEmployee:String,

    @SerializedName("idCompanies")
    val idCompanies:Int,

    @SerializedName("idAccount")
    val idAccoount : Int,

    @SerializedName("startDate")
    val startDate:String,

    @SerializedName("endDate")
    val endDate:String,

    @SerializedName("isValid")
    val isValid:Boolean,

    @SerializedName("comments")
    val comments:String,

    @SerializedName("types")
    val types:String)
