package com.robinkanatzar.roomdbnotesapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robinkanatzar.roomdbnotesapp.R;
import com.robinkanatzar.roomdbnotesapp.db.entity.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notesList;
    private Context context;

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTitle;
        public TextView noteContent;

        public NotesViewHolder(View view) {
            super(view);

            noteTitle = (TextView) view.findViewById(R.id.tv_item_title);
            noteContent = (TextView) view.findViewById(R.id.tv_item_content);
        }
    }

    public NotesAdapter(Context context, List<Note> notesList) {
        this.notesList = notesList;
        this.context = context;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent, false);
        return new NotesAdapter.NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.noteTitle.setText(note.getTitle());
        holder.noteContent.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    void setWords(List<Note> notesList){
        this.notesList = notesList;
        notifyDataSetChanged();
    }
}