// Rishabh Sahu
// Assignment # 1
package com.example.cs4520_inclass_rishabh0516;

import static java.lang.Math.floor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class InClass01 extends AppCompatActivity {
    private Button button_bmi;
    private EditText weightText;
    private EditText feetText;
    private EditText inchText;

    public static String TAG = "demo2";

    private TextView BMIText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class1);

        setTitle("BMI Calculator");
        button_bmi = findViewById(R.id.button_bmi);
        weightText = findViewById(R.id.weightText);
        feetText = findViewById(R.id.feetText);
        inchText = findViewById(R.id.inchText);
        BMIText = findViewById(R.id.BMIText);

        button_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(weightText.getText().toString().equals("")
                        || feetText.getText().toString().equals("")
                        || inchText.getText().toString().equals("")) {
                    Toast.makeText(InClass01.this, "Please fill in all the inputs",Toast.LENGTH_LONG).show();
                }

                int weight = Integer.parseInt(weightText.getText().toString());
                int feet = Integer.parseInt(feetText.getText().toString());
                int inches = Integer.parseInt(inchText.getText().toString());

                if(weight == 0) {
                    Toast.makeText(InClass01.this, "Invalid Weight!",Toast.LENGTH_LONG).show();
                }


                int totalInches = (12 * feet) + inches;
                DecimalFormat df1 = new DecimalFormat("0.##");
                double BMI = ((weight * 1.0)/(totalInches * totalInches)) * 703;
                BMI = Double.parseDouble(df1.format(BMI));

                if(BMI < 18.5) {
                    BMIText.setText("Your BMI: " + BMI + " You are underweight");
                }

                if(BMI >= 18.5 && BMI <= 24.9) {
                    BMIText.setText("Your BMI: " + BMI + " You are normal weight");
                }

                if(BMI >= 25 && BMI <= 29.9) {
                    BMIText.setText("Your BMI: " + BMI + " You are overweight");
                }

                if(BMI >= 30) {
                    BMIText.setText("Your BMI: " + BMI + " You are obese");
                }


            }
        });
    }
}