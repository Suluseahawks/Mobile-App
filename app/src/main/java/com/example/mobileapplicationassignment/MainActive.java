package com.example.mobileapplicationassignment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActive extends AppCompatActivity {

    private EditText Principle;
    private EditText tenure;
    private EditText interestrate;
    private Button calculateButton;
    private TextView emiResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Ensure the correct layout file is being used
        setContentView(R.layout.activity_main1); // Ensure this matches your XML file name

        Principle = findViewById(R.id.mortgageAmount);
        tenure = findViewById(R.id.tenure);
        interestrate = findViewById(R.id.interestRate);
        calculateButton = findViewById(R.id.calculateButton);
        emiResult = findViewById(R.id.emiResult);

        calculateButton.setOnClickListener(v -> {
            calculateEMI();
        });
    }

    public void calculateEMI() {
        if (Principle.getText().toString().isEmpty() ||
                tenure.getText().toString().isEmpty() ||
                interestrate.getText().toString().isEmpty()) {
            emiResult.setText("Please enter valid values.");
            return;
        }
        double principal = Double.parseDouble(Principle.getText().toString());
        double tenureInYears = Double.parseDouble(tenure.getText().toString());
        double rateInMonths = Double.parseDouble(interestrate.getText().toString()) / 12 / 100;
        double emi = principal * (rateInMonths * Math.pow(1 + rateInMonths, tenureInYears * 12)) /
                (Math.pow(1 + rateInMonths, tenureInYears * 12) - 1);
        emiResult.setText("Monthly EMI: $" + String.format("%.2f", emi));
    }
}
