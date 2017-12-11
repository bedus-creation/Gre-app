package com.creation.bedus.mhang;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hackers on 12/24/2016.
 */
public class SearchWord  {
    EllewFetchData db;
    String searchHint;
    public SearchWord(Context mContext,String hint){
        db=new EllewFetchData(mContext,"tamang",null,1);
        db.fetch();
        this.searchHint=hint;
    }
    public ArrayList getList(){
        ArrayList<String> hint=new ArrayList<String>();
        ArrayList<String > eng=db.getEng();
        searchHint= searchHint.toLowerCase();
        int len=searchHint.length();
        for (String each_word : eng) {
            if (each_word.toLowerCase().startsWith(searchHint)) {
                if(hint.toString().equals("")){
                    hint.clear();
                    hint.add(each_word);
                }else{
                    hint.add(each_word);
                }
            }
        }
        return hint;
    }
}
