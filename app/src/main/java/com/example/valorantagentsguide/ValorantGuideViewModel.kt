package com.example.valorantagentsguide

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantagentsguide.data.AgentsRepository
import com.example.valorantagentsguide.data.local.entity.AgentsEntity
import com.example.valorantagentsguide.ui.common.Result
import com.example.valorantagentsguide.ui.detail.DetailActivity
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

    fun navigateToDetail(agent: AgentsEntity, activity: ComponentActivity) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.AGENT_DATA, agent)
        activity.startActivity(intent)
    }
}