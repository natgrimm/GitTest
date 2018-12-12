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
    private int dateDay;
    private int dateMonth;
    private int dateYear;
    private Appointment appointment2;
    private List<String> appointments = new ArrayList<>();
    private List<Appointment> appointmentList = new ArrayList<>();
    private List<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        memberNumber = intent.getStringExtra(MESSAGE);
        number = intent.getStringExtra(NUMBER);
        name = intent.getStringExtra(NAME);

        myCalendarView = (CalendarView) findViewById(R.id.calendarView);

        //confirmButton = (Button) findViewById(R.id.confirmButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        myListView = findViewById(R.id.listView2);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appointments);
        myListView.setAdapter(arrayAdapter);

        /*confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference newRef = myRef.child("appointmentList").push();

                Appointment appointment1 = new Appointment();
                appointment1.setDay(dateDay);
                appointment1.setMonth(dateMonth);
                appointment1.setYear(dateYear);
                appointment1.setHour(7);
                appointment1.setMinute(30);
                appointment1.setAmOrPm("PM");
                appointment1.setPlace("STC 330");
                newRef.setValue(appointment1);
            }
        });*/

        myRef.child("appointmentList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();

                for (DataSnapshot child : children) {
                    Appointment appointment = child.getValue(Appointment.class);
                    keys.add(child.getKey());
                    appointmentList.add(appointment);

                    Log.w(TAG, "Appointment :" + appointment);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "The read failed: " + databaseError.toException());
            }
        });


        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                arrayAdapter.clear();
                date = (month + 1) + "/" + dayOfMonth + "/" + year;
                dateDay = dayOfMonth;
                dateMonth = month + 1;
                dateYear = year;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
                for (Appointment p : appointmentList) {
                    if (p.getDay() == dayOfMonth && p.getMonth() == (month + 1) && p.getYear() == year && p.isTaken() == false) {
                        String appointment1 = "Date: " + p.getMonth() + "/" + p.getDay() + "/" + p.getYear() + "\n" +
                                "Time: " + p.getHour() + ":" + p.getMinute() + p.getAmOrPm() + "\n" +
                                "Place: " + p.getPlace() + "\n";
                        arrayAdapter.add(appointment1);
                    }
                }
            }
        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = arrayAdapter.getItem(position);
                Log.d("Value is", ""+value);
                for (Appointment p : appointmentList) {
                    String appointment1 = "Date: " + p.getMonth() + "/" + p.getDay() + "/" + p.getYear() + "\n" +
                            "Time: " + p.getHour() + ":" + p.getMinute() + p.getAmOrPm() + "\n" +
                            "Place: " + p.getPlace() + "\n";
                    Log.d("Appointment1", ""+appointment1);
                    if (appointment1 == value) {
                        int index = appointmentList.indexOf(p);
                        Log.d("Index", ""+index);
                        String key = keys.get(index);
                        Log.d("Key is", ""+key);
                        DatabaseReference updatingRef = database.getReference(key);
                        p.setName(name);
                        p.setMemberNumber("1");
                        p.setPhoneNumber(number);
                        p.setTaken(true);
                        updatingRef.child(key).setValue(p);
                    }
                }
                Log.d("Position ",""+position);
            }
        });


    }
}
    /**
     * Sets up appointment
     * @param v activity View
     */
   /* public void onConfirmClick(View v) {
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
*/
