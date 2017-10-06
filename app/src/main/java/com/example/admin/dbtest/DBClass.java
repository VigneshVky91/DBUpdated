package com.example.admin.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBClass extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="ID";
    public static final String COL2="NAME";

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(" create table "+TABLE_NAME+" ("+COL1+" text, "+COL2+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String id,String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL1,id);
        cv.put(COL2,name);

        long res = db.insert(TABLE_NAME,null,cv);

        if(res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor viewData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID",id);
        cv.put("Name",name);

        int res=db.update(TABLE_NAME,cv,"ID = ?",new String[] { id } );
        if(res==0)
        {
            return false;
        }
        return true;
    }
    public int deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME,"ID = ?", new String[]{ id } );
        return res;
    }
}
