package com.example.valorantagentsguide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantagentsguide.data.AgentsRepository
import com.example.valorantagentsguide.data.local.entity.AgentsEntity
import com.example.valorantagentsguide.ui.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ValorantGuideViewModel(
    private val agentsRepository: AgentsRepository
) : ViewModel() {
    private val _agents = MutableStateFlow<Result<List<AgentsEntity>>>(Result.Loading)
    val agents: StateFlow<Result<List<AgentsEntity>>> = _agents

    private val agentData = MutableLiveData<AgentsEntity>()

    init {
        viewModelScope.launch {
            agentsRepository.getAgents().collect { result ->
                _agents.value = result
            }
        }
    }

    fun setAgentData(agent: AgentsEntity) {
        agentData.value = agent
    }
}