package in.kaushalzod.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent fromMain = getIntent();
        String gender = fromMain.getStringExtra("gender");
        int age = fromMain.getIntExtra("age",0);
        double bmi = fromMain.getDoubleExtra("bmi",0.0);
        double weight = fromMain.getDoubleExtra("weight",0.0);
        double height = fromMain.getDoubleExtra("height",0.0);
        String[] bmiResult = interpretBMI(bmi);

        TextView bmiView = findViewById(R.id.bmi);
        TextView bmiDotView = findViewById(R.id.bmiDot);
        TextView bmiResultView = findViewById(R.id.bmiResult);
        TextView bmiDescView = findViewById(R.id.bmiDesc);
        bmiView.setText(String.valueOf((int) bmi));
        bmiDotView.setText(String.format("%.2f", bmi - (int) bmi).substring(1));
        bmiResultView.setText(bmiResult[0]);
        bmiResultView.setTextColor(Color.parseColor(bmiResult[2]));
        bmiDescView.setText(bmiResult[1]);

        Button goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(view -> finish());
    }

    public static String[] interpretBMI(double bmi) {
        // Interpret BMI based on general adult WHO guidelines
            if (bmi < 18.5) {
                return new String[]{"Underweight", "You need to gain some muscle mass to reach healthy BMI range","#FFFFC107"};
            } else if (bmi >= 18.5 && bmi < 24.9) {
                return new String[]{"Normal weight", "You are in healthy range of BMI, Well done","#FF4CAF50"};
            } else if (bmi >= 25 && bmi < 29.9) {
                return new String[]{"Overweight", "You are overweight as per your BMI, Consider loosing some FAT","#FFE91E63"};
            } else {
                return new String[]{"Obesity","You need to start your fitness journey before its too late.","#FFFB1100"};
            }
    }
}