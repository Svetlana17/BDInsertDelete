package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.db.DaoSession;
import com.example.user.myapplication.db.Student;
import com.example.user.myapplication.db.StudentDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFab;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private RecyclerView mRecyclerView;

    private StudentAdapter adapter;
    private List<Student> mStudent;


    public StudentDao getStudentDao() {
        return mStudentDao;
    }

    private StudentDao mStudentDao;
    private Query<Student> studentQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        DaoSession daoSession= ((StudentApp)getApplication()).getDaoSession();
        mStudentDao = daoSession.getStudentDao();
        studentQuery = mStudentDao.queryBuilder().build();
        updateStudent();
    }

    public void updateStudent() {
        mStudent = studentQuery.list();
        adapter.setStudent(mStudent);
    }



    private void addStudent(){

        String firstname = mFirstNameEditText.getText().toString();
        String lastname = mLastNameEditText.getText().toString();
        mFirstNameEditText.setText("");
        mLastNameEditText.setText("");
        mFirstNameEditText.clearFocus();
        mLastNameEditText.clearFocus();

        if (firstname.trim().equals("") || lastname.trim().equals("")) {
            Toast.makeText(MainActivity.this, "Фамилия или имя не заполнены", Toast.LENGTH_SHORT).show();
            return;
        }

        Student student = new Student();
        student.setFamily(firstname);
        student.setName(lastname);


        mStudentDao.insert(student);
        updateStudent();


    }

    private void setupViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFirstNameEditText =(EditText)findViewById(R.id.first_name);
        mLastNameEditText= (EditText) findViewById(R.id.last_name);

        mStudent = new ArrayList<>();
        adapter = new StudentAdapter(mStudent, getLayoutInflater(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();

            }
        });
    }



    }
