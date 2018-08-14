package edu.phoenix.mbl402.week5appwr1030.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.phoenix.mbl402.week5appwr1030.Model.Student;

public class StudentDataSource {

    private static SQLiteOpenHelper dbOpenHelper = null;
    private static SQLiteDatabase db;

    public StudentDataSource (Context context) {

        if (dbOpenHelper == null) {
            dbOpenHelper = new StudentDBOpenHelper(context);
        }

    }
    public void open() {
        db = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        dbOpenHelper.close();
    }

    private List<Student> studentListFromCursor(Cursor cursor) {
        List<Student> students = new ArrayList<Student>();
        if (cursor.moveToFirst()) {
            do {
                Student stud = new Student();
                stud.setId(cursor.getLong(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_ID)));
                stud.setFirstName(cursor.getString(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_FIRST_NAME)));
                stud.setLastName(cursor.getString(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_LAST_NAME)));
                stud.setClassID(cursor.getInt(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_CLASS_ID)));
                stud.setClassName(cursor.getString(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_CLASS_NAME)));
                stud.setPointGrade(cursor.getInt(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_POINT_GRADE)));
                stud.setLetterGrade(cursor.getString(cursor.getColumnIndex(StudentDBOpenHelper.COLUMN_LETTER_GRADE)));
                students.add(stud);
            } while (cursor.moveToNext());
        }

        return students;
    }

    private ContentValues valuesFromStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(StudentDBOpenHelper.COLUMN_FIRST_NAME, student.getFirstName());
        values.put(StudentDBOpenHelper.COLUMN_LAST_NAME, student.getLastName());
        values.put(StudentDBOpenHelper.COLUMN_CLASS_ID, student.getClassID());
        values.put(StudentDBOpenHelper.COLUMN_CLASS_NAME, student.getClassName());
        values.put(StudentDBOpenHelper.COLUMN_POINT_GRADE, student.getPointGrade());
        values.put(StudentDBOpenHelper.COLUMN_LETTER_GRADE, student.getLetterGrade());
        return values;
    }

    public Student addStudent(Student student) {
        ContentValues values = valuesFromStudent(student);
    long id = db.insert(StudentDBOpenHelper.TABLE_STUDENTS, null, values);
        student.setId(id);
        return student;
    }

   public boolean updateStudent (Student student){
        String where = StudentDBOpenHelper.COLUMN_ID + "=" + student.getId();
        ContentValues values = valuesFromStudent(student);
        int cnt = db.update(StudentDBOpenHelper.TABLE_STUDENTS,values,where,null);
        return (cnt == 1);
   }

    public boolean deleteStudent(long id){
        String where = StudentDBOpenHelper.COLUMN_ID + "=" + id;
        int cnt = db.delete(StudentDBOpenHelper.TABLE_STUDENTS,where,null);
        return (cnt == 1 );

    }

    public List<Student> getAllStudents(){
        Cursor cursor = db.query(StudentDBOpenHelper.TABLE_STUDENTS,StudentDBOpenHelper.ALL_COLUMNS,null,null,null,null, null);
        return studentListFromCursor(cursor);
    }

    public Student getStudentbyId(long id) {
        String where = StudentDBOpenHelper.COLUMN_ID + "=" + id;
        Cursor cursor = db.query(StudentDBOpenHelper.TABLE_STUDENTS,StudentDBOpenHelper.ALL_COLUMNS,where, null,null,null,null);
        List<Student> students = studentListFromCursor(cursor);
        if (students.size() == 1) {
            return students.get(0);
        } else {
            return null;
        }
    }


}
