package com.example.joychowdhory.notepad2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joychowdhory.notepad2.R;
import com.example.joychowdhory.notepad2.model.Note;
import com.example.joychowdhory.notepad2.utils.NoteUtils;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    private Context context;
     private ArrayList<Note> notes;

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }


    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.note_layout,parent, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {

       Note note = getNote(position);
       if (note != null) {
           holder.noteText.setText(note.getNoteText());
           holder.noteDate.setText(NoteUtils.dateFromLong(note.getNoteDate()));
       }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    private Note getNote(int position) {
        return notes.get(position);
    }

    class  NoteHolder extends RecyclerView.ViewHolder{

        TextView noteText, noteDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            noteDate = itemView.findViewById(R.id.note_date);
            noteText = itemView.findViewById(R.id.note_text);
        }
    }
}
