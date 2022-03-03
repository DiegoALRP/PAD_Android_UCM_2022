package es.ucm.fdi.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorResultActivity extends AppCompatActivity {

    private TextView calculatorResultActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_result_activity);
        calculatorResultActivity = findViewById(R.id.result_textView);
    }

    @Override
    protected void onStart() {
        super.onStart();


        Intent intent = getIntent();
        String result = intent.getStringExtra(MainActivity.result);
        calculatorResultActivity.setText(result);
    }
}
