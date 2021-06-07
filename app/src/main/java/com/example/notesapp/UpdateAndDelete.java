package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Notes;

public class UpdateAndDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        EditText updateDelete = findViewById(R.id.textViewUpdate);

        String data = getIntent().getStringExtra("noteValue");
        updateDelete.setText(data);

        DatabaseHelper db = new DatabaseHelper(this);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = updateDelete.getText().toString();
                long result = db.updateNotes(new Notes(notes));

                if (result > 0) {
                    Toast.makeText(UpdateAndDelete.this, "Saved successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateAndDelete.this, "Sorry,not saved", Toast.LENGTH_SHORT).show();
                }

            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               db.deleteNote();



            }

        });


    }
}