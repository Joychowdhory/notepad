package com.example.joychowdhory.notepad2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.joychowdhory.notepad2.model.Note;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {


    @Insert (onConflict = OnConflictStrategy.REPLACE)  //if the note esit them replace it
    void insertNotes(Note note);

    @Delete
    void deleteNotes(Note note);

    @Update
    void updateNotes(Note note);
    //list all notes from database
    @Query("SELECT * FROM notes")
    List<Note> getNotes();

     @Query("SELECT * FROM notes WHERE id = :noteId")
         //get note by id
     Note getNoteByID(int noteId);

     @Query("DELETE FROM notes WHERE id = :noteId")
     void deleteNoteById(int noteId);

}
