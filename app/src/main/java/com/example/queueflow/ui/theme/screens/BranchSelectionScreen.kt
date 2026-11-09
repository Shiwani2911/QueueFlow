
package com.example.queueflow.ui.screens

import androidx.compose.foundation.BorderStroke
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
import com.example.queueflow.model.BankBranch
import androidx.compose.foundation.background

@Composable
fun BranchSelectionScreen(
    bankId: String,
    bankName: String,
    onBack: () -> Unit,
    onBranchSelected: (BankBranch) -> Unit
) {
    val branches = when (bankId) {
        "BANK01" -> listOf(
            BankBranch(
                id = "BR01",
                bankId = "BANK01",
                branchName = "Sukkur Main Branch",
                address = "Military Road, Sukkur"
            ),
            BankBranch(
                id = "BR02",
                bankId = "BANK01",
                branchName = "Rohri Branch",
                address = "Station Road, Rohri"
            ),
            BankBranch(
                id = "BR03",
                bankId = "BANK01",
                branchName = "Airport Road Branch",
                address = "Airport Road, Sukkur"
            )
        )

        "BANK02" -> listOf(
            BankBranch(
                id = "BR04",
                bankId = "BANK02",
                branchName = "Sukkur City Branch",
                address = "Shahi Bazaar, Sukkur"
            ),
            BankBranch(
                id = "BR05",
                bankId = "BANK02",
                branchName = "Rohri Main Branch",
                address = "Main Road, Rohri"
            )
        )

        "BANK03" -> listOf(
            BankBranch(
                id = "BR06",
                bankId = "BANK03",
                branchName = "Sukkur Central Branch",
                address = "Queens Road, Sukkur"
            ),
            BankBranch(
                id = "BR07",
                bankId = "BANK03",
                branchName = "Airport Branch",
                address = "Airport Road, Sukkur"
            )
        )

        else -> emptyList()
    }

    var selectedBranch by remember(bankId) {
        mutableStateOf<BankBranch?>(null)
    }

    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Choose Your Branch",
            fontSize = 27.sp,
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
            text = "Select a branch of $bankName.",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${branches.size} branches available",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = navy
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 12.dp)
        ) {
            items(branches) { branch ->

                val isSelected = selectedBranch?.id == branch.id

                Card(
                    onClick = {
                        selectedBranch = branch
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
                            text = "📍",
                            fontSize = 26.sp
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = branch.branchName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = navy
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = branch.address,
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }

                        RadioButton(
                            selected = isSelected,
                            onClick = {
                                selectedBranch = branch
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
                selectedBranch?.let(onBranchSelected)
            },
            enabled = selectedBranch != null,
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
