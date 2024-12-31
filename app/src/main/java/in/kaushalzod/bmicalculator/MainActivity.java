package in.kaushalzod.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {
    EditText ageInput;
    EditText weightInput;
    EditText heightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTouchListener();
        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput= findViewById(R.id.heightInput);

        MaterialButtonToggleGroup toggleButton;
        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if(isChecked) {
                Button btn = findViewById(checkedId);
                Log.i("Toggle", btn.getText().toString());
            }
        });

        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(view -> {
           int btnId= toggleButton.getCheckedButtonId();
           Button checkButton = findViewById(btnId);
            int age = Integer.parseInt( ageInput.getText().toString());
            double weight = Double.parseDouble( weightInput.getText().toString());
            double height = Double.parseDouble( heightInput.getText().toString());
            String gender =checkButton.getText().toString();
            double bmi =  calculateBMI(weight,height);

            Intent resultIntent = new Intent(MainActivity.this, Result.class);
            resultIntent.putExtra("weight",weight);
            resultIntent.putExtra("age",age);
            resultIntent.putExtra("height",height);
            resultIntent.putExtra("gender",gender);
            resultIntent.putExtra("bmi",bmi);
            startActivity(resultIntent);
        });
    }

    public double calculateBMI(double weightKg, double heightCm) {
        // Convert height to meters
        double heightMeters = heightCm / 100.0;

        // Calculate BMI
        return weightKg / (heightMeters * heightMeters);
    }

    public void addAge(View v){
       ageInput = findViewById(R.id.ageInput);
      String ageString = ageInput.getText().toString();
      int age = Integer.parseInt(ageString);
      ageInput.setText(String.valueOf(age+1));
    }
    public void subAge(View v){
        ageInput =findViewById(R.id.ageInput);
        String ageString = ageInput.getText().toString();
        int age = Integer.parseInt(ageString);
        if(age!=18) ageInput.setText(String.valueOf(age-1));
    }
    public void addWeight(View v){
        weightInput = findViewById(R.id.weightInput);
        String weightString = weightInput.getText().toString();
        int weight = Integer.parseInt(weightString);
        weightInput.setText(String.valueOf(weight+1));
    }
    public void subWeight(View v){
        weightInput = findViewById(R.id.weightInput);
        String weightString = weightInput.getText().toString();
        int weight = Integer.parseInt(weightString);
        if(weight!=0) weightInput.setText(String.valueOf(weight-1));
    }
    public void addHeight(View v){
       heightInput= findViewById(R.id.heightInput);
        String heightString = heightInput.getText().toString();
        int height = Integer.parseInt(heightString);
        heightInput.setText(String.valueOf(height+1));
    }
    public void subHeight(View v){
       heightInput= findViewById(R.id.heightInput);
        String heightString = heightInput.getText().toString();
        int height = Integer.parseInt(heightString);
        if(height!=0) heightInput.setText(String.valueOf(height-1));
    }

    private void setUpTouchListener() {
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    if (getCurrentFocus() != null) {
                        getCurrentFocus().clearFocus();
                    }
                }
                return false;
            }
        });
    }
}