package com.example.android.habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by lixiaochi on 14/2/17.
 */

public class HabitContract {

    /*Inner class that defines the table content of the habit table*/
    public static final class HabitEntry implements BaseColumns{
        //Table name
        public static final String TABLE_NAME = "habits";


        //column id
        public static final String _ID = BaseColumns._ID;

        //column name
        public static final String COLUMN_HABIT_NAME = "name";

        //column complete times
        public static final String COLUMN_HABIT_COMPLETE_TIMES = "completeTimes";

        //column complete times
        public static final String COLUMN_HABIT_COMPLETE_PERCENTAGE = "completePercentage";


    }
}
