package com.example.planit.dtos

import com.google.gson.annotations.SerializedName

data class DtoOutputCreateEvent(
    @SerializedName("events")
    val events:DtoOutputEvent)
