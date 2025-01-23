package com.example.valorantagentsguide.data.remote.retrofit

import com.example.valorantagentsguide.data.remote.response.AgentsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("agents")
    suspend fun getAgents(): AgentsResponse
}