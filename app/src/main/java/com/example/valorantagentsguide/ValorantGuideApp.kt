package com.example.valorantagentsguide

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valorantagentsguide.data.local.entity.AgentsEntity
import com.example.valorantagentsguide.ui.common.Result
import com.example.valorantagentsguide.ui.component.AgentsList
import com.example.valorantagentsguide.ui.detail.DetailActivity
import com.example.valorantagentsguide.ui.profile.ProfileActivity
import com.example.valorantagentsguide.ui.screen.ErrorScreen
import com.example.valorantagentsguide.ui.screen.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValorantGuideApp(
    modifier: Modifier = Modifier,
    viewModel: ValorantGuideViewModel,
    activity: ComponentActivity
) {
    val agentState by viewModel.agents.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    when (val result = agentState) {
        is Result.Loading -> {
            LoadingScreen(modifier)
        }
        is Result.Success -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            SearchBar(
                                query = searchQuery,
                                onQueryChange = { searchQuery = it },
                                onSearch = { },
                                active = false,
                                onActiveChange = { },
                                placeholder = { Text(text = "Search agent...") },
                                modifier = Modifier.padding(16.dp)

                            ) { }
                        },

                        actions = {
                            IconButton(onClick = {navigateToProfile(activity)} ){
                                Icon(imageVector = Icons.Filled.Person, contentDescription = "about page")
                            }
                        }
                    )
                }

            ) { innerPadding ->
                val filteredCats = result.data.filter { agent ->
                    agent.displayName.contains(searchQuery, ignoreCase = true) ||
                            agent.description.contains(searchQuery, ignoreCase = true)
                }
                AgentsList(
                    agents = filteredCats,
                    modifier = modifier.padding(innerPadding),
                    navigateToDetail = { agent ->
                        viewModel.navigateToDetail(agent, activity)
                    }
                )
            }

        }
        is Result.Error -> {
            ErrorScreen(message = result.error, modifier)
        }
    }
}

fun navigateToProfile(activity: ComponentActivity){
    val intent = Intent(activity, ProfileActivity::class.java)
    activity.startActivity(intent)
}





