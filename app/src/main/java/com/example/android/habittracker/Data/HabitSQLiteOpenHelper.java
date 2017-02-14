package com.example.android.habittracker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.habittracker.Data.HabitContract.HabitEntry.TABLE_NAME;

/**
 * Created by lixiaochi on 14/2/17.
 */

public class HabitSQLiteOpenHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "habit.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + TABLE_NAME;

    public HabitSQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                        HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_TIMES + " INTEGER NOT NULL DEFAULT 0, " +
                        HabitContract.HabitEntry.COLUMN_HABIT_COMPLETE_PERCENTAGE + " INTEGER NOT NULL DEFAULT 0)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
