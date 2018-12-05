package com.example.carmi.gittest;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";
    // List of strings -> this will be our list items
    private ArrayList<String> calendarItems = new ArrayList<String>();
    // The string adapter -> this will handle the data of the listview
    private ArrayAdapter calendarAdapter;
    private CalendarView myCalendarView;
    private Button confirmButton;
    private ListView myListView;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        myCalendarView = (CalendarView) findViewById(R.id.calendarView);

        //confirmButton = (Button) findViewById(R.id.confirmButton);

        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
            }
        });

/*
        // Set up the array adapter and connect it to our list view
        calendarAdapter = new ArrayAdapter(this, android.R.layout.activity_list_item, calendarItems);
        ListView listView = findViewById(R.id.calendarView);
        listView.setAdapter(calendarAdapter);

        // Add a message to our listview
        calendarAdapter.add("hi!");*/
    }

    /**
     * Sets up appointment
     * @param v activity View
     */
    public void onConfirmClick(View v) {
        Intent intent = new Intent(CalendarActivity.this , MainActivity.class);
        startActivity(intent);

        // Set a calendar reminder
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
                .putExtra(CalendarContract.Events.DESCRIPTION, "Heading out with friends to do something awesome.")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Earth")
                .putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        startActivity(intent2);
    }
}
