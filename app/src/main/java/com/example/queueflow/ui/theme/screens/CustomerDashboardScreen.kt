
package com.example.queueflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomerDashboardScreen(
    phoneNumber: String,
    onJoinQueue: () -> Unit,
    onMyTokens: () -> Unit,
    onLogout: () -> Unit
) {
    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FC))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "QueueFlow",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = navy
            )

            TextButton(onClick = onLogout) {
                Text("Logout", color = Color.Red)
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Welcome back!",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = phoneNumber,
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = navy
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Skip the waiting line!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Book your virtual token before visiting the bank.",
                    fontSize = 14.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = onJoinQueue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Join a Queue",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(
            onClick = onMyTokens,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "My Tokens",
                color = navy,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "How QueueFlow works",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text("1. Select your bank and branch")
        Spacer(modifier = Modifier.height(10.dp))
        Text("2. Choose the banking service")
        Spacer(modifier = Modifier.height(10.dp))
        Text("3. Generate your virtual token")
        Spacer(modifier = Modifier.height(10.dp))
        Text("4. Check in at the branch on time")
    }
}
