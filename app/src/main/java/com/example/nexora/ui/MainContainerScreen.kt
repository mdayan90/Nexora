package com.example.nexora.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexora.ui.theme.NexoraTheme
import com.example.nexora.ui.theme.White

@Composable
fun MainContainerScreen(onLogout: () -> Unit) {
    var currentTab by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .height(84.dp)
                        .clip(RoundedCornerShape(42.dp)),
                    color = Color(0xFF1B2414).copy(alpha = 0.85f),
                    border = BorderStroke(1.dp, White.copy(alpha = 0.08f))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NavigationItem(
                            icon = Icons.Default.Home,
                            label = "Home",
                            isSelected = currentTab == 0,
                            modifier = Modifier.weight(1f),
                            onClick = { currentTab = 0 }
                        )
                        NavigationItem(
                            icon = Icons.Default.History,
                            label = "Log",
                            isSelected = currentTab == 1,
                            modifier = Modifier.weight(1f),
                            onClick = { currentTab = 1 }
                        )

                        NavigationItem(
                            icon = Icons.Default.Person,
                            label = "Me",
                            isSelected = currentTab == 3,
                            modifier = Modifier.weight(1f),
                            onClick = { currentTab = 3 }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentTab) {
                0 -> UserDashboard()
                1 -> Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) { Text("Logs coming soon", color = Color.White) }
                2 -> Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) { Text("Reviews coming soon", color = Color.White) }
                3 -> UserProfile(onLogout = onLogout)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContainerScreenPreview() {
    NexoraTheme {
        MainContainerScreen(onLogout = {})
    }
}
