package com.example.chethan.fdb;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.chethan.fdb.Adapter.ListAdapter;
import com.example.chethan.fdb.DataBase.DBAdapter;

/**
 * Created by Chethan on 5/22/2017.
 */

public class EditPage extends AppCompatActivity {
    EditText editText;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        Button button1 = (Button)findViewById(R.id.submit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 editText = (EditText) findViewById(R.id.edit1);
                save(editText.getText().toString());
                onBackPressed();
            }
        });
    }
    private void save(String name)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.add(name);
//        adapter.notifyDataSetChanged();
        if(result>0)
        {
            editText.setText("");
            Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show();

        }else
        {
            Toast.makeText(this, "Not inserted", Toast.LENGTH_LONG).show();
            Snackbar.make(editText,"Unable To Save", Snackbar.LENGTH_SHORT).show();
        }

        db.closeDB();
//        adapter.notifyDataSetChanged();
    }
    }