package edu.phoenix.mbl402.week5appwr1030;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.phoenix.mbl402.week5appwr1030.Database.StudentDataSource;
import edu.phoenix.mbl402.week5appwr1030.Model.Student;

public class StudentEdit extends AppCompatActivity {

    private Student student = null;
    private List<Student> allStudents = null;
    private StudentDataSource dataSource;
    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataSource = new StudentDataSource(getApplicationContext());
        dataSource.open();
        allStudents = dataSource.getAllStudents();

        setContentView(R.layout.acticity_student_edit);

        View rv = (View) findViewById(R.id.rootView);

        long id = getIntent().getLongExtra(MainActivity.STUDENT_ID, Student.ID_INVALID);

        if (id == Student.ID_INVALID) {
            student = new Student();
            setEditModeAdd();
        } else {
            setEditModeView();
            student = dataSource.getStudentbyId(id);
            inflateStudentRecord(rv, student);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void inflateStudentRecord(View rv, Student student) {

        TextView tv = (TextView) rv.findViewById(R.id.studentID);
        tv.setText(String.valueOf(student.getId()));

        EditText et = (EditText) rv.findViewById(R.id.firstNameET);
        et.setText(student.getFirstName());

        et = (EditText) rv.findViewById(R.id.lastNameET);
        et.setText(student.getLastName());

        et = (EditText) rv.findViewById(R.id.classIDET);
        et.setText(String.valueOf(student.getClassID()));

        et = (EditText) rv.findViewById(R.id.classNameET);
        et.setText(student.getClassName());

        et = (EditText) rv.findViewById(R.id.pointGradeET);
        et.setText(String.valueOf(student.getPointGrade()));

        et = (EditText) rv.findViewById(R.id.letterGradeET);
        et.setText(student.getLetterGrade());

    }

    private Student retrieveStudentRecord(View rv) {

        try {
            Student student = new Student();

            EditText et = (EditText) rv.findViewById(R.id.firstNameET);
            student.setFirstName(et.getText().toString());

            et = (EditText) rv.findViewById(R.id.lastNameET);
            student.setLastName(et.getText().toString());

            et = (EditText) rv.findViewById(R.id.classIDET);
            student.setClassID(Integer.parseInt(et.getText().toString()));

            et = (EditText) rv.findViewById(R.id.classNameET);
            student.setClassName(et.getText().toString());

            et = (EditText) rv.findViewById(R.id.pointGradeET);
            student.setPointGrade(Integer.parseInt(et.getText().toString()));

            et = (EditText) rv.findViewById(R.id.letterGradeET);
            student.setLetterGrade(et.getText().toString());

            TextView tv = (TextView) findViewById(R.id.studentID);

            try {
                student.setId(Integer.parseInt(tv.getText().toString()));
            } catch (NumberFormatException e) {
                student.setId(-1);
            }

            return student;
        } catch (Exception e) {
            Toast.makeText(this, "Invalid entry found!", Toast.LENGTH_LONG).show();
            return null;
        }

    }

    public void onAddStudent(View v) {
        View rv = (View) v.getRootView();

        student = retrieveStudentRecord(rv);
        if (student != null) {
            student = dataSource.addStudent(student);

            TextView tv = (TextView) findViewById(R.id.studentID);
            tv.setText(String.valueOf(student.getId()));
        }
        finish();
    }


    public void onUpdateStudent(View v) {
        String msg = "Failed";
        View rv = v.getRootView();

        student = retrieveStudentRecord(rv);
        if (student != null && dataSource.updateStudent(student)) {
            msg = "Successful";
        }
        Toast.makeText(StudentEdit.this, "Record Update " + msg, Toast.LENGTH_SHORT).show();
    }

    public void onViewStudent(View view) {
        finish();
    }

    public void onDeleteStudent(View view) {
        new AlertDialog.Builder(StudentEdit.this)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure you want to delete this Record?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dataSource.deleteStudent(student.getId());
                        finish();
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private void setEditModeAdd () {
        Button updateButton = (Button) findViewById(R.id.buttonUpdateStudent);
        updateButton.setEnabled(false);

        Button deleteButton = (Button) findViewById(R.id.buttonDeleteStudent);
        deleteButton.setEnabled(false);

        Button viewButton = (Button) findViewById(R.id.buttonViewStudent);
        viewButton.setEnabled(false);
    }

    private void setEditModeView () {

        Button addButton = (Button) findViewById(R.id.buttonAddStudent);
        addButton.setEnabled(false);
    }
}
