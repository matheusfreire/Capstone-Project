package com.msf.myshops.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static final Object LOCK = new Object();
    private static AppExecutor sInstance;
    private final Executor dbIo;

    private AppExecutor(Executor execForDb) {
        this.dbIo = execForDb;
    }

    public static AppExecutor getInstance() {
        if (sInstance == null) {
            sInstance = new AppExecutor(Executors.newSingleThreadExecutor());
        }
        return sInstance;
    }

    public Executor getDbIo() {
        return dbIo;
    }
}