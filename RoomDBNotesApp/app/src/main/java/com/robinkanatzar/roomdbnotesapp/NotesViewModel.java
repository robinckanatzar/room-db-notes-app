package com.robinkanatzar.roomdbnotesapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.robinkanatzar.roomdbnotesapp.db.Note;
import com.robinkanatzar.roomdbnotesapp.db.NotesDatabase;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private LiveData<List<Note>> notesList;
    private NotesDatabase notesDatabase;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getNotesDatabaseInstance(getApplication().getApplicationContext());
        getNotesFromDatabase();
    }

    public LiveData<List<Note>> getListOfNotes() {
        if(notesList == null) {
            notesList = new MutableLiveData<List<Note>>();
            getNotesFromDatabase();
        }
        return notesList;
    }

    private void getNotesFromDatabase() {
        notesList = notesDatabase.getNotesDAO().getAllNotes();
    }
}
