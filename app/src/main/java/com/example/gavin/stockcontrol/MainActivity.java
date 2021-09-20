package com.example.gavin.stockcontrol;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //If the recyclerView size doesn't change this makes it more efficent
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
       // noteViewModel = ViewModelProvider(this).get(NoteViewModel.class);
        //As getAllNotes is LiveData you can use the observe method
        //LiveData is lidecycle aware so it takes a copy of the context in the constructor
        //so it only provides data when appropriate
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            //Every time onchange is called, the adapter should be updated
            public void onChanged(List<Note> notes) {
                //updateRecycler
                adapter.setNotes(notes);

            }
        });

        //This is my second commit
    }
}
