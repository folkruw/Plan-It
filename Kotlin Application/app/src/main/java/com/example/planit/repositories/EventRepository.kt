package com.example.planit.repositories

import com.example.planit.dtos.DtoInputEvent
import com.example.planit.dtos.DtoOutputCreateEvent
import retrofit2.http.*
import java.time.LocalDateTime

interface EventRepository {

    @GET("Events/fetch/{idCompanies}/{id}/{from}/{to}")
    suspend fun fetchByEmployee(
        @Path("idCompanies") idCompanies : Int,
        @Path("id") id: Int,
        @Path("from") from : LocalDateTime,
        @Path("to") to : LocalDateTime
    ): List<DtoInputEvent>

    @GET("Events/fetchByEmployee/{id}")
    suspend fun fetchRequest(
        @Path("id") id: Int
    ): List<DtoInputEvent>

    @POST("Events/create/{idCompanies}")
    suspend fun create(@Path("idCompanies") idCompanies : Int, @Body events: DtoOutputCreateEvent): DtoInputEvent
}