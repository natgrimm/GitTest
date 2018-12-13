package com.example.carmi.gittest;

import android.content.Intent;
import android.database.DataSetObserver;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    public static final String MESSAGE = "membershipNumber";
    private static final String TAG = "CalendarActivity";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    private CalendarView myCalendarView;
    private Button confirmButton;
    private ListView myListView;
    private String memberNumber;
    private String number;
    private String name;
    private String date;
    private List<String> appointments = new ArrayList<>();
    private List<Appointment> appointmentList = new ArrayList<>();
    private List<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Grab variables from the last intent
        Intent intent = getIntent();
        memberNumber = intent.getStringExtra(MESSAGE);
        number = intent.getStringExtra(NUMBER);
        name = intent.getStringExtra(NAME);

        //Find the calendarView
        myCalendarView = (CalendarView) findViewById(R.id.calendarView);

        //Get the Firebase Database set up with the right instance
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Attach the right reference to the Firebase Database
        final DatabaseReference myRef = database.getReference();

        //Find the listView
        myListView = findViewById(R.id.listView2);

        final List<Appointment> validAppointments = new ArrayList<Appointment>();

        //Create an Array Adapter that will show the appointments
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appointments);
        //Set the adapter to the listView
        myListView.setAdapter(arrayAdapter);


        //Direct the Database reference to the child appointmentList and add a ValueEventListener to read data from Firebase
        myRef.child("appointmentList").addValueEventListener(new ValueEventListener() {
            //Whenever the data changes or is first being loaded it will call this function
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Clear data before loading more
                appointmentList.clear();
                arrayAdapter.clear();
                appointments.clear();
                keys.clear();
                //Get a snapshot of the the data for every child of appointmentList in the database
                Iterable<DataSnapshot> children = snapshot.getChildren();

                //Iterate through each child of appointmentList
                for (DataSnapshot child : children) {
                    //Transfer data to the appointment class
                    Appointment appointment = child.getValue(Appointment.class);
                    //Add the key to a list so we no where to update and delete
                    keys.add(child.getKey());
                    //Add the appointment to a list of appointments
                    appointmentList.add(appointment);

                    //Make sure each appointment is it's own object
                    Log.w(TAG, "Appointment :" + appointment);
                }
            }

            //If reading the database is cancelled let us know
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "The read failed: " + databaseError.toException());
            }
        });


        //Set up a date change listener to find the date when it changes
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Clear the arrayAdapter so it can hold new values
                arrayAdapter.clear();
                validAppointments.clear();
                //Make sure the date is correct
                date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);

                //Iterate through the list of appointments and if it has the same date as the calendar add it to the arrayAdapter
                for (Appointment p : appointmentList) {
                    if (p.getDay() == dayOfMonth && p.getMonth() == (month + 1) && p.getYear() == year && p.isTaken() == false) {
                        String appointmentString = "Date: " + p.getMonth() + "/" + p.getDay() + "/" + p.getYear() + "\n" +
                                "Time: " + p.getHour() + ":" + p.getMinute() + p.getAmOrPm() + "\n" +
                                "Place: " + p.getPlace() + "\n";
                        arrayAdapter.add(appointmentString);
                        validAppointments.add(p);
                    }
                }
            }
        });


        //Set an onItemClickListener for the listView
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the Appointment from the position pressed by using the validAppointments list
                Appointment validAppointment = validAppointments.get(position);
                //Iterate through all appointments
                for (Appointment appointmentUpdate : appointmentList) {
                    //Find the appointment that equals the one the user clicked on
                    if (appointmentUpdate.equals(validAppointment)) {
                        //Get the index of that appointment
                        int index = appointmentList.indexOf(appointmentUpdate);
                        Log.d("Index", ""+index);
                        //Find the key of the appointment
                        String key = keys.get(index);
                        Log.d("Key is", ""+key);
                        //Make another database reference to update
                        DatabaseReference updatingRef = database.getReference();
                        //Set the name, member number, and phonenumber with the values given
                        appointmentUpdate.setName(name);
                        appointmentUpdate.setMemberNumber(memberNumber);
                        appointmentUpdate.setPhoneNumber(number);
                        //Make the appointment taken
                        appointmentUpdate.setTaken(true);
                        //Update the appointment
                        updatingRef.child("appointmentList").child(key).setValue(appointmentUpdate);
                    }
                }
            }
        });


    }

    /**
     * Schedules an appointment
     * @param v activity View
     */
    public void onConfirmClick(View v) {
        // depending on the user, send them to the appropriate activity after they have confirmed
        // (for the secretary, send him back to the secretary activity, and for the bishop and regular
        // user, send them to the main activity)
        Intent intent;
        if (memberNumber.equals("2"))
        {
            intent = new Intent(CalendarActivity.this, SecretaryActivity.class);
        }
        else
        {
            intent = new Intent(CalendarActivity.this , MainActivity.class);
        }
        // now start the activity up
        startActivity(intent);

        // No matter who the user is, set a calendar reminder for them. They can choose to discard
        // this, or save it if they want.
        int hour = 7;
        int min = 25;
        int sec = 0;

        // Declare what the begin time is
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2018, 11, 26, hour, min);
        long startMillis = beginTime.getTimeInMillis();

        // Declare what the end time is
        Calendar endTime = Calendar.getInstance();
        endTime.set(2018, 11, 26, hour + 1, min);
        long endMillis = endTime.getTimeInMillis();

        // Start the calendar reminder activity
        Intent intent2 = new Intent(Intent.ACTION_INSERT)
                .setType("vnd.android.cursor.item/event")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false) // just included for completeness
                .putExtra(CalendarContract.Events.TITLE, "Appointment Reminder")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Appointment with a bishopric member.")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Earth")
                .putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        startActivity(intent2);
    }
}

