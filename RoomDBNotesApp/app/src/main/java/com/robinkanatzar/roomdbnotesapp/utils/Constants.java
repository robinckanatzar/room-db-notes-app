package com.robinkanatzar.roomdbnotesapp.utils;

// Room Tutorial 5: Create a new class to hold our application constants.
// Room Tutorial 6: Create a new directory called "utils". Move this class into that new folder.
// Room Tutorial 7: Change this class from "public class Constants{}" to "final public class Constants {}" with an empty constructor.
final public class Constants {
    private Constants() {

    }

    // Room Tutorial 8: Add two constants we know we'll need: table name and the database name.
    public static final String NOTES_TABLE_NAME = "notes_table";
    public static final String DATABASE_NAME = "notesdatabase.db";

    public static final String INTENT_EXTRA_NOTE_ID = "note_id";
}
