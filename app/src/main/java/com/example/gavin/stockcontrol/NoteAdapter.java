package com.example.gavin.stockcontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//<NoteAdapter.NoteHolder> tells noteadapter which adapter to use
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    //You shouldn't call method with a null reference with in the adapter so well
    //initalise notes as an ArrayList, we could have added null checks around the methods instead
    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    //parent is the recyclerView
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        //the inflate parameters are from recycler view
        return new NoteHolder(itemView);
    }

    @Override
    //Taking care of getting the note java objects into the views of our noteholder
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    //Get the note data from ListData when onChanged is fired
    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        //The itemView is the card itself
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
