package com.example.queueflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit
) {
    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)
    val mint = Color(0xFFA8E8D0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEAF2FF),
                        Color.White,
                        Color(0xFFE8F8F3)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // Logo and app name
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(105.dp)
                        .background(navy, RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Q",
                        fontSize = 58.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = mint
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "QueueFlow",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = navy
                )

                Text(
                    text = "Smart Queues. Less Waiting.",
                    fontSize = 15.sp,
                    color = Color(0xFF60718B)
                )
            }

            // Welcome message
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your time matters!",
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold,
                    color = navy,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Skip the long lines. Get your virtual token and manage your bank visit with ease.",
                    fontSize = 16.sp,
                    color = Color(0xFF60718B),
                    textAlign = TextAlign.Center,
                    lineHeight = 25.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Decorative queue illustration
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf(
                        Color(0xFF2879F5),
                        Color(0xFF8B83F6),
                        Color(0xFFA8E8D0)
                    ).forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(color, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "•",
                                fontSize = 26.sp,
                                color = navy,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Get Started button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onGetStarted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue
                    )
                ) {
                    Text(
                        text = "Let's Get Started",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "A smarter way to visit your bank.",
                    fontSize = 12.sp,
                    color = Color(0xFF8190A5)
                )
            }
        }
    }
}
