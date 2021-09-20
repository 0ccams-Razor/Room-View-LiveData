package com.example.gavin.stockcontrol;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDOA noteDao;
    private LiveData<List<Note>> allNotes;

    //ViewModel will pass an Application object
    //Application is a subclass of context, we can use it as the context to create our database instance
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        //Although this is an abstract class because we used Room.databaseBuilder
        // Room has subclassed the abstract class
        noteDao = database.noteDao();
        //Room has also subclasses this interface method
        allNotes = noteDao.getAllNotes();
    }

    //methods the repository exposes to the outside as an API
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deletAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    //Room will automatically execute LiveData operations on a background thread
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //Has to be static so it doesn't have a reference to the repository itself
    //which could cause a memory leak
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDOA noteDOA;

        //Because the class is static we can't access the repository directly
        //so we need to use a constructor
        private InsertNoteAsyncTask(NoteDOA noteDOA){
            this.noteDOA = noteDOA;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            //As the async task passes an array of arguments rather than one note we have to select the first element in the array
            noteDOA.insert(notes[0]);
            return null;
        }
    }


    //Has to be static so it doesn't have a reference to the repository itself
    //which could cause a memory leak
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDOA noteDOA;

        //Because the class is static we can't access the repository directly
        //so we need to use a constructor
        private DeleteAllNotesAsyncTask(NoteDOA noteDOA){
            this.noteDOA = noteDOA;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            //As the async task passes an array of arguments rather than one note we have to select the first element in the array
            noteDOA.deleteAllNotes();
            return null;
        }
    }

    //Has to be static so it doesn't have a reference to the repository itself
    //which could cause a memory leak
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDOA noteDOA;

        //Because the class is static we can't access the repository directly
        //so we need to use a constructor
        private UpdateNoteAsyncTask(NoteDOA noteDOA){
            this.noteDOA = noteDOA;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            //As the async task passes an array of arguments rather than one note we have to select the first element in the array
            noteDOA.update(notes[0]);
            return null;
        }
    }

    //Has to be static so it doesn't have a reference to the repository itself
    //which could cause a memory leak
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDOA noteDOA;

        //Because the class is static we can't access the repository directly
        //so we need to use a constructor
        private DeleteNoteAsyncTask(NoteDOA noteDOA){
            this.noteDOA = noteDOA;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            //As the async task passes an array of arguments rather than one note we have to select the first element in the array
            noteDOA.delete(notes[0]);
            return null;
        }
    }





}
