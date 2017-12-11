package com.creation.bedus.mhang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizClass  extends EllewFetchData{

    ArrayList<String> mWords;
    ArrayList<String> mMeaning;

    public QuizClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.fetch();
        mWords = this.getEng();
        mMeaning = this.getMeaning();
        //Toast.makeText(context,"the length is"+mWords.size(),Toast.LENGTH_LONG).show();
    }
    public ArrayList<String> getQuestion(){
        int OptionNumber[]=getNumber(mWords.size()-1,4);
        ArrayList<String> mOption=new ArrayList<String>();

        int QuestionNumber=(int )Math.floor(Math.random()*4);

        /*
        * Adding Question before answer
         */
        mOption.add(mMeaning.get(OptionNumber[QuestionNumber]));


        mOption.add(mWords.get(OptionNumber[0]));
        mOption.add(mWords.get(OptionNumber[1]));
        mOption.add(mWords.get(OptionNumber[2]));
        mOption.add(mWords.get(OptionNumber[3]));

        return mOption;
    }
    public ArrayList<String> getAnswer(){
        int OptionNumber[]=getNumber(mWords.size(),4);
        Log.d("This is message"," this"+mWords.size());
        ArrayList<String> mOption=new ArrayList<String>();

        int QuestionNumber=(int )Math.floor(Math.random()*3);

        /*
        * Adding Question before answer
         */
        mOption.add(mWords.get(OptionNumber[QuestionNumber]));


        mOption.add(mMeaning.get(OptionNumber[0]));
        mOption.add(mMeaning.get(OptionNumber[1]));
        mOption.add(mMeaning.get(OptionNumber[2]));
        mOption.add(mMeaning.get(OptionNumber[3]));

        /*
        * Adding answer to the option
         */
        QuestionNumber=QuestionNumber+1;
        mOption.add(""+QuestionNumber);

        return mOption;
    }
    public int[] getNumber(int max,int Size){
        int i=0;
        int a[]=new int[Size];
        a[i]=(int) Math.floor(Math.random()*max);
        for(i=1;i<Size; i++){
            a[i]=(int) Math.floor(Math.random()*max);
            for(int j=i-1;j>=0;j--){
                if(a[i]!=a[j]){
                    //if the new value is unique accept this value and find next;
                }else{
                    i=i-1;
                }
            }
        }
        return a;
    }
}
