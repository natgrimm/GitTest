package com.example.carmi.gittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAppointment extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        //ref = database.getReference("users/ada");
    }

    public void createAppointment(View v) {
        // grab the info from all the fields
        EditText month = findViewById(R.id.textMonth);
        EditText day = findViewById(R.id.textDay);
        EditText year = findViewById(R.id.textYear);
        EditText hour = findViewById(R.id.textHour);
        EditText minute = findViewById(R.id.textMinute);
        EditText AMorPM = findViewById(R.id.textAMorPM);
        EditText place = findViewById(R.id.textPlace);

        /*
        int mon = Integer.parseInt(month.getText().toString());
        int d = Integer.parseInt(day.getText().toString());
        int y = Integer.parseInt(year.getText().toString());
        int h = Integer.parseInt(hour.getText().toString());
        int min = Integer.parseInt(minute.getText().toString());
        String AorP = AMorPM.getText().toString();
        String p = place.getText().toString();

        // make sure that all the fields were filled out
        if (month.getText().toString().isEmpty()) {
            month.setError("membership number required");
            month.requestFocus();
            return;
        }
        if (day.getText().toString().isEmpty()) {
            day.setError("membership number required");
            day.requestFocus();
            return;
        }
        if (year.getText().toString().isEmpty()) {
            year.setError("membership number required");
            year.requestFocus();
            return;
        }
        if (hour.getText().toString().isEmpty()) {
            hour.setError("membership number required");
            hour.requestFocus();
            return;
        }
        if (minute.getText().toString().isEmpty()) {
            minute.setError("membership number required");
            minute.requestFocus();
            return;
        }
        if (AorP.isEmpty()) {
            AMorPM.setError("membership number required");
            AMorPM.requestFocus();
            return;
        }
        if (p.isEmpty()) {
            place.setError("membership number required");
            place.requestFocus();
            return;
        }
*/
        /*
        // all of the fields have been checked now. Create a new appointment
        DatabaseReference postsRef = ref.child("https://wardcalendar-477b7.firebaseio.com/");

        DatabaseReference newPostRef = postsRef.push();
        //newPostRef.setValueAsync(new Appointment(d, mon, y, h, min, AorP, p));

        // We can also chain the two calls together
        //postsRef.push().setValueAsync(new Post("alanisawesome", "The Turing Machine"));
*/
        /*
        ref = database.getReference("users").child("https://wardcalendar-477b7.firebaseio.com/")
                .push().setValue(new Appointment(d, mon, y, h, min, AorP, p));*/
    }
}
