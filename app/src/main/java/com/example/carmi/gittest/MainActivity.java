package com.example.carmi.gittest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            String name = preferences.getString("Name", null);

            if (name != null) {
                EditText tb = findViewById(R.id.textName);
                tb.setText(name);
            }
        }


        
    }

    @Override
    protected void onStop() {

        super.onStop();

        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor = spref.edit();
        EditText tb = findViewById(R.id.textName);
        String name = String.valueOf(tb.getText());
        prefEditor.putString("Name", name);
        prefEditor.apply();

    }
}
