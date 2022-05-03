package edu.agh.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddNoteActivity extends AppCompatActivity {

    Button btn_addNoteConfirmation;
    TextInputEditText tn_address, tn_noteContent;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        dbHelper = new DbHelper(this);


        btn_addNoteConfirmation = findViewById(R.id.btn_addNoteConfirmation);
        tn_address = findViewById(R.id.tn_address);
        tn_noteContent = findViewById(R.id.tn_noteContent);

        //button listener
        btn_addNoteConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if fields are not empty
                if(tn_address.getText().length() == 0 || tn_noteContent.getText().length() == 0){
                    Toast.makeText(AddNoteActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }else{

                    //create note object
                    Note note = new Note();

                    note.setAddress(String.valueOf(tn_address.getText()));
                    note.setNoteContent(String.valueOf(tn_noteContent.getText()));

                    //insert note to the database
                    if(dbHelper.addNote(note)){
                        //successful insert
                        Toast.makeText(AddNoteActivity.this, "Successful insert", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        //insert unsuccessful
                        Toast.makeText(AddNoteActivity.this, "An error occurred while saving your note", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}