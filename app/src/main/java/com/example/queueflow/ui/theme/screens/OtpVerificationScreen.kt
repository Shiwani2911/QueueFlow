
package com.example.queueflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpVerificationScreen(
    phoneNumber: String,
    onVerify: () -> Unit,
    onBack: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val navy = Color(0xFF102F67)
    val blue = Color(0xFF2879F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = onBack) {
            Text("← Back", color = navy)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Verify Your Number",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enter the 6-digit verification code for $phoneNumber",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = {
                otp = it.filter { char -> char.isDigit() }.take(6)
                errorMessage = ""
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter 6-digit OTP") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = navy,
                fontSize = 20.sp
            ),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                if (otp == "123456") {
                    onVerify()
                } else {
                    errorMessage = "Invalid OTP. Try 123456."
                }
            },
            enabled = otp.length == 6,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                "Verify & Continue",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
