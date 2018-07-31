package com.robinkanatzar.roomdbnotesapp;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main) RecyclerView recyclerView;

    private List<Note> notesList = new ArrayList<>();
    private NotesAdapter notesAdapter;
    private LinearLayoutManager linearLayoutManager;
    private NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();
        initFAB();

        initViewModel();
    }

    private void initViewModel() {
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getListOfNotes().observe(this, listOfNotes -> {
            Toast.makeText(this, "Inside viewModel observer", Toast.LENGTH_SHORT).show();
            notesList.clear();
            for(int i = 0; i < listOfNotes.size(); i++) {
                notesList.add(listOfNotes.get(i));
            }
            notesAdapter.notifyDataSetChanged();
        });
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
                        notesViewModel.deleteNote(notesList.get(position));
                        Toast.makeText(MainActivity.this, getString(R.string.note_deleted), Toast.LENGTH_SHORT).show();
                    }
                })
        );
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
}
