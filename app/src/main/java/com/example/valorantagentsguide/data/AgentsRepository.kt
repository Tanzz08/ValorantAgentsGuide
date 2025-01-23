package com.example.valorantagentsguide.data

import android.util.Log
import com.example.valorantagentsguide.data.local.entity.AgentsEntity
import com.example.valorantagentsguide.data.local.room.AgentsDao
import com.example.valorantagentsguide.data.remote.retrofit.ApiService
import com.example.valorantagentsguide.ui.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AgentsRepository(
    private val apiService: ApiService,
    private val agentsDao: AgentsDao
) {
    fun getAgents(): Flow<Result<List<AgentsEntity>>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getAgents()
            val agentsList = response.data.map { agent ->
                val role = agent.role
                val abilities = agent.abilities.firstOrNull()
                AgentsEntity(
                    uuid = agent.uuid,
                    displayName = agent.displayName,
                    description = agent.description,
                    fullPortraitImg = agent.fullPortrait,
                    displayIcon = agent.displayIcon,
                    roleName = role.displayName,
                    roleIcon = role.displayIcon,
                    abilityName = abilities?.displayName ?: "Unknown",
                    abilityIcon = abilities?.displayIcon ?: "",
                    abilityDesc = abilities?.description ?: "No description available"
                )
            }
            agentsDao.insertsAgents(agentsList)
            val cachedAgents = agentsDao.getAgents()
            emit(Result.Success(cachedAgents))
        } catch (e: Exception) {
            Log.e("AgentsRepository", "Error fetching agents: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        @Volatile
        private var instance: AgentsRepository? = null
        fun getInstance(
            apiService: ApiService,
            agentsDao: AgentsDao
        ): AgentsRepository =
            instance ?: synchronized(this) {
                instance ?: AgentsRepository(apiService, agentsDao)
            }.also { instance = it }
    }
}