package com.example.carmi.gittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecretaryActivity extends AppCompatActivity {
    private static final String TAG = "SecretaryActivity";
    public static final String MESSAGE = "membershipNumber";
    private String memberNumber;
    private List<String> keys = new ArrayList<String>();
    private ArrayList<String> listItems = new ArrayList<String>();
    private List<Appointment> appointmentList = new ArrayList<Appointment>();
    private List<Appointment> validAppointments = new ArrayList<Appointment>();
    private Appointment appointmentUpdate;
    private String keyUpdate;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary);

        Intent intent = getIntent();
        memberNumber = intent.getStringExtra(MESSAGE);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        //Find the listView
        ListView secListView = findViewById(R.id.listView2);


        //Reference to database is pointed at appointmentList
        myRef.child("appointmentList").addValueEventListener(new ValueEventListener() {
            //When data changes in the database this function is activated
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Clear data before loading more
                appointmentList.clear();
                keys.clear();
                adapter.clear();
                validAppointments.clear();
                //Get a snapshot of all the children of appointmentList
                Iterable<DataSnapshot> children = snapshot.getChildren();

                //Iterate through the children
                for (DataSnapshot child : children) {
                    //Set them as appointments
                    Appointment appointment = child.getValue(Appointment.class);
                    //Add the appointment to appointmentList
                    appointmentList.add(appointment);
                    //Add the key to keys List
                    keys.add(child.getKey());
                    //Check to see if the appointment is taken but not confirmed
                    if(appointment.isTaken() == true && appointment.isConfirmed() == false) {
                        //Put appointment in string format
                        String appointment1 = "Name: " + appointment.getName() + "       " + "Member #: " + appointment.getMemberNumber() + "\n" + "Phone: " + appointment.getPhoneNumber() + "\n" +
                                "Date: " + appointment.getMonth() + "/" + appointment.getDay() + "/" + appointment.getYear() + "      " +
                                "Time: " + appointment.getHour() + ":" + appointment.getMinute() + appointment.getAmOrPm() + "\n" +
                                "Place: " + appointment.getPlace() + "\n";
                        //Add string to adapter
                        adapter.add(appointment1);
                        //Add actual appointment to keep track of it while in list form
                        validAppointments.add(appointment);
                    }
                }
            }

            //If database is cancelled give this error
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "The read failed: " + databaseError.toException());
            }
        });


        //Set an onItemClickListener for the listView
        secListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the Appointment from the position pressed by using the validAppointments list
                Appointment validAppointment = validAppointments.get(position);
                //Iterate through all appointments
                for (Appointment appointmentSearch : appointmentList) {
                    //Find the appointment that equals the one the user clicked on
                    if (appointmentSearch.equals(validAppointment)) {
                        appointmentUpdate = appointmentSearch;
                        //Get the index of that appointment
                        int index = appointmentList.indexOf(appointmentSearch);
                        //Find the key of the appointment
                        keyUpdate = keys.get(index);
                    }
                }

                // Create a string with the date of the appointment and who it's for
                String message = "You've selected " + appointmentUpdate.getName() + "'s appointment " +
                        "on " + appointmentUpdate.getMonth() + "/" + appointmentUpdate.getDay() +
                        "/" + appointmentUpdate.getYear();

                // Send a toast to the screen indicating which appointment was selected
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        //Create an Array Adapter that will show the appointments
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        //Set the adapter to the listView
        secListView.setAdapter(adapter);

    }

    public void approveAppointment(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        //Make another database reference to update
        DatabaseReference updatingRef = database.getReference();
        //Make the appointment taken
        appointmentUpdate.setConfirmed(true);
        //Update the appointment
        updatingRef.child("appointmentList").child(keyUpdate).setValue(appointmentUpdate);
    }

    public void denyAppointment(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        //Make another database reference to update
        DatabaseReference updatingRef = database.getReference();
        //Null the other database objects
        appointmentUpdate.setName(null);
        appointmentUpdate.setPhoneNumber(null);
        appointmentUpdate.setMemberNumber(null);
        //Make the appointment taken
        appointmentUpdate.setTaken(false);
        //Update the appointment
        updatingRef.child("appointmentList").child(keyUpdate).setValue(appointmentUpdate);
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
