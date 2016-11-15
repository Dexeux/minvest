package com.example.chrisg.minvest.drawer;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chrisg.minvest.account.Account_Activity;
import com.example.chrisg.minvest.R;
import com.example.chrisg.minvest.anal.AActivity;

/**
 * Created by chrisg on 11/12/16.
 */

public class DrawerActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private String[] mDrawerTitles;
    private ListView mDrawerList;
    private DrawerAdapter mAdapter;

    public void initDrawer(){

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mDrawerTitles = getResources().getStringArray(R.array.drawer);


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mAdapter = new DrawerAdapter(this,mDrawerTitles);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(getBaseContext(),Account_Activity.class);
                    startActivity(intent);
                    finish();
                }else if( i == 1){
                    Intent intent = new Intent(getBaseContext(),AActivity.class);
                    startActivity(intent);
                    finish();
                }else if (i == 2){
                    finish();
                }
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.open,
                R.string.close
        )

        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

    }
}
