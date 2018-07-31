package com.robinkanatzar.roomdbnotesapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.robinkanatzar.roomdbnotesapp.utils.Constants;

import java.util.List;

// Room Tutorial 10: Add the DAO (data access object).
// Add methods for CRUD - create, read, update, delete
// Room Tutorial 11: Create a new package called "db". Move this file and the Note file into it.
@Dao
public interface NotesDAO {

    @Query("SELECT * FROM "+ Constants.NOTES_TABLE_NAME)
    List<Note> getAllNotes();

    @Insert
    long insertNote(Note newNote);

    @Update
    void updateNote(Note updatedNote);

    @Delete
    void deleteNote(Note noteToDelete);
}