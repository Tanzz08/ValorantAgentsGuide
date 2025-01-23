package com.example.valorantagentsguide.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.valorantagentsguide.data.local.entity.AgentsEntity

@Composable
fun AgentDetailScreen(
    agent: AgentsEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = agent.fullPortraitImg,
            contentScale = ContentScale.Fit,
            contentDescription = "Full portrait of ${agent.displayName}",
            modifier = Modifier
                .size(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = agent.displayName,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = agent.roleIcon,
                contentScale = ContentScale.Fit,
                contentDescription = "Role icon of ${agent.roleName}",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = agent.roleName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = agent.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Abilities",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))

        agent.abilities.forEach { ability ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        val painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(ability.displayIcon)
                                .build()
                        )
                        androidx.compose.foundation.Image(
                            painter = painter,
                            contentDescription = "Ability icon of ${ability.displayName}",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = ability.displayName,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = ability.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Start),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}