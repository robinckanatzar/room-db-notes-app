package com.robinkanatzar.roomdbnotesapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.robinkanatzar.roomdbnotesapp.utils.Constants;
import com.robinkanatzar.roomdbnotesapp.utils.DateConverter;

// Room Tutorial 12: Add Database class (in the db folder). Make the class abstract. Make the class extend RoomDatabase.
// Add the @Database annotation. Reference the entity "Notes.class".
@Database(entities = {Note.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDAO getNotesDAO();
    private static NotesDatabase notesDatabase;

    public static NotesDatabase getNotesDatabaseInstance(Context context) {
        if(notesDatabase == null) {
            notesDatabase = buildNewNotesDatabaseInstance(context);
        }
        return notesDatabase;
    }

    private static NotesDatabase buildNewNotesDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, NotesDatabase.class, Constants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public void clearDatabase(){
        notesDatabase = null;
    }
}