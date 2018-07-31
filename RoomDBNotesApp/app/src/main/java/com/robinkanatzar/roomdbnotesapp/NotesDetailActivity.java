package com.robinkanatzar.roomdbnotesapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.robinkanatzar.roomdbnotesapp.db.Note;
import com.robinkanatzar.roomdbnotesapp.db.NotesDatabase;
import com.robinkanatzar.roomdbnotesapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesDetailActivity extends AppCompatActivity {

    @BindView(R.id.et_title) TextView title;
    @BindView(R.id.et_content) TextView content;

    private NotesDatabase notesDatabase;
    private Note note;
    private Boolean isNewNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);

        ButterKnife.bind(this);

        notesDatabase = NotesDatabase.getNotesDatabaseInstance(NotesDetailActivity.this);

        if(this.getIntent().getExtras() != null) {
            if(this.getIntent().getExtras().getString(Constants.INTENT_EXTRA_NOTE_ID) != null) {
                note = (Note) this.getIntent().getExtras().get(Constants.INTENT_EXTRA_NOTE_ID);
                title.setText(note.getTitle());
                content.setText(note.getContent());
            }
        } else {
            isNewNote = true;
        }
    }

    @OnClick(R.id.btn_save)
    public void didTapSave() {
        if(title.getText().length() > 0 && content.getText().length() > 0) {
            if(isNewNote) {
                note = new Note(title.getText().toString(), content.getText().toString());
                createNote(note);
            } else {
                note.setTitle(title.getText().toString());
                note.setContent(content.getText().toString());
                updateNote(note);
            }
        } else {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_cancel)
    public void didTapCancel() {
        finish();
    }

    @SuppressLint("StaticFieldLeak") // TODO static field leak
    private void updateNote(final Note noteToUpdate) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.getNotesDAO().updateNote(noteToUpdate);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak") // TODO static field leak
    private void createNote(final Note noteToCreate) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.getNotesDAO().insertNote(noteToCreate);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }
        }.execute();
    }
}
