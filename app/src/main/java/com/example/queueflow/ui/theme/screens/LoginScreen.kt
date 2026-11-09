
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
fun LoginScreen(
    onContinue: (String) -> Unit,
    onBack: () -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }

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
            text = "Welcome to QueueFlow",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Enter your phone number to continue.",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Phone Number",
            fontWeight = FontWeight.SemiBold,
            color = navy
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { input ->
                phoneNumber = input.filter { it.isDigit() }.take(10)
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color(0xFF102F67),
                fontSize = 16.sp),
            placeholder = {
                Text("3XX XXXXXXX", color = Color.Gray)
            },
            leadingIcon = {
                Text(
                    text = "+92",
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    color = navy
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "We'll send you a verification code.",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                onContinue("+92$phoneNumber")
            },
            enabled = phoneNumber.length == 10,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Send OTP",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
