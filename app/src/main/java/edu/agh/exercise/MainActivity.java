package edu.agh.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_addNote, btn_refreshList;
    ListView lv_Notes;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addNote = findViewById(R.id.btn_addNote);
        btn_refreshList = findViewById(R.id.btn_refreshList);
        lv_Notes = findViewById(R.id.lv_Notes);

        dbHelper = new DbHelper(this);

        //fill list view with notes from the database
        fillNoteListView();

        //addNote button listener
        btn_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //start AddNote Activity
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        //refreshList button listener
        btn_refreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillNoteListView();
            }
        });
    }

    private void fillNoteListView(){
        //get list of notes from the database
        List<Note> noteList = dbHelper.getAllNotes();

        //create String table with notes
        String[] values = new String[noteList.size()];
        int i = 0;
        for(Note note : noteList){
            values[i] = note.toString();
            i += 1;
        }

        //fill listView with values from values array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                values);
        lv_Notes.setAdapter(adapter);
    }
}