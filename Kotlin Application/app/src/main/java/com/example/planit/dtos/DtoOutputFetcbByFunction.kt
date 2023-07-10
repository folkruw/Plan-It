package com.example.planit.dtos

import com.google.gson.annotations.SerializedName

data class DtoOutputFetcbByFunction(
    @SerializedName("idFunctions")
    var idFunctions: Int,

    @SerializedName("idCompanies")
    var idCompanies: Int
)
