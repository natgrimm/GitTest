package com.example.carmi.gittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NameAndNumberActivity extends AppCompatActivity {
    public static final String MESSAGE = "membershipNumber";
    public static final String MEMBER = "member";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    private String memberNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_number);

        Intent intent = getIntent();
        memberNumber = intent.getStringExtra(MESSAGE);
    }

    public void onScheduleClick(View v) {
        // Pull the information that the user put into the name and phone text-boxes
        EditText name = findViewById(R.id.textName2);
        EditText phone = findViewById(R.id.textPhone2);
        EditText member = findViewById(R.id.textMemberNumber);

        // Change the information into a string that we can use.
        String userName = name.getText().toString();
        String phoneNumber = phone.getText().toString();
        String membership = member.getText().toString();

        // Make sure that the name and number fields were not left empty
        if (userName.isEmpty()) {
            name.setError("name cannot be left empty");
            name.requestFocus();
            return;
        }

        if (phoneNumber.isEmpty()) {
            phone.setError("number cannot be left empty");
            phone.requestFocus();
            return;
        }

        if (membership.isEmpty()) {
            member.setError("membership number cannot be left empty");
            member.requestFocus();
            return;
        }

        // Create an intent containing the username and phone number and start the Calendar Activity
        //String intentMessage = userName + " " + phoneNumber;
        Intent intent = new Intent(this, CalendarActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, intentMessage);
        intent.putExtra(MESSAGE, memberNumber);
        intent.putExtra(MEMBER, membership);
        intent.putExtra(NUMBER, phoneNumber);
        intent.putExtra(NAME, userName);
        startActivity(intent);
    }
}
