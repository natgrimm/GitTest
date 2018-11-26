package com.example.carmi.gittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView myCalendarView;
    private Button confirmButton;
    private ListView myListView;
    private String date;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        myCalendarView = (CalendarView) findViewById(R.id.calendarView);

        confirmButton = (Button) findViewById(R.id.confirmButton);

        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.activity_list_item);



    }

}
