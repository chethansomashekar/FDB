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

import com.example.chethan.fdb.Adapter.PendingAdapter;
import com.example.chethan.fdb.Data.DataList;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.R;

import java.util.ArrayList;

/**
 * Created by Chethan on 5/22/2017.
 */

public class Pending extends Fragment {
    private RecyclerView recyclerView;
    private FragmentActivity mActivity;
    private PendingAdapter adapter;
    private DBAdapter dbAdapter;
    private ArrayList<DataList> list;

    public Pending(ArrayList<DataList> pendingList, DBAdapter dbAdapter) {
        this.list=pendingList;
        this.dbAdapter=dbAdapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pending, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PendingAdapter(mActivity, list,dbAdapter);
        //((MainActivity)mActivity).pendingRetrieve();
        //recyclerView.setAdapter(adapter);
        retrievePending();
        adapter.notifyDataSetChanged();
        return rootView;
    }

    public void retrievePending()
    {
       list.clear();
        dbAdapter.openDB();
        Cursor c=dbAdapter.getPendingData();
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            DataList p=new DataList(id,name);
            list.add(p);
        }
        setAdapter();
        dbAdapter.closeDB();
    }

    public void setAdapter(){
        if(!(list.size()<1))
        {
            recyclerView.setAdapter(adapter);
        }
    }

}

