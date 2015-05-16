package com.example.limkokwing.limkokwingstudentapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by Jakaria on 16-May-15.
 */
public class SecondActivity extends MainActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
