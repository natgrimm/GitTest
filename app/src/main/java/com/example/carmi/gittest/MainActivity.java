package com.example.carmi.gittest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.GitTest.MESSAGE";
    private static final String TAG = "MainActivity";
    // List of strings -> this will be our list items
    private ArrayList<String> listItems = new ArrayList<String>();
    // The string adapter -> this will handle the data of the listview
    private ArrayAdapter<String> adapter;

    List<Appointment> masterSchedule = new ArrayList<Appointment>();
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If there are any Shared Preferences, load them into their respective text-boxes
        // First, find the needed text-boxes
        EditText nameTextBox = findViewById(R.id.textName);
        EditText phoneTextBox = findViewById(R.id.textPhone);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            // See if there is a name and phone number saved into preferences
            String name = preferences.getString("Name", null);
            String phone = preferences.getString("Phone", null);

            // Check if they are valid, and if so, find and set the correct text-box to that value
            if (name != null) {
                nameTextBox.setText(name);
            }

            if (phone != null) {
                phoneTextBox.setText(phone);
            }
        }

        // Make a fake appointment
        Appointment p = new Appointment(20, 12, 2018, 5, 5,
                                        "PM", "TAY220");

        // Set up a string to contain the appointment info
        String appointment = "Date: " + p.getDate() + "\n" +
                             "Time: " + p.getTime() + "\n" +
                             "Place: " + p.getPlace() + "\n";

        // Set up the array adapter and connect it to the list view
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Add our appointment to the listview
        adapter.add(appointment);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save the information that the user entered into the text-boxes into SharedPreferences
        // When they load up the app again, this information will automatically be put back into the box
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor = spref.edit();

        // Find the name text-box and save the info into a string
        EditText tb = findViewById(R.id.textName);
        String name = String.valueOf(tb.getText());

        // Find the phone number text-box and save the info into a string
        EditText textBox = findViewById(R.id.textPhone);
        String phone = String.valueOf(textBox.getText());

        // put the information into our shared preferences editor and "apply it"
        prefEditor.putString("Name", name);
        prefEditor.putString("Phone", phone);
        prefEditor.apply();
    }

    /**
     * Pulls up calendar view
     * @param v activity View
     */
    public void onScheduleClick(View v) {
        // Pull the information that the user put into the name and phone text-boxes
        EditText name = findViewById(R.id.textName);
        EditText phone = findViewById(R.id.textPhone);

        // Change the information into a string that we can use.
        String userName = name.getText().toString();
        String phoneNumber = phone.getText().toString();

        // Send this information off to be checked...
        if (userName.equals(" ") || userName.equals("")) {
            Log.w(TAG, ("User name was left empty."));
        }
        if (phoneNumber.equals(" ") || phoneNumber.equals("")) {
            Log.w(TAG, ("Phone number was left empty."));
        }

        // Create an intent containing the username and phone number and start the Calendar Activity
        String intentMessage = userName + " " + phoneNumber;
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(EXTRA_MESSAGE, intentMessage);
        startActivity(intent);
    }
}
