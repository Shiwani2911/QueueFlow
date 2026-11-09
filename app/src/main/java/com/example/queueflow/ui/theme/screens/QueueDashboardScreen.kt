
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
fun QueueDashboardScreen(
    bankName: String,
    branchName: String,
    serviceName: String,
    onBack: () -> Unit,
    onJoinQueue: () -> Unit
) {
    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Back", color = blue, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Queue Dashboard",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Text(
            text = "Check the queue before joining.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = bankName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Text(
            text = branchName,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF2FF)
            )
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "SELECTED SERVICE",
                    fontSize = 12.sp,
                    color = blue,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = serviceName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = navy
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QueueInfoCard(
                title = "NOW SERVING",
                value = "W012",
                modifier = Modifier.weight(1f)
            )

            QueueInfoCard(
                title = "PEOPLE WAITING",
                value = "8",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        QueueInfoCard(
            title = "ESTIMATED WAIT TIME",
            value = "40 minutes",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "These are demo queue figures. Your token will be generated in the next step.",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onJoinQueue,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            )
        ) {
            Text(
                text = "Get My Token",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun QueueInfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F6FA)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF102F67)
            )
        }
    }
}
