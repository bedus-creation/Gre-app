package com.creation.bedus.mhang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class will fetch a word and meaning ,pronounce ,
 * example of that word
 */
public class FetchIndividual extends SQLiteOpenHelper{
    String eng=null;
    String pronounce=null;
    String meaning=null;
    String example=null;

    public FetchIndividual(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public String getPronounce(){
        return pronounce;
    }
    public String getMeaning(){
        return meaning;
    }
    public String getExample(){return example;}
    public void searchByEng(String eng){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where eng='"+eng+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            pronounce=res.getString(res.getColumnIndex("pronounce"));
            meaning=res.getString(res.getColumnIndex("meaning"));
            example=res.getString(res.getColumnIndex("example"));
            res.moveToNext();
        }
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
}
