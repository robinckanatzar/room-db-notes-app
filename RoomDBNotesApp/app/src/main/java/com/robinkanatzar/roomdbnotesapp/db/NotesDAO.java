package com.robinkanatzar.roomdbnotesapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.robinkanatzar.roomdbnotesapp.utils.Constants;

import java.util.List;

/*
DAO: Contains the methods used for accessing the database.

To access your app's data using the Room persistence library, you work with data access objects, or
DAOs. This set of Dao objects forms the main component of Room, as each DAO includes methods that
offer abstract access to your app's database.

By accessing a database using a DAO class instead of query builders or direct queries, you can
separate different components of your database architecture. Furthermore, DAOs allow you to easily
mock database access as you test your app.

A DAO can be either an interface or an abstract class. If it's an abstract class, it can optionally
have a constructor that takes a RoomDatabase as its only parameter. Room creates each DAO implementation
at compile time.
 */
@Dao
public interface NotesDAO {

    /*
    @Query is the main annotation used in DAO classes. It allows you to perform read/write operations
    on a database. Each @Query method is verified at compile time, so if there is a problem with the
    query, a compilation error occurs instead of a runtime failure.

    Example with query parameters:
        @Query("SELECT * FROM user WHERE age > :minAge")
        public User[] loadAllUsersOlderThan(int minAge);

    Room generates all necessary code to update the LiveData when the database is updated.
     */
    @Query("SELECT * FROM "+ Constants.NOTES_TABLE_NAME)
    public LiveData<List<Note>> getAllNotes();

    /*
    When you create a DAO method and annotate it with @Insert, Room generates an implementation that
    inserts all parameters into the database in a single transaction.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNote(Note newNote);

    /*
    The Update convenience method modifies a set of entities, given as parameters, in the database.
    It uses a query that matches against the primary key of each entity.
     */
    @Update
    void updateNote(Note updatedNote);

    /*
    The Delete convenience method removes a set of entities, given as parameters, from the database.
    It uses the primary keys to find the entities to delete
     */
    @Delete
    void deleteNote(Note noteToDelete);
}