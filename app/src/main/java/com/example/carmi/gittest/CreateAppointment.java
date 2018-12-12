package com.example.carmi.gittest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class CreateAppointment extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    public static final String MESSAGE = "membershipNumber";
    private String memberNumber;
    private EditText month;
    private EditText day;
    private EditText year;
    private EditText hour;
    private EditText minute;
    private EditText AMorPM;
    private EditText place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        // pull the membership number from the intent that started this activity
        Intent intent = getIntent();
        memberNumber = intent.getStringExtra(MESSAGE);

        // connect all the edit text variables to their respective text-box
        month = findViewById(R.id.textMonth);
        day = findViewById(R.id.textDay);
        year = findViewById(R.id.textYear);
        hour = findViewById(R.id.textHour);
        minute = findViewById(R.id.textMinute);
        AMorPM = findViewById(R.id.textAMorPM);
        place = findViewById(R.id.textPlace);

        // it can get tiring to type in the info all the time, so we'll pull info from shared preferences
        // and see if we can put some info in for the user
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            // grab any info that might be there
            String mon = preferences.getString("Month", null);
            String d = preferences.getString("Day", null);
            String y = preferences.getString("Year", null);
            String h = preferences.getString("Hour", null);
            String min = preferences.getString("Minute", null);
            String AorP = preferences.getString("AMorPM", null);
            String p = preferences.getString("Place", null);

            // Check if they are valid, and if so, find and set the correct text-box to that value
            if (mon != null) {
                month.setText(mon);
            }
            if (d != null) {
                day.setText(d);
            }
            if (y != null) {
                year.setText(y);
            }
            if (h != null) {
                hour.setText(h);
            }
            if (min != null) {
                minute.setText(min);
            }
            if (AorP != null) {
                AMorPM.setText(AorP);
            }
            if (p != null) {
                place.setText(p);
            }
        }

        ref = database.getReference();
    }

    public void createAppointment(View v) {
        // make sure that all the text-boxes were filled out
        checkFields();

        // grab the info in the fields and turn it into usable stuff
        int mon = parseInt(month.getText().toString());
        int d = Integer.parseInt(day.getText().toString());
        int y = Integer.parseInt(year.getText().toString());
        int h = Integer.parseInt(hour.getText().toString());
        int min = Integer.parseInt(minute.getText().toString());
        String AorP = AMorPM.getText().toString();
        String p = place.getText().toString();


        // all of the fields have been checked now. Create a new appointment
        Appointment apt = new Appointment(d, mon, y, h, min, AorP, p);
        apt.setConfirmed(false);
        apt.setTaken(false);

        // push it onto the database
        DatabaseReference aptsRef = ref.child("appointmentList");
        aptsRef.push().setValue(apt);

        // send out a toast signalling that we've successfully created an appointment
        Toast.makeText(getApplicationContext(), "Appointment successfully created", Toast.LENGTH_SHORT).show();

        // now send the user back to the main activity -> with their membership number
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MESSAGE, memberNumber);
        startActivity(intent);
    }

    /**
     * Check that all the user input fields were filled
     */
    private void checkFields() {
        if (month.getText().toString().isEmpty()) {
            month.setError("enter a month");
            month.requestFocus();
            return;
        }
        if (day.getText().toString().isEmpty()) {
            day.setError("enter a day");
            day.requestFocus();
            return;
        }
        if (year.getText().toString().isEmpty()) {
            year.setError("enter a year");
            year.requestFocus();
            return;
        }
        if (hour.getText().toString().isEmpty()) {
            hour.setError("enter an hour");
            hour.requestFocus();
            return;
        }
        if (minute.getText().toString().isEmpty()) {
            minute.setError("enter the minute");
            minute.requestFocus();
            return;
        }
        if (AMorPM.getText().toString().isEmpty()) {
            AMorPM.setError("enter AM or PM");
            AMorPM.requestFocus();
            return;
        }
        if (place.getText().toString().isEmpty()) {
            place.setError("enter the place");
            place.requestFocus();
            return;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save the information that the user entered into the text-boxes into SharedPreferences
        // When they load up the app again, this information will automatically be put back into the boxes
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor = spref.edit();

        // put the information into our shared preferences editor and "apply it"
        prefEditor.putString("Month", month.getText().toString());
        prefEditor.putString("Day", day.getText().toString());
        prefEditor.putString("Year", year.getText().toString());
        prefEditor.putString("Hour", hour.getText().toString());
        prefEditor.putString("Minute", minute.getText().toString());
        prefEditor.putString("AMorPM", AMorPM.getText().toString());
        prefEditor.putString("Place", place.getText().toString());
        prefEditor.apply();
    }
}
