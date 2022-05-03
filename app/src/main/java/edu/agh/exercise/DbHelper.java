package edu.agh.exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NOTES_TABLE = "Notes";
    public static final String ID_COLUMN = "NoteId";
    public static final String ADDRESS_COLUMN = "NoteAddress";
    public static final String DATE_COLUMN = "NoteDate";
    public static final String CONTENT_COLUMN = "NoteContent";

    public DbHelper(@Nullable Context context) {
        super(context, "exercise.db", null, 1);
    }

    /**
     * Create new database (called the first time database is accessed)
     * TASK 1 - onCreate method:
     *      Fill in the createStatement String with SQL query
     *          start with "CREATE TABLE "
     *          use NOTES_TABLE variable as a table name
     *          use ID_COLUMN, ADDRESS_COLUMN, DATE_COLUMN, CONTENT_COLUMN variables as column names
     *          columns should be in this specific order (ID_COLUMN, ADDRESS_COLUMN, DATE_COLUMN, CONTENT_COLUMN)
     *          for ID_COLUMN use INTEGER PRIMARY KEY AUTOINCREMENT
     *          for DATE_COLUMN use DATETIME DEFAULT CURRENT_TIMESTAMP
     *      Execute query using execSQL method on SQLiteDatabase
     *
     *      IMPORTANT: If you change anything in this method after running the app, you have to increment
     *      "version" number in the constructor above, so that onCreate method will be called again.
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE ";
    }


    //called when database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //not really right way to do it, because we will loose all the data

        //drop the table
        String statement = "DROP TABLE IF EXISTS " + NOTES_TABLE;
        db.execSQL(statement);

        //create new table
        onCreate(db);
    }


    //get list of all notes
    public List<Note> getAllNotes(){
        List<Note> resultList = new ArrayList<>();

        //get data from the database
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + NOTES_TABLE;

        //result set
        Cursor cursor = db.rawQuery(query, null);

        //check if there are any results
        if(cursor.moveToFirst()){
            //create new Note object for every row and put it into resultList
            do{
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setAddress(cursor.getString(1));
                note.setDate(cursor.getString(2));
                note.setNoteContent(cursor.getString(3));

                resultList.add(note);
            }while(cursor.moveToNext());

        }
        //close the cursor and the db
        cursor.close();
        db.close();
        return resultList;
    }

    /**
     * add note to the database
     * TASK 2 - addNote method:
     *      Fill in the addNote method so it returns true if the insert was successful and false if
     *      the insert was unsuccessful.
     *
     *      Hints:
     *          Create ContentValues object and assign noteContent and address to the right columns.
     *          You don't have to worry about id and date - the database will take care of it.
     *          Use insert method on the db object (already created), second parameter
     *              (nullColumnHack) should be set to null.
     *          To check if the insert was successful assign the operation (db.insert(...)) to long
     *              variable (-1 means unsuccessful insert).
     *          Remember to close connection before return by using close method on the db object.
     *
     * @param note Note object to be added to the database
     * @return boolean value that indicates whether the insert operation was successful
     */
    public boolean addNote(Note note){

        SQLiteDatabase db = this.getWritableDatabase();

        //YOUR CODE HERE

        //change according to the instructions
        return true;
    }


    /**
     * Deletes a Note with specified id from the database.
     * Returns false if the delete was successful.
     * Returns true if the delete was unsuccessful.
     *
     * @param id id of the note to be deleted
     * @return boolean value that indicates whether the delete operation was successful
     */
    public boolean deleteNoteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        //delete row from database where id column match given id
        long delete = db.delete(NOTES_TABLE, ID_COLUMN + "=?", new String[]{String.valueOf(id)});

        //close db
        db.close();

        //returns true if delete was successful or false if delete was unsuccessful
        return delete != -1;
    }
}
