package com.zhack.YW;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class SignUpActivity extends AppCompatActivity {

    String neighbour;
    int countUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        ParseQuery<ParseUser> query = ParseUser.getQuery();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EfggqM19wCuCy4XeOOLXiT7PqMCpqVNVpx7hzKNK") // should correspond to Application Id env variable
                .clientKey("wN6ZJERctLLWzvI34bSrLpNcEXDyJFptDSLQhyko")
                .server("https://parseapi.back4app.com").build());


        final EditText etEmail;
        final EditText etPassword;
        Spinner spinner;

        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                if (e == null){
                    countUser = count;
                }
            }
        });

        Spinner dropdown = findViewById(R.id.spNeighbour);
//create a list of items for the spinner.
        final String[] items = new String[]{"Please select your neighbour", "Marina", "Seaside", "Pacific Groove", "Monterey"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Button btGoback;
        Button btRegister;

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btRegister = findViewById(R.id.btRegister);
        btGoback = findViewById(R.id.btGoback);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                if (item != null && item != items[0]) {
                    neighbour = item;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
// Set core properties
                user.setUsername(String.valueOf(etEmail.getText()));
                user.setPassword(String.valueOf(etPassword.getText()));
                user.put("neighbour", neighbour);
                user.put("number", countUser);
// Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(SignUpActivity.this, "Successfully Created Account", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });

        btGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}