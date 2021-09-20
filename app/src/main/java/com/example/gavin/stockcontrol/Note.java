package com.example.gavin.stockcontrol;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Room annotation
@Entity(tableName = "note_table")
public class Note {
    //Room will create columns for these member variables
    @PrimaryKey(autoGenerate = true)
    //You can use @ignore to stop the variabl being added as a database column
    //You can change the column name using @ColumnInfo(name = "priority_column")
    private int id;
    private String title;
    private String description;
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
