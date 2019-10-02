package com.tregz.mvc.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tregz.mvc.main.MainListener;

public abstract class BaseFragment extends Fragment {
    public static String TAG = BaseFragment.class.getSimpleName();
    protected MainListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, State.ATTACHED.name());
        try {
            listener = (MainListener) context;
        } catch (ClassCastException e) {
            String name = MainListener.class.getSimpleName();
            throw new ClassCastException(context.toString() + " must implement " + name);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, State.FRAGMENT_CREATED.name());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, State.VIEW_CREATED.name());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, State.ACTIVITY_CREATED.name());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, State.VIEW_DESTROYED.name());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, State.FRAGMENT_DESTROYED.name());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, State.DETACHED.name());
    }

    public enum State {
        ATTACHED,
        FRAGMENT_CREATED,
        VIEW_INFLATING,
        VIEW_CREATED,
        ACTIVITY_CREATED, // CREATED
        VIEW_DESTROYED,
        FRAGMENT_DESTROYED,
        DETACHED // DESTROYED
    }
}
