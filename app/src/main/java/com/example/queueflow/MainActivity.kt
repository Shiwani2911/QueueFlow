package com.example.queueflow

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queueflow.Queue.QueueManager
import com.example.queueflow.model.BankService
import com.example.queueflow.model.QueueToken
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.queueflow.ui.screens.WelcomeScreen
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.queueflow.ui.screens.BankSelectionScreen
import com.example.queueflow.ui.screens.BranchSelectionScreen
import com.example.queueflow.ui.screens.ServiceSelectionScreen
import androidx.activity.compose.BackHandler
import com.example.queueflow.ui.screens.LoginScreen
import com.example.queueflow.ui.screens.QueueDashboardScreen
import com.example.queueflow.ui.screens.TokenScreen
import com.example.queueflow.ui.screens.OtpVerificationScreen
import com.example.queueflow.ui.theme.QueueFlowTheme
import com.example.queueflow.ui.screens.MyTokensScreen
import com.example.queueflow.ui.screens.CustomerDashboardScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class QueueService(
    val name: String,
    val description: String,
    val emoji: String,
    val prefix: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            QueueFlowTheme {

                var currentScreen by rememberSaveable {
                    mutableStateOf("welcome")
                }

                var tokenRefresh by remember {
                    mutableIntStateOf(0)
                }

                var customerPhone by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedBankName by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedBankId by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedBranchName by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedBranchId by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedServiceId by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedServiceName by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedServicePrefix by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedServiceMinutes by rememberSaveable {
                    mutableIntStateOf(0)
                }

                var generatedTokenNumber by rememberSaveable {
                    mutableStateOf("")
                }

                val queueManager = remember {
                    QueueManager()
                }

                var generatedToken by remember {
                    mutableStateOf<QueueToken?>(null)
                }

                BackHandler(enabled = currentScreen != "welcome") {
                    currentScreen = when (currentScreen) {
                        "login" -> "welcome"
                        "otp" -> "login"
                        "customer-dashboard" -> "welcome"
                        "bank" -> "welcome"
                        "branch" -> "bank"
                        "branch-confirmation" -> "branch"
                        "service" -> "branch"
                        "service-confirmation" -> "service"
                        "queue-dashboard" -> "service"
                        "token" -> "queue-dashboard"
                        "my-tokens" -> "customer-dashboard"
                        else -> "welcome"
                    }
                }

                when (currentScreen) {

                    "welcome" -> WelcomeScreen(
                        onGetStarted = {
                            currentScreen = "login"
                        }
                    )

                    "login" -> LoginScreen(
                        onBack = {
                            currentScreen = "welcome"
                        },
                        onContinue = { phone ->
                            customerPhone = phone
                            currentScreen = "otp"
                        }
                    )

                    "otp" -> OtpVerificationScreen(
                        phoneNumber = customerPhone,
                        onBack = {
                            currentScreen = "login"
                        },
                        onVerify = {
                            currentScreen = "customer-dashboard"
                        }
                    )

                    "customer-dashboard" -> CustomerDashboardScreen(
                        phoneNumber = customerPhone,
                        onJoinQueue = {
                            currentScreen = "bank"
                        },
                        onMyTokens = {
                            currentScreen = "my-tokens"
                        },
                        onLogout = {
                            customerPhone = ""
                            currentScreen = "welcome"
                        }
                    )

                    "my-tokens" -> MyTokensScreen(
                        tokens = remember(tokenRefresh) {
                            queueManager.getAllTokens().filter {
                                it.customerId == "CUSTOMER001"
                            }
                        },
                        onBack = {
                            currentScreen = "customer-dashboard"
                        },
                        onCancelToken = { tokenId ->
                            if (queueManager.cancelToken(tokenId)) {
                                tokenRefresh++
                            }
                        }
                    )

                    "bank" -> {
                        BankSelectionScreen(
                            onBack = {
                                currentScreen = "welcome"
                            },
                            onBankSelected = { bank ->
                                selectedBankName = bank.name
                                selectedBankId = bank.id
                                currentScreen = "branch"
                            }
                        )
                    }

                    "branch" -> {
                        BranchSelectionScreen(
                            bankId = selectedBankId,
                            bankName = selectedBankName,
                            onBack = {
                                currentScreen = "bank"
                            },
                            onBranchSelected = { branch ->
                                selectedBranchId = branch.id
                                selectedBranchName = branch.branchName
                                currentScreen = "service"
                            }
                        )
                    }

                    "branch-confirmation" -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Branch Selected!",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(text = selectedBankName)

                            Text(text = selectedBranchName)

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = {
                                    currentScreen = "branch"
                                }
                            ) {
                                Text("Back to Branches")
                            }
                        }

                    }
                    "service" -> {
                        ServiceSelectionScreen(
                            branchId = selectedBranchId,
                            branchName = selectedBranchName,
                            onBack = {
                                currentScreen = "branch"
                            },
                            onServiceSelected = { service ->
                                selectedServiceId = service.id
                                selectedServiceName = service.serviceName
                                selectedServicePrefix = service.prefix
                                selectedServiceMinutes = service.estimatedServiceMinutes
                                currentScreen = "queue-dashboard"
                            }
                        )
                    }
                    "queue-dashboard" -> QueueDashboardScreen(
                        bankName = selectedBankName,
                        branchName = selectedBranchName,
                        serviceName = selectedServiceName,
                        onBack = {
                            currentScreen = "service"
                        },
                        onJoinQueue = {
                            val service = BankService(
                                id = selectedServiceId,
                                branchId = selectedBranchId,
                                serviceName = selectedServiceName,
                                prefix = selectedServicePrefix,
                                estimatedServiceMinutes = selectedServiceMinutes
                            )

                            generatedToken = queueManager.generateToken(
                                customerId = "CUSTOMER001",
                                service = service
                            )

                            currentScreen = "token"
                        }
                    )

                    "service-confirmation" -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Service Selected!",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(text = "Bank: $selectedBankName")
                            Text(text = "Branch: $selectedBranchName")
                            Text(text = "Service: $selectedServiceName")

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Estimated service time: $selectedServiceMinutes minutes"
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = {
                                    currentScreen = "service"
                                }
                            ) {
                                Text("Back to Services")
                            }
                        }
                    }

                    "token" -> generatedToken?.let { token ->

                        TokenScreen(
                            tokenNumber = token.tokenNumber,
                            serviceName = selectedServiceName,
                            branchName = selectedBranchName,
                            onBack = {
                                currentScreen = "queue-dashboard"
                            }
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun QueueHomeScreen() {

    val services = listOf(
        QueueService(
            "Cash Withdrawal",
            "Withdraw money from your account",
            "💵",
            "W"
        ),
        QueueService(
            "Cash Deposit",
            "Deposit money into your account",
            "💳",
            "D"
        ),
        QueueService(
            "Account Services",
            "Account information and other services",
            "🏦",
            "A"
        )
    )

    val queueManager = remember {
        QueueManager()
    }

    var selectedServiceName by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var generatedToken by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var currentQueueToken by remember {
        mutableStateOf<QueueToken?>(null)
    }

    val selectedService = services.firstOrNull {
        it.name == selectedServiceName
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            if (generatedToken == null) {

                // HOME SCREEN

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "Skip the line.\nSave your time.",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Get your bank queue token digitally before arriving at the branch."
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Welcome!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Select a banking service to continue.",
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Available Services",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    services.forEach { service ->

                        val isSelected =
                            selectedService == service

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .clickable {
                                    selectedServiceName = service.name
                                },
                            shape = RoundedCornerShape(18.dp),
                            border = if (isSelected) {
                                BorderStroke(
                                    2.dp,
                                    MaterialTheme.colorScheme.primary
                                )
                            } else {
                                null
                            },
                            colors = CardDefaults.cardColors(
                                containerColor =
                                    MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Text(
                                    text = service.emoji,
                                    fontSize = 26.sp
                                )

                                Spacer(
                                    modifier = Modifier.width(16.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = service.name,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = service.description,
                                        fontSize = 12.sp
                                    )
                                }

                                RadioButton(
                                    selected = isSelected,
                                    onClick = {
                                        selectedServiceName =
                                            service.name
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {

                            selectedService?.let { service ->

                                val bankService = BankService(
                                    id = service.prefix,
                                    branchId = "BR01",
                                    serviceName = service.name,
                                    prefix = service.prefix,
                                    estimatedServiceMinutes = 5
                                )

                                val token =
                                    queueManager.generateToken(
                                        customerId = "CUSTOMER001",
                                        service = bankService
                                    )

                                currentQueueToken = token
                                generatedToken = token.tokenNumber
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = selectedService != null,
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Text(
                            text = "Get Virtual Token",
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Remember to check in at the branch before your deadline.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }

            } else {

                // TOKEN CONFIRMATION SCREEN

                val token = currentQueueToken

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        text = "✓",
                        fontSize = 48.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Your Virtual Token",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Your queue token has been generated.",
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(28.dp),
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "YOUR TOKEN NUMBER",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = generatedToken ?: "",
                                fontSize = 42.sp,
                                fontWeight = FontWeight.Bold,
                                color =
                                    MaterialTheme.colorScheme.primary
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = selectedService?.name ?: ""
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "Token Information",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            Text("Branch: Sukkur Main Branch")

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = "Status: ${
                                    token?.status?.name
                                        ?.replace("_", " ")
                                }"
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = "Check-in deadline: ${
                                    token?.let {
                                        SimpleDateFormat(
                                            "hh:mm a",
                                            Locale.getDefault()
                                        ).format(
                                            Date(it.checkInDeadline)
                                        )
                                    } ?: ""
                                }"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Important: Visit the selected branch and check in before your deadline. Otherwise, your token will expire and you will need to request a new one.",
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {
                            generatedToken = null
                            selectedServiceName = null
                            currentQueueToken = null
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Back to Home")
                    }
                }
            }
        }
    }
}
