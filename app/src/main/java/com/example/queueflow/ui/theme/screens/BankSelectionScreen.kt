
package com.example.queueflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BankOption(
    val id: String,
    val name: String,
    val description: String
)

@Composable
fun BankSelectionScreen(
    onBack: () -> Unit,
    onBankSelected: (BankOption) -> Unit
) {
    val banks = listOf(
        BankOption(
            "BANK01",
            "City Trust Bank",
            "Reliable banking for everyone"
        ),
        BankOption(
            "BANK02",
            "National Finance Bank",
            "Your money, your future"
        ),
        BankOption(
            "BANK03",
            "People's Community Bank",
            "Banking made simple"
        )
    )

    var selectedBank by remember {
        mutableStateOf<BankOption?>(null)
    }

    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Choose Your Bank",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onBack) {
            Text(
                text = "← Back",
                fontSize = 16.sp,
                color = Color(0xFF2879F5)
            )
        }

        Text(
            text = "Select the bank you want to visit.",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(28.dp))

        banks.forEach { bank ->

            val isSelected = selectedBank?.id == bank.id

            Card(
                onClick = {
                    selectedBank = bank
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected)
                        Color(0xFFEAF2FF)
                    else
                        Color(0xFFF5F7FB)
                ),
                border = if (isSelected)
                    androidx.compose.foundation.BorderStroke(
                        2.dp,
                        blue
                    )
                else null
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🏦",
                        fontSize = 30.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = bank.name,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = navy
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = bank.description,
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }

                    RadioButton(
                        selected = isSelected,
                        onClick = {
                            selectedBank = bank
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = blue
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                selectedBank?.let(onBankSelected)
            },
            enabled = selectedBank != null,
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
