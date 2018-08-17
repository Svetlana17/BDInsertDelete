package com.example.user.myapplication;

import android.app.Application;

import com.example.user.myapplication.db.DaoMaster;
import com.example.user.myapplication.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class StudentApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper= new DaoMaster.DevOpenHelper(this, "STUDENT");
        Database database = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession() {

        return daoSession;
    }
}
