package com.example.valorantagentsguide.ui.component

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valorantagentsguide.data.local.entity.AgentsEntity
import com.example.valorantagentsguide.ui.theme.ValorantAgentsGuideTheme

@Composable
fun AgentsList(
    agents: List<AgentsEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(agents) { agent ->
            AgentsListItem(
                agent = agent
            )
        }
    }
}

@Composable
fun AgentsListItem(
    agent: AgentsEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ){
        AsyncImage(
            model = agent.displayIcon,
            contentScale = ContentScale.Fit,
            contentDescription = "Image of ${agent.displayName}",
            modifier = Modifier
                .size(170.dp)
        )
        Text(
            text = agent.displayName,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Row(
            modifier = modifier
        ) {
            AsyncImage(
                model = agent.roleIcon,
                contentScale = ContentScale.Fit,
                contentDescription = "Image of ${agent.roleName}",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Text(
                text = agent.roleName,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
        Text(
            text = agent.description,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
