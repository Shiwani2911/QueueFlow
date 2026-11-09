
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
fun TokenScreen(
    tokenNumber: String,
    serviceName: String,
    branchName: String,
    onBack: () -> Unit
) {
    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("← Back", color = blue)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "You're in the queue!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your virtual token has been generated.",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF2FF)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "YOUR TOKEN NUMBER",
                    color = blue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = tokenNumber,
                    color = navy,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text("Service: $serviceName", color = navy)
                Text("Branch: $branchName", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Important: Visit your selected branch and check in before your 30-minute deadline. Your token may expire if you do not check in.",
            color = Color.DarkGray,
            fontSize = 14.sp,
            lineHeight = 21.sp
        )
    }
}
