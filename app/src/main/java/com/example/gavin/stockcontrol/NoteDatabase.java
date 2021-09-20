package com.example.gavin.stockcontrol;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//Tell Room to setup a database
//More entities can be added inside the {}
//If we update the database we increase the version number and provide a migration strategy

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    //Room takes care of the code
    private static NoteDatabase instance;

    //Room takes care of the code
    public abstract NoteDOA noteDao();


    //Create a singletone instance of the database, thread safe with synchronized
    static NoteDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (NoteDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .fallbackToDestructiveMigration()
                            //popluates the database with some test settings
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    //has to be static so we  can access it from getInstance
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDOA noteDao;

        private PopulateDbAsyncTask(NoteDatabase db){
            //Possible because on create was called after the database was created
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }


}
