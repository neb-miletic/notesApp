package com.example.notesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.notesapp.model.Notes;
import com.example.notesapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

         String CREATE_USER_TABLE = "CREATE TABLE "  + Util.TABLE_NAME + " (" +  Util.NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.NOTES + " TEXT)";

         db.execSQL(CREATE_USER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS  Util.DATABASE_NAME";

        db.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);

    }

    public long insertNotes (Notes notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTES, notes.getNotes_id());
        contentValues.put(Util.NOTES, notes.getNotes());
        long newRowId = db.insert(Util.TABLE_NAME,null, contentValues);

        db.close();
        Log.d("Inserted","ID" + newRowId);


        return newRowId;
    }
//
//    public Notes getNotes(long id)
//    {
//         SQLiteDatabase db = this.getWritableDatabase();
//
//
//        Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.NOTES_ID}, Util.NOTES + "=?", new String[] {id}, null,null,null);
//
//        if(cursor != null)
//            cursor.moveToFirst();
//
//
//        return new Notes(
//            Long.parseLong(cursor.getString(0)),
//            cursor.getString(1));
//
//
//
//    }

    public List<Notes> fetchAllNotes () {

        List<Notes> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null );


        if (cursor.moveToFirst()) {
            do {
                Notes notes = new Notes();
                notes.setNotes_id(cursor.getInt(0));
                notes.setNotes(cursor.getString(1));

                notesList.add(notes);

            } while (cursor.moveToNext());
        }

        return notesList;
    }



    public int updateNotes (Notes notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ notes.getNotes() + notes.getNotes_id());

        contentValues.put(Util.NOTES, notes.getNotes());

        return db.update(Util.TABLE_NAME, contentValues, Util.NOTES + "=?", new String[]{String.valueOf(notes.getNotes_id())});

    }
    public void deleteNote(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.NOTES_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

}
