package com.example.chethan.fdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chethan.fdb.DataBase.DBAdapter;

/**
 * Created by Chethan on 5/22/2017.
 */

public class EditPage extends AppCompatActivity {
    private EditText editText;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        Button button1 = (Button) findViewById(R.id.submit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.edit1);
                save(editText.getText().toString());
                Intent intent = new Intent(EditPage.this, MainActivity.class).putExtra("editableText", editText.getText().toString());
                startActivity(intent);
                finish();
                 onBackPressed();
            }
        });
    }

    private void save(String name) {
        dbAdapter=new DBAdapter(this);
        dbAdapter.openDB();
        long result=dbAdapter.add(name);
        if(result>0)
        {
            editText.setText("");
            Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this, "Not inserted", Toast.LENGTH_LONG).show();
        }

        dbAdapter.closeDB();
    }
}