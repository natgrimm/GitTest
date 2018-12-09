package com.example.carmi.gittest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.GitTest.MESSAGE";
    private static final String TAG = "LoginActivity";
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();


        // If there is a membership number in Shared Preferences, load it into the correct text-box
        // First, find the needed text-box
        EditText nameTextBox = findViewById(R.id.textMembershipNumber);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            // See if there is a name and phone number saved into preferences
            String membershipNumber = preferences.getString("Membership Number", null);

            // If the string pulled from SharedPreferences is valid, load it into the text-box
            if (membershipNumber != null) {
                nameTextBox.setText(membershipNumber);
            }
        }
    }
/*
    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null)
        if (mAuth.getCurrentUser() != null) {

        }
    }
*/
    public void onSignInOrRegisterClick(View v) {
        // Pull the information that the user entered in the membership and password text-boxes
        EditText membershipNumber = findViewById(R.id.textMembershipNumber);
        EditText password = findViewById(R.id.textPassword);

        // Change the information into a string that we can use.
        final String memberNumber = membershipNumber.toString();
        final String pass = password.toString();

        // Make sure that the fields were not left empty
        if (memberNumber.isEmpty()) {
            membershipNumber.setError("membership number required");
            membershipNumber.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("password required");
            password.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            password.setError("minimum length for password is 6");
            password.requestFocus();
            return;
        }

        // The fields are not empty at this point, so let's authenticate our user.
        // set progress bar to be visible
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(memberNumber + "@gmail.com", pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // the task was completed. Take the progress bar away
                progressBar.setVisibility(View.GONE);

                // was the registration successful?
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    // registration was not successful. This may be because the user is already registered
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        // here, we know that it failed because the user was already signed in. Act accordingly
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                        mAuth.signInWithEmailAndPassword(memberNumber + "@gmail.com", pass);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*
        // Create an intent containing the membership number and start the Calendar Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_MESSAGE, memberNumber);
        startActivity(intent);
        */
    }


    @Override
    protected void onStop() {
        super.onStop();

        // Save the membership # info that the user entered into the text-box into SharedPreferences
        // When they load up the app again, this information will automatically be put back into the box
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor = spref.edit();

        // Find the membership # text-box and save the info into a string
        EditText tb = findViewById(R.id.textMembershipNumber);
        String membershipNumber = String.valueOf(tb.getText());

        // put the information into our shared preferences editor and "apply it"
        prefEditor.putString("Membership Number", membershipNumber);
        prefEditor.apply();
    }
}
