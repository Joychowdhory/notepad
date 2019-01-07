package com.example.joychowdhory.notepad2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.joychowdhory.notepad2.adapters.NotesAdapter;
import com.example.joychowdhory.notepad2.db.NotesDB;
import com.example.joychowdhory.notepad2.db.NotesDao;
import com.example.joychowdhory.notepad2.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        init recyclerview

                   recyclerView=findViewById(R.id.notes_list);
                   recyclerView.setLayoutManager(new LinearLayoutManager(this));


//init fab Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             todo add new note
                 onAddNewNote();
            }
        });

        dao = NotesDB.getInstance(this).notesDao();
    }
    private void loadNotes() {

        this.notes = new ArrayList<>();
        List<Note> list = dao.getNotes(); // get all note from database

        this.notes.addAll(list);
        this.adapter = new NotesAdapter(this,notes);
        this.recyclerView.setAdapter(adapter);


    }

    private void onAddNewNote() {
        // TODO: 1/7/2019  start Edit activity
        startActivity(new Intent(this,EditNoteActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }
}
