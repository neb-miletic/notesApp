package com.example.notesapp.model;

public class Notes {

    private String notes;
    private int notes_id;

    public Notes(String notes) {
        this.notes = notes;

    }


    public Notes() {

    }

    public int getNotes_id () {
        return notes_id;
    }

    public int setNotes_id (int user_id) {
        return notes_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
