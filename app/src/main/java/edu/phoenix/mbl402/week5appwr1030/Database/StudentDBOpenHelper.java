package edu.phoenix.mbl402.week5appwr1030.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CollegeStudent.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STUDENTS = "STUDENTS";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_CLASS_ID = "CLASS_ID";
    public static final String COLUMN_CLASS_NAME = "CLASS_NAME";
    public static final String COLUMN_POINT_GRADE = "POINT_GRADE";
    public static final String COLUMN_LETTER_GRADE = "LETTER_GRADE";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME,
            COLUMN_CLASS_ID,
            COLUMN_CLASS_NAME,
            COLUMN_POINT_GRADE,
            COLUMN_LETTER_GRADE

    };

    public static final String SQL_TABLE_CREATE =
            "CREATE TABLE " + TABLE_STUDENTS + " ("+
                    COLUMN_ID + " INTEGER NOT NULL, " +
                    COLUMN_FIRST_NAME + " TEXT, "+
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_CLASS_ID + " INTEGER NOT NULL, " +
                    COLUMN_CLASS_NAME + " TEXT, " +
                    COLUMN_POINT_GRADE + " INTEGER NOT NULL, " +
                    COLUMN_LETTER_GRADE + " TEXT " +
                    ")";
    private static final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_STUDENTS;



    public StudentDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_TABLE_DROP);
        sqLiteDatabase.execSQL(SQL_TABLE_CREATE);

    }
}
