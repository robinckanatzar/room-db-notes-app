package com.robinkanatzar.roomdbnotesapp.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.robinkanatzar.roomdbnotesapp.db.NotesDatabase;
import com.robinkanatzar.roomdbnotesapp.db.dao.NotesDAO;
import com.robinkanatzar.roomdbnotesapp.db.entity.Note;

import java.util.List;

public class NotesRepository {

    private LiveData<List<Note>> notesList;
    private NotesDAO notesDAO;

    public NotesRepository(Application application) {
        NotesDatabase notesDatabase = NotesDatabase.getNotesDatabaseInstance(application);
        notesDAO = notesDatabase.getNotesDAO();
        notesList = notesDAO.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return notesList;
    }

    public void insert(Note note) {
        new insertAsyncTask(notesDAO).execute(note);
    }

    public void delete(Note note) {
        new deleteAsyncTask(notesDAO).execute(note);
    }

    public void update(Note note) {
        new updateAsyncTask(notesDAO).execute(note);
    }

    private static class insertAsyncTask extends android.os.AsyncTask<Note, Note, Void> {
        private NotesDAO notesDAO;

        insertAsyncTask(NotesDAO notesDAO) {
            this.notesDAO = notesDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDAO.insertNote(notes[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends android.os.AsyncTask<Note, Note, Void> {
        private NotesDAO notesDAO;

        deleteAsyncTask(NotesDAO notesDAO) {
            this.notesDAO = notesDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDAO.deleteNote(notes[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Note, Note, Void> {
        private NotesDAO notesDAO;

        updateAsyncTask(NotesDAO notesDAO) {
            this.notesDAO = notesDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDAO.updateNote(notes[0]);
            return null;
        }
    }
}