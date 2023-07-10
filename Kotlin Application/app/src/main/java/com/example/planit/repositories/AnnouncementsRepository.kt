package com.example.planit.repositories

import com.example.planit.dtos.DtoInputAnnouncements
import com.example.planit.dtos.DtoOutputFetcbByFunction
import retrofit2.http.Body
import retrofit2.http.POST

interface AnnouncementsRepository {
    @POST("Announcements/fetchByFunction")
    suspend fun fetchByFunction(@Body dto: DtoOutputFetcbByFunction):List<DtoInputAnnouncements>
}