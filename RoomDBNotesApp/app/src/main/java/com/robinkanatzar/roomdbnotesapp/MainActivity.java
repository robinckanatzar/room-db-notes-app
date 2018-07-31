package com.robinkanatzar.roomdbnotesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.robinkanatzar.roomdbnotesapp.db.Note;
import com.robinkanatzar.roomdbnotesapp.db.NotesDatabase;
import com.robinkanatzar.roomdbnotesapp.utils.Constants;
import com.robinkanatzar.roomdbnotesapp.utils.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// Room Tutorial 17: Edit the MainActivity
// Add @BindView for RV
// Add Butterknife.bind(this)
// Delete onCreate options menu
// Delete onOptionsItemSelected

// Room Tutorial 20: Incorporate Adapter

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main) RecyclerView recyclerView;

    private NotesDatabase notesDatabase;
    private List<Note> notesList = new ArrayList<>();
    private NotesAdapter notesAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();
        initDatabase();
        initFAB();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadExistingNotes();
    }

    private void initRecyclerView() {
        notesAdapter = new NotesAdapter(MainActivity.this, notesList);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerViewItemClickListener(MainActivity.this, recyclerView ,new RecyclerViewItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, NotesDetailActivity.class);
                        intent.putExtra(Constants.INTENT_EXTRA_NOTE_ID, notesList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        deleteNote(notesList.get(position));
                        Toast.makeText(MainActivity.this, getString(R.string.note_deleted), Toast.LENGTH_SHORT).show();
                        loadExistingNotes();
                    }
                })
        );
    }

    private void initDatabase() {
        notesDatabase = NotesDatabase.getNotesDatabaseInstance(MainActivity.this);
    }

    private void initFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotesDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("StaticFieldLeak") // TODO static field leak
    private void loadExistingNotes() {
        new AsyncTask<Void, Void, List<Note>>() {
            @Override
            protected List doInBackground(Void... params) {
                return notesDatabase.getNotesDAO().getAllNotes();
            }

            @Override
            protected void onPostExecute(List items) {
                notesList.clear();
                for(int i = 0; i < items.size(); i++) {
                    notesList.add((Note) items.get(i));
                }
                notesAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak") // TODO static field leak
    private void deleteNote(final Note noteToDelete) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.getNotesDAO().deleteNote(noteToDelete);
                return null;
            }
        }.execute();
    }
}
