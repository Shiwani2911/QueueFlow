
package com.example.queueflow.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queueflow.model.BankService

@Composable
fun ServiceSelectionScreen(
    branchId: String,
    branchName: String,
    onBack: () -> Unit,
    onServiceSelected: (BankService) -> Unit
) {
    val services = listOf(
        BankService(
            id = "SERVICE01",
            branchId = branchId,
            serviceName = "Cash Withdrawal",
            prefix = "W",
            estimatedServiceMinutes = 5
        ),
        BankService(
            id = "SERVICE02",
            branchId = branchId,
            serviceName = "Cash Deposit",
            prefix = "D",
            estimatedServiceMinutes = 4
        ),
        BankService(
            id = "SERVICE03",
            branchId = branchId,
            serviceName = "Account Services",
            prefix = "A",
            estimatedServiceMinutes = 10
        )
    )

    var selectedService by remember(branchId) {
        mutableStateOf<BankService?>(null)
    }

    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 24.dp)
    ) {

        TextButton(onClick = onBack) {
            Text(
                text = "← Back",
                fontSize = 16.sp,
                color = Color(0xFF2879F5)
            )
        }

        Text(
            text = "Choose a Service",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "What would you like to do today?",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF2FF)
            )
        ) {
            Column(
                modifier = Modifier.padding(14.dp)
            ) {
                Text(
                    text = "Selected Branch",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = branchName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = navy
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Available Services",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = navy
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 12.dp)
        ) {
            items(services) { service ->

                val isSelected =
                    selectedService?.id == service.id

                val serviceEmoji = when (service.prefix) {
                    "W" -> "💵"
                    "D" -> "💳"
                    else -> "🏦"
                }

                Card(
                    onClick = {
                        selectedService = service
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected)
                            Color(0xFFEAF2FF)
                        else
                            Color(0xFFF5F6F8)
                    ),
                    border = if (isSelected)
                        BorderStroke(2.dp, blue)
                    else
                        null
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = serviceEmoji,
                            fontSize = 30.sp
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = service.serviceName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = navy
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Estimated service time: ${service.estimatedServiceMinutes} min",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }

                        RadioButton(
                            selected = isSelected,
                            onClick = {
                                selectedService = service
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = blue
                            )
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                selectedService?.let(onServiceSelected)
            },
            enabled = selectedService != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            )
        ) {
            Text(
                text = "Continue",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
