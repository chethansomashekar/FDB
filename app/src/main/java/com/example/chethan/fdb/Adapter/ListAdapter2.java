package com.example.chethan.fdb.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chethan.fdb.Data.Person;
import com.example.chethan.fdb.DataBase.DBAdapter;
import com.example.chethan.fdb.Fragments.Finished;
import com.example.chethan.fdb.R;

import java.util.ArrayList;

/**
 * Created by Chethan on 6/2/2017.
 */

public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.MyViewHolder> {
    private ArrayList<Person> List;
    Context context=null;
    Finished finished = new Finished();
    /* public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
*/
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public ImageButton imageButton;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            imageButton = (ImageButton) view.findViewById(R.id.check);
        }
    }
    /*public ListAdapter(FragmentActivity activity, ArrayList<Person> List) {
        this.List =  List;

    }*/
    public ListAdapter2(Context context,ArrayList<Person> List){
        this.context=context;
        this.List=List;
    }

    @Override
    public ListAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person, parent, false);

        return new ListAdapter2.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter2.MyViewHolder holder, final int position) {
        final Person person = List.get(position);
        holder.Name.setText( person.getName());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, person.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                int id = person.getID();
                DBAdapter db = new DBAdapter(context);
                boolean bool = db.updatedata2(id);

                if (bool == true) {
//                    db.updatedata(id);
                    finished.data();

                    retrieve2();
//                    db.updatedata(id);

                    Toast.makeText(context, "updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "not updated", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
    public void retrieve2()
    {
        List.clear();
        DBAdapter db=new DBAdapter(context);
        db.openDB();
        Cursor c=db.getAllPlayers2();
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            Person p=new Person(id,name);
            List.add(p);
        }
        notifyDataSetChanged();
//        Finished finished = new Finished();
        finished.data();

//        if(!(List.size()<1))
//        {
//
//            recyclerView.setAdapter(adapter);
//
//        }

        db.closeDB();
    }
    @Override
    public int getItemCount() {
        return List.size();
    }
}
