package com.creation.bedus.mhang;

import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Quiz extends ActionBarActivity {
    TableLayout mTableLayout;
    QuizClass mQuiz;
    ArrayList<String> mOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        actionBar.setDisplayShowHomeEnabled(true);

        mQuiz=new QuizClass(this,"tamang",null,1);

        mOption=new ArrayList<String>();
        mOption=mQuiz.getAnswer();
        mTableLayout=(TableLayout)findViewById(R.id.id_QuizTable);

        Toast.makeText(this,"the size is :"+mOption.size(),Toast.LENGTH_LONG).show();


        /* Adding the data to the table layout for user perception
        *  in row and column
         */
        setTableLayout();
    }
    private void refreshQuestion(){
        mOption=mQuiz.getAnswer();
        setTableLayout();
    }
    private void setTableLayout(){
        mTableLayout.removeAllViews();
        TableRow mQuestionRow=new TableRow(this);
        TextView mQuestion=new TextView(this);
        mQuestion.setTextColor(Color.WHITE);
        mQuestion.setText(mOption.get(0));

        mQuestionRow.addView(mQuestion);
        mTableLayout.addView(mQuestionRow);
        for(int i=1; i<5;i++){
            TableRow mTempRow=new TableRow(this);
            Button mButton=new Button(this);
            mButton.setTextColor(Color.WHITE);
            mButton.setText(mOption.get(i));
            //mButton.setWidth(mTableLayout.getWidth());
            final int finalI = i;
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(""+finalI);
                    //refreshQuestion();
                }
            });

            mTempRow.addView(mButton);
            mTableLayout.addView(mTempRow);
        }
    }
    private void checkAnswer(String  OptionNumber){
       if(mOption.get(5).equals(OptionNumber)){
           refreshQuestion();
       }else{
           Toast.makeText(this,"Sorry ! :-D \n Try Again",Toast.LENGTH_LONG).show();
       }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
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
