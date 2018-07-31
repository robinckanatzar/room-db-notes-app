package com.robinkanatzar.roomdbnotesapp.ui.notesdetail;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.robinkanatzar.roomdbnotesapp.R;
import com.robinkanatzar.roomdbnotesapp.db.entity.Note;
import com.robinkanatzar.roomdbnotesapp.db.NotesDatabase;
import com.robinkanatzar.roomdbnotesapp.utils.Constants;
import com.robinkanatzar.roomdbnotesapp.viewmodel.NotesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesDetailActivity extends AppCompatActivity {

    @BindView(R.id.et_title) TextView title;
    @BindView(R.id.et_content) TextView content;

    private Note note;
    private Boolean isNewNote = false;
    private NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);

        ButterKnife.bind(this);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

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
                notesViewModel.createNote(note);
                finish();
            } else {
                note.setTitle(title.getText().toString());
                note.setContent(content.getText().toString());
                notesViewModel.updateNote(note);
                finish();
            }
        } else {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_cancel)
    public void didTapCancel() {
        finish();
    }
}
