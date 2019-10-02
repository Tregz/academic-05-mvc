package com.tregz.mvc.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

class MainNavigation {
    private static final MainNavigation ourInstance = new MainNavigation();

    static MainNavigation getInstance() {
        return ourInstance;
    }

    private MainNavigation() {
    }

    void pop(AppCompatActivity activity) {
        controller(activity).popBackStack();
    }

    void navigate(AppCompatActivity activity, int action) {
        controller(activity).navigate(action);
    }

    Integer fragmentId(AppCompatActivity activity) {
        NavDestination destination = controller(activity).getCurrentDestination();
        return destination != null ? destination.getId() : null;
    }

    private NavController controller(AppCompatActivity activity) {
        return Navigation.findNavController(activity, 0); // TODO set viewId
    }
}
