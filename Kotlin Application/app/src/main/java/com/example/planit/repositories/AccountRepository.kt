package com.example.planit.repositories

import com.example.planit.dtos.DtoInputAccount
import com.example.planit.dtos.DtoOutputLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountRepository {

    @POST("Account/login/phone")
    suspend fun login(@Body account:DtoOutputLogin):DtoInputAccount
}