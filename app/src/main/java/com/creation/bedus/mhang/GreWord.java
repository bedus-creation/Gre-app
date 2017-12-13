/*
*  this activity search the gre word stored in the database
*/
package com.creation.bedus.mhang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class GreWord extends ActionBarActivity {
    int backButtonCount;
    EllewFetchData db;
    EditText searchDataComponent;
    ImageButton SeachEnableButton;
    TableLayout mTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gre_word);
        backButtonCount=0;
        /* Initializing the database */
        db=new EllewFetchData(this,"tamang",null,1);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);

        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v= inflater.inflate(R.layout.search,null);
        actionBar.setCustomView(v);


        /* Initilizing the component */
        mTable=(TableLayout) findViewById(R.id.id_hintTable);

        searchDataComponent=(EditText) findViewById(R.id.id_searchQuery);
        SeachEnableButton=(ImageButton) findViewById(R.id.id_searchEnableButton);
        SeachEnableButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   searchDataComponent.setVisibility(searchDataComponent.getVisibility()==View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
               }
        });

        searchDataComponent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String data=searchDataComponent.getText().toString();String mHint = searchDataComponent.getText().toString();
                if(mHint.equals("")){
                    mTable.removeAllViews();
                }else{
                    searchProcess(mHint);

                }
            }
        });
    }
    private void searchProcess(String mHint) {
        SearchWord mSearch = new SearchWord(getApplicationContext(), mHint);
        ArrayList<String> hint = mSearch.getList();
        mTable.removeAllViews();
        if (hint.size() == 1) {
            mTable.removeAllViews();
            DisplayWord(hint.get(0), Color.BLUE);
            FetchIndividual fidb=new FetchIndividual(getApplicationContext(),"tamang",null,1);
            fidb.searchByEng(hint.get(0));
            DisplayWord(fidb.getMeaning(), Color.WHITE);
            DisplayWord(fidb.getPronounce(), Color.WHITE);
            DisplayWord(fidb.getExample(), Color.WHITE);
        } else {
            mTable.removeAllViews();
            for (final String each_hint:hint) {
                TableRow mTableRow=new TableRow(getApplicationContext());
                Button mButton=new Button(getApplicationContext());
                mButton.setBackgroundColor(Color.WHITE);
                mButton.setTextColor(Color.BLACK);
                mButton.setText(each_hint);
                mButton.setWidth(mTable.getWidth());
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTable.removeAllViews();
                        View view=getCurrentFocus();
                        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                        DisplayWord(each_hint, Color.BLUE);
                        FetchIndividual fidb = new FetchIndividual(getApplicationContext(), "tamang", null, 1);
                        fidb.searchByEng(each_hint);
                        DisplayWord(fidb.getMeaning(), Color.WHITE);
                        DisplayWord(fidb.getPronounce(), Color.WHITE);
                        DisplayWord(fidb.getExample(), Color.WHITE);
                    }
                });
                mTableRow.addView(mButton);
                mTable.addView(mTableRow);
            }
        }
    }
    public void DisplayWord(String word,int mColor){
        TableRow mTableRowWord=new TableRow(getApplicationContext());
        TextView mText = new TextView(getApplicationContext());
        mText.setTextSize(30f);
        mText.setTextColor(mColor);
        mText.setWidth(mTable.getWidth());
        mText.setText(word);
        mTableRowWord.addView(mText);
        mTable.addView(mTableRowWord);
    }

    @Override
    public void onBackPressed(){
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gre_word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
