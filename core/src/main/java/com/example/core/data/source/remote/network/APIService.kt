package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ResponSekolah
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("sekolah")
    suspend fun getSekolah(
        @Query("perPage") perPage: Int=25
    ): ResponSekolah
}