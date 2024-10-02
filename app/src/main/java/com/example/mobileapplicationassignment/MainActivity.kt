package com.example.mobileapplicationassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobileapplicationassignment.ui.theme.MobileApplicationAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileApplicationAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EMI_Calculator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EMI_Calculator(modifier: Modifier = Modifier) {
    var mortgageAmount by remember { mutableStateOf("") }
    var tenure by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var emiResult by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = mortgageAmount,
            onValueChange = { mortgageAmount = it },
            label = { Text("Principle") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = tenure,
            onValueChange = { tenure = it },
            label = { Text("Tenure (Years)") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = interestRate,
            onValueChange = { interestRate = it },
            label = { Text("Interest Rate (%)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            emiResult = calculateEMI(
                mortgageAmount.toDoubleOrNull() ?: 0.0,
                tenure.toDoubleOrNull() ?: 0.0,
                interestRate.toDoubleOrNull() ?: 0.0
            )
        }) {
            Text("Calculate EMI")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = emiResult)
    }
}

fun calculateEMI(principal: Double, tenureInYears: Double, annualRate: Double): String {
    if (principal == 0.0 || tenureInYears == 0.0 || annualRate == 0.0) {
        return "Please enter valid values."
    }
    val rateInMonths = annualRate / 12 / 100
    val emi = principal * (rateInMonths * Math.pow(1 + rateInMonths, tenureInYears * 12)) /
            (Math.pow(1 + rateInMonths, tenureInYears * 12) - 1)
    return "Monthly EMI: $${String.format("%.2f", emi)}"
}
