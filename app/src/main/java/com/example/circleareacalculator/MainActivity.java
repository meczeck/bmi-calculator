package com.example.circleareacalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //This referenced data type help to round numbers into 2 decimal places
    private static final DecimalFormat twoDpFormat = new DecimalFormat("0.00");
    private EditText editTextWeight;
    private EditText editTextHeight;
    private AlertDialog.Builder builder;
    private String bmiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To get EditText for Weight from xml
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        //To get EditText for Height from xml
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        //To get Button Component from xml
        Button calcButton = (Button) findViewById(R.id.calculateBmiButton);
        //To create Alert dialog object
        builder = new AlertDialog.Builder(this);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });
    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    public void calculateBmi() {
        try {
            if (editTextWeight.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), R.string.weight_is_null_message, Toast.LENGTH_SHORT).show();
            }
            else if (editTextHeight.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), R.string.height_is_null_message, Toast.LENGTH_SHORT).show();
            }
            else {
                //To convert editText values to integers
                Double userWeight = Double.parseDouble(editTextWeight.getText().toString());
                Double userHeight = Double.parseDouble(editTextHeight.getText().toString()) / 100;

                //To calculate Circle area
                Double bmiValue = userWeight / (userHeight * userHeight);

//To check the value of BMI
                if(bmiValue < 18.5) {
                    bmiResult = "Underweight";
                }
                else if(bmiValue >= 18.5 && bmiValue <= 24.9) {
                    bmiResult = "Healthy Weight";
                }
                else if(bmiValue >= 25 && bmiValue <= 29.9) {
                    bmiResult = "OverWeight";
                }
                else if(bmiValue >= 30 && bmiValue <= 34.9) {
                    bmiResult = "Obese";
                }
                else if(bmiValue >= 35 && bmiValue <= 39.9) {
                    bmiResult = "Severely Obese";
                }
                else if(bmiValue >= 40) {
                    bmiResult = "Morbidly Obese";
                }


                //Alert Dialog to display results
                builder.setMessage("BMI value is " + twoDpFormat.format(bmiValue) + " Kg/square metre" + "\r\n" + "Category : " + bmiResult)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                //Creating the dialog box
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.dialog_title);
                alert.show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.exception_error, Toast.LENGTH_SHORT).show();
        }
    }

}