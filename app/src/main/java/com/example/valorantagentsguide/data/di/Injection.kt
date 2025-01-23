package com.example.valorantagentsguide.data.di

import android.content.Context
import com.example.valorantagentsguide.data.AgentsRepository
import com.example.valorantagentsguide.data.local.room.AgentsDatabase
import com.example.valorantagentsguide.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): AgentsRepository {
        val apiService = ApiConfig.getApiService()
        val database = AgentsDatabase.getInstance(context)
        val dao = database.agentsDao()
        return AgentsRepository.getInstance(apiService, dao)
    }
}