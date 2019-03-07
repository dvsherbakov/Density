package com.example.density;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import java.text.DecimalFormat;
import android.text.TextWatcher;
import android.text.Editable;

public class MainActivity extends AppCompatActivity {
    private TextView mResText;
    private EditText mEditTextPressure;
    private EditText mEditTextHeight;
    private Button mStartButton;
    private Double dPressure;
    private Double dHeight;
    private Spinner mUnitSpinner;
    private final Double gr;
    private static DecimalFormat df2 = new DecimalFormat("#.####");

    public MainActivity() {
        gr = 9.81;
        dPressure = 0.0;
        dHeight = 0.0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResText = findViewById(R.id.txt_result);
        mEditTextPressure = findViewById(R.id.inp_pressure);
        mEditTextHeight = findViewById(R.id.inp_height);
        mStartButton = findViewById(R.id.btn_start_result);
        mUnitSpinner = findViewById(R.id.sp_unit);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encount();
            }
        });
        mEditTextHeight.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Encount();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        mEditTextPressure.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Encount();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    protected void Encount(){
        if (mEditTextPressure.getText().toString().length() > 0)
            dPressure = Double.parseDouble(mEditTextPressure.getText().toString());
        if (mEditTextHeight.getText().toString().length() > 0)
            dHeight = Double.parseDouble(mEditTextHeight.getText().toString());
        if (dPressure <= 0 || dHeight <= 0)
            mResText.setText(0 + "");
        else {
            Integer sp = mUnitSpinner.getSelectedItemPosition();
            if (sp == 0) dPressure = dPressure * 101325;
            else dPressure = dPressure * 1000000;
            Double res = dPressure / (gr * dHeight * 1000);
            if (res < 1.0) res = 1.00;
            mResText.setText(Html.fromHtml(df2.format(res) + " г/см<sup><small>3</small></sup>"));
        }
    }
}
