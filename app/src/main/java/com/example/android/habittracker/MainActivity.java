package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.habittracker.Data.HabitContract;
import com.example.android.habittracker.Data.HabitContract.HabitEntry;
import com.example.android.habittracker.Data.HabitSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    public HabitSQLiteOpenHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitSQLiteOpenHelper(this);
        insertHabit();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_TIMES,
                HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_PERCENTAGE
        };

        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        TextView displayView = (TextView) findViewById(R.id.test_text_view);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitContract.HabitEntry._ID + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_TIMES + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_PERCENTAGE + "\n"
            );

            //FIGURE OUT THE INDEX OF EACH COLUMN
            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int timesColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_TIMES);
            int percentageColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_PERCENTAGE);
            //Interate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                //Use that index to extract the String or Int value of the word
                //at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentTimes = cursor.getInt(timesColumnIndex);
                int currentPercentage = cursor.getInt(percentageColumnIndex);
                //Display the values from each colun of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentTimes + " - " +
                        currentPercentage));
            }
        } finally {
            cursor.close();
        }
    }

    private void insertHabit(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Walking for 30 mins");
        values.put(HabitEntry.COLUMN_HABIT_COMPLETE_TIMES, 2);
        values.put(HabitEntry.COLUMN_HABIT_COMPLETE_PERCENTAGE, 90);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

    }
}
