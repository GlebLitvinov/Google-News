package com.project.googlenews.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.project.googlenews.R;
import com.project.googlenews.model.FavoriteFragment;
import com.project.googlenews.model.Item;
import com.project.googlenews.model.ItemFragment;
import com.project.googlenews.model.MainFragment;
import com.project.googlenews.model.listener.IFavoriteListener;
import com.project.googlenews.model.listener.IGoToListener;

import android.view.MenuInflater;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends ActionBarActivity implements IGoToListener, IFavoriteListener {

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<Item> favorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favorites = new ArrayList<>();
        try {
            File file = new File(getFilesDir(), "favorite.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                Log.i("item", reader.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);

        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                // getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
//                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Initialize the first fragment when the application first loads.
        mainFragment = new MainFragment();
        favoriteFragment = new FavoriteFragment();
        favoriteFragment.setData(favorites);
        mainFragment.setData(this, this);
        if (savedInstanceState == null) {
            selectItem(0);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_search:
                // Show toast about click.
                Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setFavorite(Item item) {
        favorites.add(item);
    }

    @Override
    public void deleteFromFavorite(Item item) {
        favorites.remove(item);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private MainFragment mainFragment;
    private FavoriteFragment favoriteFragment;

    private FragmentManager manager;

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        if (manager == null) {
            manager = getFragmentManager();
            manager.beginTransaction()
                    .add(R.id.content_frame, favoriteFragment,"fav")
                    .hide(favoriteFragment)
                    .add(R.id.content_frame, mainFragment, "main")
                    .commit();

        }
        // Update the main content by replacing fragments
        switch (position) {
            case 0:

                manager.beginTransaction()
                        .hide(favoriteFragment)
                        .show(mainFragment)
                        .commit();
                break;
            case 1:
                favoriteFragment.setData(favorites);
                favoriteFragment.onResume();
                manager.beginTransaction()
                        .hide(mainFragment)
                        .show(favoriteFragment)
                        .commit();
                break;

            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
       /* if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
*/
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
//            setTitle(mScreenTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void goTo(String url) {
        Intent browserView = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserView);
    }

    private boolean internetConnectionExist() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            File file = new File(getFilesDir(), "favorite.txt");
            file.delete();
            file = new File(getFilesDir(), "favorite.txt");
            FileWriter outputStream = new FileWriter(file);
            for (Item item : favorites) {
                outputStream.write(item.toString());
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
