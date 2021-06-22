package com.example.anomia;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
}
