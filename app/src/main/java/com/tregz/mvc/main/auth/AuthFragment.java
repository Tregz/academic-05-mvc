package com.tregz.mvc.main.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseFragment;
import com.tregz.mvc.main.MainFragment;

public class AuthFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d(TAG, MainFragment.State.VIEW_INFLATING.name());
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    static {
        TAG = AuthFragment.class.getSimpleName();
    }
}
