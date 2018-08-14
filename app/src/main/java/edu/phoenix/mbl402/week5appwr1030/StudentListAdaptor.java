package edu.phoenix.mbl402.week5appwr1030;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.phoenix.mbl402.week5appwr1030.Model.Student;

public class StudentListAdaptor extends ArrayAdapter {

    private List<Student> students;

    public StudentListAdaptor(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);

        students = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_student, parent, false);
        }

        Student student = students.get(position);

        TextView idText = (TextView) convertView.findViewById(R.id.Id);
        idText.setText(Long.toString(student.getId()));

        TextView fullNameText = (TextView) convertView.findViewById(R.id.studentName);
        fullNameText.setText(student.getFullName());

        TextView classIDText = (TextView) convertView.findViewById(R.id.classID);
        classIDText.setText(Long.toString(student.getClassID()));

        TextView classNameText = (TextView) convertView.findViewById(R.id.className);
        classNameText.setText(student.getClassName());

        TextView classPointsText = (TextView) convertView.findViewById(R.id.classPoints);
        classPointsText.setText(Integer.toString(student.getPointGrade()));

        TextView classGradeText = (TextView) convertView.findViewById(R.id.classGrade);
        classGradeText.setText(student.getLetterGrade());

        return convertView;
    }




}
