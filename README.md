# MapNote_Exercise
## TASK 1 - onCreate method (DbHelper.java)
+ Fill in the createStatement String with SQL query
  + start with "CREATE TABLE "
  + use NOTES_TABLE variable as a table name
  + use ID_COLUMN, ADDRESS_COLUMN, DATE_COLUMN, CONTENT_COLUMN variables as column names
  + columns should be in this specific order (ID_COLUMN, ADDRESS_COLUMN, DATE_COLUMN, CONTENT_COLUMN)
  + for ID_COLUMN use INTEGER PRIMARY KEY AUTOINCREMENT
  + for DATE_COLUMN use DATETIME DEFAULT CURRENT_TIMESTAMP
+ Execute query using execSQL method on SQLiteDatabase

IMPORTANT: If you change anything in this method after running the app, you have to increment "version" number in the constructor above, so that onCreate method will be called again.

## TASK 2 - addNote method - add note to the database (DbHelper.java)
Fill in the addNote method so it returns true if the insert was successful and false if the insert was unsuccessful.  
  
Hints:  
+ Create ContentValues object and assign noteContent and address to the right columns.
+ You don't have to worry about id and date - the database will take care of it.
+ Use insert method on the db object (already created), second parameter (nullColumnHack) should be set to null.
+ To check if the insert was successful assign the operation (db.insert(...)) to long variable (-1 means unsuccessful insert).
+ Remember to close connection before return by using close method on the db object.

## TASK 3
After compliting task 1 and task 2 you should now be able to run the app, add notes and see the list of all added notes on the screen. However notes will be displayed for example as edu.agh.exercise.Note@c35e0. The task is to add a fragment of code that will make the list contain valuable information, such as the note content or the address.

## TASK 4* - Create a new activity for deleting notes
The easiest way to complete this task is to create a new Empty Activity, then add a textInputLayout for the id of the note to be deleted and a confirmation button.  
Hints:  
+ follow the example of the AddNoteActivity class
+ use deleteNoteById method implemented in the DbHelper class to delete note from the database
