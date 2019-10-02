package com.tregz.mvc.base;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, State.CREATED.name());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, State.STARTED.name());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, State.RESUMED.name());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, State.MENU_CREATED.name());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, State.PAUSED.name());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, State.STOPPED.name());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, State.DESTROYED.name());
    }

    private enum State {
        CREATED,
        STARTED,
        RESUMED,
        MENU_CREATED,
        PAUSED,
        STOPPED,
        DESTROYED
    }
}
