package com.robinkanatzar.roomdbnotesapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.robinkanatzar.roomdbnotesapp.db.entity.Note;
import com.robinkanatzar.roomdbnotesapp.utils.Constants;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM "+ Constants.NOTES_TABLE_NAME)
    public LiveData<List<Note>> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNote(Note newNote);

    @Update
    void updateNote(Note updatedNote);

    @Delete
    void deleteNote(Note noteToDelete);
}