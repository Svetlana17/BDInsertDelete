package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.db.Student;
import com.example.user.myapplication.db.StudentDao;

import java.util.List;


public class StudentAdapter  extends  RecyclerView.Adapter<StudentAdapter.StudentHolder>

{

    private List<Student> mStudent;
    private LayoutInflater mInflater;
    private AppCompatActivity mAppCompatActivity;

    public StudentAdapter(List<Student> students, LayoutInflater inflater, AppCompatActivity activity) {
        mStudent = students;
        mInflater = inflater;
        mAppCompatActivity = activity;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.student_item, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {
        Student student = mStudent.get(position);
        holder.bindStudent(student);
    }

    @Override
    public int getItemCount() {
        return mStudent.size();
    }

    public void setStudent(List<Student> student) {
        mStudent = student;
        notifyDataSetChanged();
    }
    class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mLastName;
        private TextView mFirstName;
        private Student student;


        public StudentHolder(View itemView) {
            super(itemView);
            mLastName = (TextView) itemView.findViewById(R.id.last_name);
            mFirstName = (TextView) itemView.findViewById(R.id.first_name);
            itemView.setOnClickListener(this);

        }

        public void bindStudent(Student student) {
            this.student = student;
            mLastName.setText(student.getFamily());
            mFirstName.setText(student.getName());
        }

        @Override
        public void onClick(View view) {
            Student student = mStudent.get(getLayoutPosition());
            Long studentId = student.getId();
            ((MainActivity)mAppCompatActivity).getStudentDao().deleteByKey(studentId);
            ((MainActivity)mAppCompatActivity).updateStudent();
        }

    }
}