package com.example.chethan.fdb.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chethan.fdb.Adapter.ListAdapter2;
import com.example.chethan.fdb.Data.Person;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.R;

import java.util.ArrayList;

/**
 * Created by Chethan on 5/22/2017.
 */

public class Finished extends Fragment {
    RecyclerView recyclerView;
//    FragmentActivity mActivity;
//    DBAdapter dbAdapter;

    ListAdapter2 adapter;
//    MainActivity main =new MainActivity();
    ArrayList<Person> List = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.finshed, container, false);
        Pending pending = new Pending();
        pending.getActivity();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter2(getActivity(), List);
//        Pending pending = new Pending();
//        pending.onResume();


        retrieve2();
        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
       retrieve2();

    }

    private void retrieve2() {
        List.clear();

        DBAdapter db = new DBAdapter(getActivity());
        db.openDB();
//        Person person = List.get();
//        int id = person.getID();
//        db.updatedata(id);

        Cursor c = db.getAllPlayers2();
        // adapter.notifyDataSetChanged();


        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);

            Person p = new Person(id, name);

//            adapter.notifyDataSetChanged();
            List.add(p);


        }
        adapter.notifyDataSetChanged();
/*
        //CHECK IF ARRAYLIST ISNT EMPTY
        if (!(List.size() < 1)) {

            recyclerView.setAdapter(adapter);

        }*/
        data();

        db.closeDB();
    }

    public void data() {
        if (!(List.size() < 1)) {
            recyclerView.setAdapter(adapter);

        }
    }
}
