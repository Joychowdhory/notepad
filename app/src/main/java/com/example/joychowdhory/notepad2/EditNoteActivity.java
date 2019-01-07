package com.example.joychowdhory.notepad2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.joychowdhory.notepad2.db.NotesDB;
import com.example.joychowdhory.notepad2.db.NotesDao;
import com.example.joychowdhory.notepad2.model.Note;

import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    private EditText inputNote;
    private NotesDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        inputNote = findViewById(R.id.input_note);

        dao = NotesDB.getInstance(this).notesDao();
    }

    public boolean onCreateOptionmenu(Menu menu) {

        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        //TODO: 7/1/2019 save note
        String text = inputNote.getText().toString();
        if(! text.isEmpty()) {
            long date=new Date().getTime();// get current system time
            Note note = new Note(text, date); //create new note
            dao.insertNotes(note); //insert and save note to database

            finish();
        }
    }
}
