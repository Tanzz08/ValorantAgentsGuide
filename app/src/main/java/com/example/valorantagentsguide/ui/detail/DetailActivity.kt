package com.example.valorantagentsguide.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.IntentCompat
import com.example.valorantagentsguide.ValorantGuideViewModel
import com.example.valorantagentsguide.ViewModelFactory
import com.example.valorantagentsguide.data.local.entity.AgentsEntity
import com.example.valorantagentsguide.ui.screen.AgentDetailScreen
import com.example.valorantagentsguide.ui.theme.ValorantAgentsGuideTheme

class DetailActivity : ComponentActivity() {
    private lateinit var agent: AgentsEntity
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: ValorantGuideViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agent = IntentCompat.getParcelableExtra(intent, AGENT_DATA, AgentsEntity::class.java) as AgentsEntity

        setContent{
            ValorantAgentsGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgentDetailScreen(agent = agent)
                }
            }
        }
    }

    companion object {
        const val AGENT_DATA = "agent_data"
    }
}