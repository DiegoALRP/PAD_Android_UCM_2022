package es.ucm.fdi.calculatorv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private EditText editText_X;
    private EditText editText_Y;
    public static final String result = "es.ucm.fdi.calculator.result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        editText_X = findViewById(R.id.X);
        editText_Y = findViewById(R.id.Y);

        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addXandY(v);
            }
        });

    }

    private void addXandY(View view) {

        String x_value = editText_X.getText().toString();
        String y_value = editText_Y.getText().toString();

        if (verify_if_empty(x_value, y_value)) return;
        //if (veriy_is_not_number(x_value, y_value)) return;

        float x_number = Float.parseFloat(x_value);
        float y_number = Float.parseFloat(y_value);

        float sum_result = calculator.sum_2_num(x_number, y_number);

        String sum_result_string = String.valueOf(sum_result);

        //Error
        Log.e("In the case of error:", sum_result_string);
        //Warning
        Log.w("In the case of warning:", sum_result_string);
        //Information
        Log.i("In the case of information:", sum_result_string);
        //Debug
        Log.d("In the case of debug ", sum_result_string);
        //Verbose
        Log.v("In the case of verbose", sum_result_string);

        Intent intent = new Intent(this, CalculatorResultActivity.class);
        intent.putExtra(MainActivity.result, sum_result_string);
        startActivity(intent);
    }

    private boolean verify_if_empty(String x_value, String y_value) {

        boolean x_empty = x_value.isEmpty();
        boolean y_empty = y_value.isEmpty();

        if (x_empty) {
            editText_X.setError("Need to fill first field up");
            return true;
        }
        if (y_empty) {
            editText_Y.setError("Need to fill second field up");
            return true;
        }

        return false;
    }

    private boolean veriy_is_not_number(String x_value, String y_value) {

        int x_length = x_value.length();
        int y_length = y_value.length();
        int longest = x_length;
        boolean is_digit = true;

        if (y_length > x_length) {
            longest = y_length;
        }

        for (int i = 0; i < longest && is_digit; i++) {

            if (i < x_length) {
                if (!Character.isDigit(x_value.charAt(i))) {

                    is_digit = false;
                    editText_X.setError("First field is not a number");
                }
            }
            if (i < y_length) {
                if (!Character.isDigit(y_value.charAt(i))) {

                    is_digit = false;
                    editText_Y.setError("Second filed is not a number");
                }
            }
        }

        return is_digit;
    }
}