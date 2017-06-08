package com.example.chethan.fdb;

import android.content.Intent;
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
import com.example.chethan.fdb.Data.Person;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.Fragments.Finished;
import com.example.chethan.fdb.Fragments.Pending;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    DBAdapter db=new DBAdapter(this);
    ArrayList<Person> List=new ArrayList<>();
//    Pending pending = new Pending();
//    Finished finshed = new Finished();
//    public void MainActivity (){
//
//    }
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

   /* public void retrieve() {
        List.clear();

//            DBAdapter db=new DBAdapter(this);
        db.openDB();


        Cursor c=db.getAllPlayers();

        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            Person p=new Person(id,name);

            // adapter.notifyDataSetChanged();
            List.add(p);
            // adapter.notifyDataSetChanged();

        }

           *//* adapter.notifyDataSetChanged();

            if(!(List.size()<1))
            {

                recyclerView.setAdapter(adapter);

            }
*//*
           pending.data();
        db.closeDB();
    }

    public void retrieve2() {
        List.clear();

//            DBAdapter db=new DBAdapter(this);
        db.openDB();


        Cursor c=db.getAllPlayers();

        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            Person p=new Person(id,name);

            // adapter.notifyDataSetChanged();
            List.add(p);
            // adapter.notifyDataSetChanged();

        }

           *//* adapter.notifyDataSetChanged();

            if(!(List.size()<1))
            {

                recyclerView.setAdapter(adapter);

            }
*//*
           finshed.data();
        db.closeDB();
    }*/

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Pending pending = new Pending();

                    return pending;
                case 1:
                    Finished finished = new Finished();

                    return finished;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
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
        /*mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {

                // do this instead, assuming your adapter reference
                // is named mAdapter:
                Fragment frag = mAdapter.fragments[position];
                if (frag != null && frag instanceof FragmentTwo) {
                    ((FragmentTwo)frag).sendGetRequest();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {  }
        });*/
    }
}
