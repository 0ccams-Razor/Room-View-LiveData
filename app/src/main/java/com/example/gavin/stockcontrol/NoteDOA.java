package com.example.gavin.stockcontrol;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

//Tells Room this is a DOA
//Make one DOA per entiry/class
@Dao
public interface NoteDOA {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    //If the table columns dpn't match the not class you will get a compile time error
    //LiveData makes this observable
    LiveData<List<Note>> getAllNotes();
}
