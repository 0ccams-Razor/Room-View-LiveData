package com.example.gavin.stockcontrol;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

//AndroidViewModel is a subcass of ViewModel
//The difference is with AndroidViewModel we get passed the Application in the constructor
//We can use Application to get the context, as Application has the same life cycle as the application
//You can't store activity or fragment contexts as the can be destroyed leaving the ViewModel holding a null and causing a memory leak
//We need a context to pass to the database
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    //The activity should not have access to the ViewModel so we need
    //wrapper methods to provide access to repository functions

    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deletAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
