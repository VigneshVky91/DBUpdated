package com.example.admin.dbtest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StringBuffer data;
    EditText name,id;
    Button button,button2,update,delete;
    DBClass mydb;
    TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DBClass(this);

        id=(EditText)findViewById(R.id.editText);
        name=(EditText)findViewById(R.id.editText2);
        details = (TextView)findViewById(R.id.textView);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        update = (Button)findViewById(R.id.button3);
        delete = (Button)findViewById(R.id.button4);

        addData();
        viewData();
        updateData();
        deleteData();
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
    public void viewData()
    {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.viewData();
                data=new StringBuffer();
                if(res.getCount()!=0)
                {
                    while (res.moveToNext())
                    {
                        data.append("Id : "+res.getString(0)+"\n");
                        data.append("Name : "+res.getString(1)+"\n\n\n");
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No data to show",Toast.LENGTH_LONG).show();
                }
                details.setText(data);

            }
        });
    }
    public void updateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1=id.getText().toString();
                String name1=name.getText().toString();
                boolean res=mydb.updateData(id1,name1);

                if(res)
                {
                    Toast.makeText(MainActivity.this,"Updated Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Updated",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void deleteData()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String id1 = id.getText().toString();
                int res = mydb.deleteData(id1);
                Toast.makeText(MainActivity.this,res+"Rows deleted",Toast.LENGTH_LONG).show();
            }
        });

    }
}
