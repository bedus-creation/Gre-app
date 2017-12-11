package com.creation.bedus.mhang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hackers on 12/24/2016.
 */
public class EllewFetchData extends SQLiteOpenHelper {
    Context mContext;
    public ArrayList<String> eng=new ArrayList<String>();
    public ArrayList<String> pronounce=new ArrayList<String>();
    public ArrayList<String> meaning=new ArrayList<String>();
    public ArrayList<String> example=new ArrayList<String>();
    public EllewFetchData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table data "
                +"(eng text primary key,"+
                "pronounce text,"+
                "meaning text,"+
                "example text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS data");
        onCreate(db);
    }
    /*
    * @param this function should be called to initialize the
    * the database.
     */
    public void fetch(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            eng.add(res.getString(res.getColumnIndex("eng")));
            pronounce.add(res.getString(res.getColumnIndex("pronounce")));
            meaning.add(res.getString(res.getColumnIndex("meaning")));
            example.add(res.getString(res.getColumnIndex("example")));
            res.moveToNext();
        }
    }
    public ArrayList<String> getEng(){
        return eng;
    }
    public ArrayList<String> getPronounce(){
        return pronounce;
    }
    public ArrayList<String> getMeaning(){
        return meaning;
    }
    public ArrayList<String> getExample(){
        return example;
    }
}
