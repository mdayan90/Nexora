package com.example.nexora.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.nexora.ui.theme.*

@Composable
fun UserDashboard() {
    var isOnline by remember { mutableStateOf(true) }
    var showStatusDialog by remember { mutableStateOf(false) }

    if (showStatusDialog) {
        AlertDialog(
            onDismissRequest = { showStatusDialog = false },
            containerColor = Color(0xFF1B2414),
            title = {
                Text(
                    text = if (isOnline) "Go Offline?" else "Go Online?",
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = if (isOnline) 
                        "You will stop receiving real-time visitor alerts." 
                        else "You will start receiving visitor notifications.",
                    color = White.copy(alpha = 0.7f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isOnline = !isOnline
                        showStatusDialog = false
                    }
                ) {
                    Text("Confirm", color = LimeGreen, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showStatusDialog = false }) {
                    Text("Cancel", color = White.copy(alpha = 0.5f))
                }
            },
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 8.dp
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        // dynamic background glows
        Box(
            modifier = Modifier
                .size(400.dp)
                .offset(x = (-150).dp, y = (-50).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(LimeGreen.copy(alpha = 0.12f), Color.Transparent)
                    ),
                    CircleShape
                )
                .blur(60.dp)
        )
        
        Box(
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 120.dp, y = 100.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(LimeGreen.copy(alpha = 0.08f), Color.Transparent)
                    ),
                    CircleShape
                )
                .blur(80.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))
                
                // Header
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Dashboard",
                            color = LimeGreen,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-1).sp
                        )
                        Text(
                            text = "Welcome back, Ayan",
                            color = White.copy(alpha = 0.7f),
                            fontSize = 16.sp
                        )
                    }
                    
                    Row(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Online/Offline Toggle
                        Surface(
                            onClick = { showStatusDialog = true },
                            shape = RoundedCornerShape(20.dp),
                            color = White.copy(alpha = 0.08f),
                            border = BorderStroke(1.dp, White.copy(alpha = 0.1f)),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(
                                            if (isOnline) LimeGreen else Color.Red,
                                            CircleShape
                                        )
                                )
                                Text(
                                    text = if (isOnline) "Online" else "Offline",
                                    color = White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Surface(
                            onClick = { /* TODO */ },
                            modifier = Modifier.size(48.dp),
                            shape = CircleShape,
                            color = White.copy(alpha = 0.08f),
                            border = BorderStroke(1.dp, White.copy(alpha = 0.1f))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Notifications, contentDescription = null, tint = White, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                // Bento Grid
                BentoGrid()
            }

            item {
                Spacer(modifier = Modifier.height(120.dp)) // Extra space for floating nav
            }
        }
    }
}

@Composable
fun BentoGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        // Featured Visitor Card
        GlassCard(
            modifier = Modifier.fillMaxWidth().height(220.dp),
            title = "Visitor Traffic",
            value = "1,284",
            trend = "+24% this month",
            icon = Icons.Default.History,
            isFeatured = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GlassCard(
                modifier = Modifier.weight(1f).height(180.dp),
                title = "Society Rules & Regulation",
                value = "Guidelines",
                icon = Icons.Default.Settings,
                accentColor = LimeGreen
            )
            GlassCard(
                modifier = Modifier.weight(1f).height(180.dp),
                title = "Raise Complaint",
                value = "Report Now",
                icon = Icons.Default.Add
            )
        }

        Text(
            text = "Pending Visitors",
            color = White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        // Expandable Visitor Cards
        VisitorGlassCard(
            name = "Aryan Sharma",
            status = "Pending",
            purpose = "UI Design Discussion",
            time = "02:30 PM"
        )
        
        VisitorGlassCard(
            name = "Simran Kaur",
            status = "Pending",
            purpose = "Document Verification",
            time = "03:15 PM"
        )
        
        VisitorGlassCard(
            name = "Rahul Verma",
            status = "Pending",
            purpose = "Maintenance Work",
            time = "04:00 PM"
        )
    }
}

@Composable
fun VisitorGlassCard(
    name: String,
    status: String,
    purpose: String,
    time: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showFullScreenImage by remember { mutableStateOf(false) }

    if (showFullScreenImage) {
        Dialog(onDismissRequest = { showFullScreenImage = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showFullScreenImage = false },
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.size(280.dp),
                    shape = CircleShape,
                    color = Color(0xFF1B2414),
                    border = BorderStroke(2.dp, LimeGreen)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(150.dp)
                        )
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        White.copy(alpha = 0.07f),
                        White.copy(alpha = 0.03f)
                    )
                )
            )
            .border(
                BorderStroke(1.dp, White.copy(alpha = 0.1f)),
                RoundedCornerShape(28.dp)
            )
            .clickable { isExpanded = !isExpanded }
            .animateContentSize()
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Image Placeholder
                Surface(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { showFullScreenImage = true },
                    shape = CircleShape,
                    color = White.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = LimeGreen, modifier = Modifier.size(26.dp))
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = name, color = White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).background(Color(0xFFFFD700), CircleShape))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = status, color = Color(0xFFFFD700).copy(alpha = 0.9f), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }
                
                Icon(
                    imageVector = if (isExpanded) Icons.Default.Remove else Icons.Default.Add,
                    contentDescription = null,
                    tint = White.copy(alpha = 0.2f),
                    modifier = Modifier.size(24.dp)
                )
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(20.dp))
                Divider(color = White.copy(alpha = 0.05f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = "PURPOSE", color = White.copy(alpha = 0.4f), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Text(text = purpose, color = White, fontSize = 15.sp)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "ENTRY TIME", color = White.copy(alpha = 0.4f), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Text(text = time, color = White, fontSize = 15.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { /* Accept */ },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LimeGreen),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Accept", color = Black, fontWeight = FontWeight.ExtraBold)
                    }
                    
                    OutlinedButton(
                        onClick = { /* Reject */ },
                        modifier = Modifier.weight(1f).height(48.dp),
                        border = BorderStroke(1.dp, Color.Red.copy(alpha = 0.4f)),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                    ) {
                        Text("Reject", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    trend: String? = null,
    icon: ImageVector,
    accentColor: Color = White,
    isFeatured: Boolean = false
) {
    Box(
        modifier = modifier

            .clip(RoundedCornerShape(32.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        White.copy(alpha = 0.06f),
                        White.copy(alpha = 0.02f)
                    )
                )
            )
            .border(
                BorderStroke(
                    1.dp,
                    Brush.verticalGradient(
                        colors = listOf(
                            White.copy(alpha = 0.15f),
                            White.copy(alpha = 0.05f)
                        )
                    )
                ),
                RoundedCornerShape(32.dp)
            )
            .padding(24.dp)
    ) {
        Column {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (accentColor == LimeGreen) LimeGreen else White.copy(alpha = 0.4f),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = title, color = White.copy(alpha = 0.5f), fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(
                text = value,
                color = if (accentColor == LimeGreen) LimeGreen else White,
                fontSize = if (isFeatured) 36.sp else 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            )
            if (trend != null) {
                Text(
                    text = trend,
                    color = LimeGreen.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick,
        color = Color.Transparent,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) LimeGreen else Color(0xFF81C784).copy(alpha = 0.3f),
                modifier = Modifier.size(26.dp)
            )
            Text(
                text = label,
                color = if (isSelected) LimeGreen else Color(0xFF81C784).copy(alpha = 0.3f),
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun UserDashboardPreview() {
    NexoraTheme {
        UserDashboard()
    }
}
