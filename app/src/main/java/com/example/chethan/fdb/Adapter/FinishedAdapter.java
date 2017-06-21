package com.example.chethan.fdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chethan.fdb.Data.DataList;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.MainActivity;
import com.example.chethan.fdb.R;

import java.util.ArrayList;

/**
 * Created by Chethan on 6/2/2017.
 */

public class FinishedAdapter extends RecyclerView.Adapter<FinishedAdapter.MyViewHolder> {
    private ArrayList<DataList> finishedList;
    private Context context;
    private DBAdapter dbAdapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;
        private ImageButton imageButton;
        private MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            imageButton = (ImageButton) view.findViewById(R.id.check);
        }
    }

    public FinishedAdapter(Context context, ArrayList<DataList> finishedList, DBAdapter dbAdapter){
        this.context=context;
        this.finishedList=finishedList;
        this.dbAdapter=dbAdapter;
    }

    @Override
    public FinishedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.datalist, parent, false);
        return new FinishedAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FinishedAdapter.MyViewHolder holder, final int position) {
        final DataList dataList = finishedList.get(position);
        holder.Name.setText( dataList.getName());
       holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, dataList.getName() + "selected", Toast.LENGTH_SHORT).show();
                int id = dataList.getID();
                boolean bool = dbAdapter.updateFinishedData(id);
                ((MainActivity)context).finishedRetrieve();
                if (bool) {
                    Toast.makeText(context, "updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "not updated", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return finishedList.size();
    }
}
