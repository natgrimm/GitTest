package com.example.carmi.gittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
    }

    public void createAppointment(View v) {
        /*
        DatabaseReference postsRef = ref.child("posts");

        DatabaseReference newPostRef = postsRef.push();
        newPostRef.setValueAsync(new Post("gracehop", "Announcing COBOL, a New Programming Language"));

        // We can also chain the two calls together
        postsRef.push().setValueAsync(new Post("alanisawesome", "The Turing Machine"));
        */
    }
}
