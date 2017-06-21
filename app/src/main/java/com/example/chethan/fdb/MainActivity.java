package com.example.chethan.fdb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chethan.fdb.Data.DataList;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.Fragments.Finished;
import com.example.chethan.fdb.Fragments.Pending;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private DBAdapter dbAdapter=new DBAdapter(this);
    private Pending pending;
    private Finished finished;
    private ArrayList<DataList> pendingList=new ArrayList<>();
    private ArrayList<DataList>finishedList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditPage.class);
                startActivity(intent);
            }
        });
       // String editableText= getIntent().getStringExtra("editableText");
       // save(editableText);
    }
   /* private void save(String name) {
//        dbAdapter=new DBAdapter(this);
        dbAdapter.openDB();
        long result=dbAdapter.add(name);
        if(result>0)
        {
           // editText.setText("");
            Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this, "Not inserted", Toast.LENGTH_LONG).show();
        }

        dbAdapter.closeDB();
    }*/
    public void pendingRetrieve () {
            pendingList.clear();
            dbAdapter.openDB();
            Cursor c = dbAdapter.getPendingData();
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String name = c.getString(1);
                DataList p = new DataList(id, name);
               pendingList.add(p);
            }
             dbAdapter.closeDB();
        mViewPager.setAdapter(mSectionsPagerAdapter);
        }
    public void finishedRetrieve() {
        finishedList.clear();
        dbAdapter.openDB();
        Cursor c = dbAdapter.getFinishedData();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            DataList p = new DataList(id, name);
          finishedList.add(p);
        }
        dbAdapter.closeDB();
       mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                     pending = new Pending(pendingList,dbAdapter);
                    return pending;
                case 1:
                     finished = new Finished(dbAdapter,finishedList);
                    return finished;
            }
            return null;
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PENDING";
                case 1:
                    return "FINISHED";
            }
            return null;
        }
    }
}
