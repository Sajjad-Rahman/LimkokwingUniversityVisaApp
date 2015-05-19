package com.example.limkokwing.limkokwingstudentapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static WebView webView;
    private String studentID;
    private String studentPassword;
    private boolean loginSuccess;

    Fragment fragment2 = null;
    FragmentManager fragmentManager;
    int position2;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        // Set Pinch to Zoom controls
        webView.getSettings().setBuiltInZoomControls(true);
        // Hide the Zoom controls in the display
        webView.getSettings().setDisplayZoomControls(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onNavigationDrawerItemSelected (int position) {
        switch (position) {
            case 0:
                if (loginSuccess) {
                    fragment2 = new FragmentSevenActivity();
                } else {
                    fragment2 = new FragmentFirstActivity();
                }
                break;
            case 1:
                if (loginSuccess){
                    fragment2 = new FragmentSecondActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Please Log In First", Toast.LENGTH_SHORT).show();
                    fragment2 = new FragmentFirstActivity();
                }
                break;
            case 2:
                if (loginSuccess){
                    fragment2 = new FragmentThirdActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Please Log In First", Toast.LENGTH_SHORT).show();
                    fragment2 = new FragmentFirstActivity();
                }
                break;
            case 3:
                webView.loadUrl("www.google.com");
                fragment2 = new FragmentFourActivity();
                break;
            case 4:
                fragment2 = new FragmentFifthActivity();
                break;
            case 5:
                fragment2 = new FragmentSixthActivity();
                break;
        }
        position2 = position;
        // update the main content by replacing fragments
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
        fragmentManager.beginTransaction().add(PlaceholderFragment.newInstance(position + 1), "").commit();
    }

    public void onSectionAttached (int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar () {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        if (! mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance (int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment () {
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach (Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void buttonLogin (View view) {
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        studentID = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        studentPassword = editText2.getText().toString();

        if (studentID.equals("1111") && studentPassword.equals("2222")) {
            loginSuccess = true;
            Toast.makeText(MainActivity.this, "Log In Successfully", Toast.LENGTH_SHORT).show();
            fragment2 = new FragmentSevenActivity();
            fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
            //fragmentManager.beginTransaction().add(PlaceholderFragment.newInstance(position + 1), "").commit();
        } else {
            Toast.makeText(MainActivity.this, "Wrong Input, Try Again", Toast.LENGTH_SHORT).show();
            editText1.setText("");
            editText2.setText("");
        }
    }

    public void buttonLogout (View view) {
        loginSuccess = false;
        fragment2 = new FragmentFirstActivity();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
        Toast.makeText(MainActivity.this, "Log Out Successfully", Toast.LENGTH_SHORT).show();
    }

    public void buttonVisa (View view) {
        fragment2 = new FragmentSecondActivity();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
        //fragmentManager.beginTransaction().add(PlaceholderFragment.newInstance(position2 + 1), "").commit();
    }
    public void buttonFees (View view){
        fragment2 = new FragmentThirdActivity();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
        //fragmentManager.beginTransaction().add(PlaceholderFragment.newInstance(position2 + 1), "").commit();
    }
    public void buttonVisaStatus (View view){
        fragment2 = new FragmentEightActivity();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
        //fragmentManager.beginTransaction().add(PlaceholderFragment.newInstance(position2 + 1), "").commit();
    }
    // TODO update the action bar title. in two place, one when locked 2 transition. another when clicks button inside login screen
    public void buttonCancelVisa (View view){
        fragment2 = new FragmentNineActivity();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
        //fragmentManager.beginTransaction().add(PlaceholderFragment.newInstance(position2 + 1), "").commit();
    }

}
