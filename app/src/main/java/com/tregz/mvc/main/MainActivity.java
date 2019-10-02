package com.tregz.mvc.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseActivity;
import com.tregz.mvc.main.auth.AuthFragment;

public class MainActivity extends BaseActivity implements MainListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment()).commitNow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    item.setIcon(R.drawable.ic_person);
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new AuthFragment(), AuthFragment.TAG)
                            .addToBackStack(AuthFragment.TAG).commit();
                    item.setIcon(R.drawable.ic_arrow_back);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    static {
        TAG = MainActivity.class.getSimpleName();
    }
}
