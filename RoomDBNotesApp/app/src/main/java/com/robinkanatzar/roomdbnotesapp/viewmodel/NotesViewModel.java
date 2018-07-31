package com.robinkanatzar.roomdbnotesapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.robinkanatzar.roomdbnotesapp.repository.NotesRepository;
import com.robinkanatzar.roomdbnotesapp.db.entity.Note;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository notesRepository;
    private LiveData<List<Note>> notesList;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        notesList = notesRepository.getAllNotes();
    }

    public LiveData<List<Note>> getListOfNotes() {
        return notesList;
    }

    public void deleteNote(Note note) {
        notesRepository.delete(note);
    }

    public void createNote(Note note) {
        notesRepository.insert(note);
    }

    public void updateNote(Note note) {
        notesRepository.update(note);
    }
}
