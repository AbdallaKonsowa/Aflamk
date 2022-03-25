package com.example.aflamk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.aflamk.R;

public class LoginActivity extends AppCompatActivity {

    EditText EmailET, PasswordET;
    TextView EmailTV, passTV;
    Boolean isVisible1 = true;
    Boolean isVisible2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setContentView(R.layout.activity_login);
        //////////////////////////// hide The Header \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\\
        EmailET = findViewById(R.id.EmailET);
        PasswordET = findViewById(R.id.PasswordET);
        EmailTV = findViewById(R.id.EmailTV);
        passTV = findViewById(R.id.passTV);

        EmailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (start > 0) {
                    if (isVisible1) {
                        YoYo.with(Techniques.StandUp)
                                .duration(1000)
                                .repeat(0)
                                .playOn(findViewById(R.id.EmailTV));
                        EmailTV.setVisibility(View.VISIBLE);
                        isVisible1 = false;
                    }

                } else if (count == 0) {
                    if (!isVisible1) {
                        EmailTV.setVisibility(View.INVISIBLE);
                        isVisible1 = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        PasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    if (isVisible2) {
                        YoYo.with(Techniques.StandUp)
                                .duration(1000)
                                .repeat(0)
                                .playOn(findViewById(R.id.passTV));
                        passTV.setVisibility(View.VISIBLE);
                        isVisible2 = false;
                    }

                } else if (count == 0) {
                    if (!isVisible2) {
                        passTV.setVisibility(View.INVISIBLE);
                        isVisible2 = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void social(View view) {
        Toast.makeText(this, "Feature not supported", Toast.LENGTH_SHORT).show();

    }

    public void click(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}