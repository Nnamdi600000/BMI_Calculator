package com.CodeNnamdi.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // Class variable
    private TextView resultText;
    private RadioButton maleBtn;
    private RadioButton femaleBtn;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpButtonClickListener();
    }

    private void findViews(){
        resultText = findViewById(R.id.text_view_result);
        maleBtn = findViewById(R.id.radio_button_male);
        femaleBtn = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.age_id);
        feet = findViewById(R.id.feet_id);
        inches = findViewById(R.id.inches_id);
        weight = findViewById(R.id.weight_id);
        calculationBtn = findViewById(R.id.calculate_btn_id);
    }

    private void setUpButtonClickListener() {
        calculationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageText = age.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18){
                    displayResult(bmiResult);
                    Toast.makeText(MainActivity.this, "18 years or above", Toast.LENGTH_LONG).show();
                }else {
                    displayGuidance(bmiResult);
                    Toast.makeText(MainActivity.this, "Below 18", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private double calculateBmi() {

        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();

        // Converting Text string to Integers
       int feet = Integer.parseInt(feetText);
       int inches = Integer.parseInt(inchesText);
       int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        //The height in meters is the inches multiplied by 0.0254
        double heightToMeter = totalInches * 0.0254;

        //Bmi formula is the weight in kg divided by height to meter squared
        return weight / (heightToMeter * heightToMeter);
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi < 18.5){
            //Display you are underweight
            fullResultString = bmiTextResult + " You are underweight";
        }else if(bmi > 25){
            //Display you are overweight
            fullResultString = bmiTextResult + " You are overweight";
        }else {
            //Display you have a healthy weight
            fullResultString = bmiTextResult + " You have a healthy weight! Keep it up";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleBtn.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, Contact your doctor for the healthy range for boys";
        }else if(femaleBtn.isChecked()){
            //Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, Contact your doctor for the healthy range for girls";
        }else {
            //Display general guidance
            fullResultString = bmiTextResult + " As you are under 18, Contact your doctor for the healthy range";
        }
        resultText.setText(fullResultString);
    }

}