
package com.example.queueflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queueflow.model.QueueToken
import com.example.queueflow.model.TokenStatus

@Composable
fun MyTokensScreen(
    tokens: List<QueueToken>,
    onBack: () -> Unit,
    onCancelToken: (String) -> Unit
) {
    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FC))
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Back", color = navy)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "My Tokens",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "View your active and previous queue tokens.",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        if (tokens.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "No tokens yet",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = navy
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your generated tokens will appear here.",
                        color = Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(
                    items = tokens,
                    key = { it.id }
                ) { token ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = token.tokenNumber,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = navy
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Branch: ${token.branchId}",
                                color = Color.DarkGray
                            )

                            Text(
                                text = "Service: ${token.serviceId}",
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Status: ${token.status.name.replace('_', ' ')}",
                                fontWeight = FontWeight.SemiBold,
                                color = when (token.status) {
                                    TokenStatus.COMPLETED -> Color(0xFF18864B)
                                    TokenStatus.EXPIRED,
                                    TokenStatus.CANCELLED,
                                    TokenStatus.NO_SHOW -> Color.Red
                                    else -> blue
                                }
                            )

                            if (
                                token.status == TokenStatus.WAITING_NOT_CHECKED_IN ||
                                token.status == TokenStatus.CHECKED_IN
                            ) {
                                Spacer(modifier = Modifier.height(12.dp))

                                OutlinedButton(
                                    onClick = {
                                        onCancelToken(token.id)
                                    }
                                ) {
                                    Text(
                                        "Cancel Token",
                                        color = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
