package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Notes;

public class CreateNote extends AppCompatActivity {

 DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        EditText textMulti = findViewById(R.id.textMulti);
        Button saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = textMulti.getText().toString();
                long result = db.insertNotes(new Notes(notes));

                if (result > 0)
                {
                    Toast.makeText(CreateNote.this, "Saved successfully!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(CreateNote.this, "Sorry,not saved", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }
}