package edu.phoenix.mbl402.week5appwr1030;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import edu.phoenix.mbl402.week5appwr1030.Database.StudentDataSource;
import edu.phoenix.mbl402.week5appwr1030.Model.Student;

public class MainActivity extends AppCompatActivity {

    public static final String STUDENT_ID = "ID";

    private StudentDataSource dataSource;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new StudentDataSource(getApplicationContext());
        dataSource.open();
    }

    protected void onResume() {
        super.onResume();
        dataSource.open();

        students = dataSource.getAllStudents();
        StudentListAdaptor adapter = new StudentListAdaptor(this,R.layout.list_student, students);

        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, StudentEdit.class);

                Student student = students.get(position);
                intent.putExtra(STUDENT_ID, student.getId());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    public void onButtonClickAdd (View view) {
        Intent intent = new Intent(this, StudentEdit.class);
        intent.putExtra(STUDENT_ID,Student.ID_INVALID);
        startActivity(intent);
        // Log.i("Week4App", "add button click");
    }
}
