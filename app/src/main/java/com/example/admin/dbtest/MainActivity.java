package com.example.admin.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,id;
    Button button;
    DBClass mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DBClass(this);
        id=(EditText)findViewById(R.id.editText);
        name=(EditText)findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        addData();
    }
    public void addData()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1=id.getText().toString();
                String name1=name.getText().toString();
                boolean res = mydb.insertData(id1,name1);

                if(res)
                {
                    Toast.makeText(MainActivity.this,"Data added successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Data Failed to be added",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
