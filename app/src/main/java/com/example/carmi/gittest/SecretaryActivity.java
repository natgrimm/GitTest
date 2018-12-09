package com.example.carmi.gittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecretaryActivity extends AppCompatActivity {
    public static final String MESSAGE = "membershipNumber";
    private String memberNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary);

        Intent intent = getIntent();
        memberNumber = intent.getStringExtra(MESSAGE);
    }

    public void approveAppointment(View v) {

    }

    public void denyAppointment(View v) {

    }

    public void createAppointment(View v) {
        Intent intent = new Intent(this, CreateAppointment.class);
        intent.putExtra(MESSAGE, memberNumber);
        startActivity(intent);
    }

    public void scheduleAppointment(View v) {
        Intent intent = new Intent(this, NameAndNumberActivity.class);
        intent.putExtra(MESSAGE, memberNumber);
        startActivity(intent);
    }
}
