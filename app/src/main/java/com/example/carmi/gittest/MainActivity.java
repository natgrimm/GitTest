package com.example.carmi.gittest;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.GitTest.MESSAGE";
    private static final String TAG = "MainActivity";
    private static final String CHANNEL_ID = "YOUR_CHANNEL_NAME";
    private static final int notificationId = 123456;

    List<Appointment> appointmentList = new ArrayList<Appointment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //////
        // notifications stuff...
        createNotificationChannel();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                // automatically remove the notification when the user taps it
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mBuilder.build());
        /////


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

    /*
    * You must create the notification channel before posting any notifications on Android 8.0 and
    * higher. You should execute this code as soon as your app starts. It's safe to call this
    * repeatedly because creating an existing notification channel performs no operation.
    */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            String name = "default";
            String description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onScheduleClick(View v) {
        // Pull the information that the user put into the name and phone text-boxes
        EditText name = findViewById(R.id.textName);
        EditText phone = findViewById(R.id.textPhone);

        // Change the information into a string that we can use.
        String userName = name.toString();
        String phoneNumber = phone.toString();

        // Send this information off to be checked...
        if (userName.equals(" ")) {
            Log.w(TAG, ("User name was left empty."));
        }
        if (phoneNumber.equals(" ")) {
            Log.w(TAG, ("Phone number was left empty."));
        }

        // Create an intent containing the username and phone number and start the Calendar Activity
        String intentMessage = userName + " " + phoneNumber;
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(EXTRA_MESSAGE, intentMessage);
        startActivity(intent);
    }

    // TEMPORARY
    // here for now to help us see what bishop, counselor, and secretary need to see
    public void onBishopViewClick(View v) {
        // not sure what exactly we need in these, but I'm going to have them include the name and
        // number of the user

        // Pull the information that the user put into the name and phone text-boxes
        EditText name = findViewById(R.id.textName);
        EditText phone = findViewById(R.id.textPhone);

        // Change the information into a string that we can use.
        String userName = name.toString();
        String phoneNumber = phone.toString();

        // Create an intent containing the username and phone number and start the Calendar Activity
        String intentMessage = userName + " " + phoneNumber;
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(EXTRA_MESSAGE, intentMessage);
        startActivity(intent);
    }

    public void onCounselorViewClick(View v) {
        // Pull the information that the user put into the name and phone text-boxes
        EditText name = findViewById(R.id.textName);
        EditText phone = findViewById(R.id.textPhone);

        // Change the information into a string that we can use.
        String userName = name.toString();
        String phoneNumber = phone.toString();

        // Create an intent containing the username and phone number and start the Calendar Activity
        String intentMessage = userName + " " + phoneNumber;
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(EXTRA_MESSAGE, intentMessage);
        startActivity(intent);
    }

    public void onSecretaryViewClick(View v) {
        // Pull the information that the user put into the name and phone text-boxes
        EditText name = findViewById(R.id.textName);
        EditText phone = findViewById(R.id.textPhone);

        // Change the information into a string that we can use.
        String userName = name.toString();
        String phoneNumber = phone.toString();

        // Create an intent containing the username and phone number and start the Calendar Activity
        String intentMessage = userName + " " + phoneNumber;
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(EXTRA_MESSAGE, intentMessage);
        startActivity(intent);
        this.goToCalendar(v);

    }

    public void goToCalendar (View view){
        Intent intent = new Intent (this, CalendarActivity.class);
        startActivity(intent);
    }
}
