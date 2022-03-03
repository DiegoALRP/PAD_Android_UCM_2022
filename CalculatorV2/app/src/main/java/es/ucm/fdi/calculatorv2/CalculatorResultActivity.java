package es.ucm.fdi.calculatorv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
