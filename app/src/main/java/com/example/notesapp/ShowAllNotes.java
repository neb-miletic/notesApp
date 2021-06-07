package com.example.notesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class ShowAllNotes extends AppCompatActivity {
    ListView showListNotes;
    ArrayList<String> notesListArray ;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_notes);
        showListNotes = findViewById(R.id.showNotesListview);

        notesListArray = new ArrayList<>();


        DatabaseHelper db = new DatabaseHelper(ShowAllNotes.this);

        List<Notes> notesList = db.fetchAllNotes();

        for (Notes notes :notesList)
        {
            notesListArray.add(notes.getNotes());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesListArray);
        showListNotes.setAdapter(adapter);

         showListNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 Intent intent = new Intent(getApplicationContext(),UpdateAndDelete.class);
                 intent.putExtra("noteValue", notesListArray.get(position));

                 startActivity(intent);
             }
         });
    }
}