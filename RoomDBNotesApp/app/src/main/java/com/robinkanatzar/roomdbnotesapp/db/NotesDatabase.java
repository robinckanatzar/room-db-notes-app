package com.robinkanatzar.roomdbnotesapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.robinkanatzar.roomdbnotesapp.utils.Constants;
import com.robinkanatzar.roomdbnotesapp.utils.DateConverter;

/*
Database: Contains the database holder and serves as the main access point for the underlying
connection to your app's persisted, relational data.

Must be an abstract class that extends RoomDatabase.

Must include the list of entities associated with the database within the annotation. (i.e. the
Note entity here)

Must contain an abstract method that has 0 arguments and returns the class that is annotated with @Dao.
(i.e. public abstract NotesDAO getNotesDAO();)
 */
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

    /*
    At runtime, you can acquire an instance of Database by calling Room.databaseBuilder() or
    Room.inMemoryDatabaseBuilder().
     */
    private static NotesDatabase buildNewNotesDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, NotesDatabase.class, Constants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public void clearDatabase(){
        notesDatabase = null;
    }
}