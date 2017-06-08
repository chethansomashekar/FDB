package com.example.chethan.fdb.Fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chethan.fdb.Adapter.ListAdapter;
import com.example.chethan.fdb.Data.Person;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.MainActivity;
import com.example.chethan.fdb.R;
import java.util.ArrayList;

/**
 * Created by Chethan on 5/22/2017.
 */

public class Pending extends Fragment {
    RecyclerView recyclerView;
    FragmentActivity mActivity;
    DBAdapter dbAdapter;
    ListAdapter adapter;
    ArrayList<Person> List=new ArrayList<>();
    MainActivity main = new MainActivity();

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        Fragment currentFragment = getFragmentManager().findFragmentByTag("FRAGMENT");
////        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
////        fragTransaction.detach(currentFragment);
////        fragTransaction.attach(currentFragment);
////        fragTransaction.commit();
//        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
        setRetainInstance(true);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.pending, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(getActivity(), List);
//        MainActivity main = new MainActivity();

        retrieve();
////
        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Person person = List.get(position);
                Toast.makeText(getContext(), person.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                int id = person.getID();
//                Log.d("id", String.valueOf(person.getID()));
                DBAdapter db = new DBAdapter(getActivity());
                boolean bool = db.updatedata(id);
              //  adapter.notifyDataSetChanged();
                retrieve();

                if (bool == true) {
                    Toast.makeText(getContext(), "updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "not updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
//        final SwipeRefreshLayout mSwipeRefreshLayout =(SwipeRefreshLayout)rootView.findViewById(R.id.swipe);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Refresh items
//                refreshItems();
//            }
//
//
//
//                void refreshItems() {
//                    retrieve();
//
//                }
//            void onItemsLoadComplete() {
//                // Update the adapter and notify data set changed
//                // ...
//
//                // Stop refresh animation
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//
//
//            });
//
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

       retrieve();
    }

    public void retrieve()
    {
        List.clear();

        DBAdapter db=new DBAdapter(getActivity());
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

        adapter.notifyDataSetChanged();
/*
        if(!(List.size()<1))
        {

            recyclerView.setAdapter(adapter);

        }*/
         data();

        db.closeDB();
//        Finished finshed = new Finished();
//        finshed.data();

    }
    public void data(){
        if(!(List.size()<1))
        {
            recyclerView.setAdapter(adapter);
        }
    }

}

