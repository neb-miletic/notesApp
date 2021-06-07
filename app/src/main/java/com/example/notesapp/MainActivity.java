package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void createNewNote (View view)
    {
        Intent  intent = new Intent(this, CreateNote.class);
        startActivity(intent);
    }

    public void showAllNotes(View view) {
        Intent  intent = new Intent(this, ShowAllNotes.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}