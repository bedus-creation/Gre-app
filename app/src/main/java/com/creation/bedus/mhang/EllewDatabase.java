package com.creation.bedus.mhang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class EllewDatabase extends SQLiteOpenHelper{
    Context mContext;
    public EllewDatabase(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table data "
                +"(eng text primary key,"+
                "pronounce text,"+
                "meaning text,"+
                "example text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS data");
        onCreate(db);


    }

    public boolean insertContact  (String eng, String pronounce, String meaning,
                                   String example)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eng", eng);
        contentValues.put("pronounce",pronounce);
        contentValues.put("meaning", meaning);
        contentValues.put("example", example);
        db.insert("data", null, contentValues);
        return true;
    }
    public void getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Toast.makeText(mContext,res.getString(res.getColumnIndex("eng")),Toast.LENGTH_LONG).show();
            res.moveToNext();
        }
    }
}
