package com.creation.bedus.mhang;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;


public class ListenGreWord extends ActionBarActivity {
    int backButtonCount;
    Button listen_gre;
    SeekBar mSeekBar;
    TTSSpeak mTTs;
    Thread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_gre_word);

        mSeekBar=(SeekBar) findViewById(R.id.id_seekBarChooser);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==100){
                   Toast.makeText(getApplicationContext(),"z",Toast.LENGTH_SHORT).show();
               }else if(progress>96){
                   Toast.makeText(getApplicationContext(),"y",Toast.LENGTH_SHORT).show();
               }else if(progress>92){
                   Toast.makeText(getApplicationContext(),"x",Toast.LENGTH_SHORT).show();
               }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext()," the Length is "+seekBar.getProgress(), Toast.LENGTH_LONG).show();
            }
        });
        mTTs= new TTSSpeak(this,"Startted");
        mThread=new Thread(mTTs);

        backButtonCount = 0;
        listen_gre=(Button) findViewById(R.id.id_listen_gre);

        /* Adding action to the component */
        listen_gre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listen_gre.getText().toString().equals("PLAY")) {
                    listen_gre.setText("STOP");
                    ListenGreWord.this.startListening();
                } else {
                    mTTs.stop();
                    listen_gre.setText("PLAY");
                }
            }
        });
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        actionBar.setDisplayShowHomeEnabled(true);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void startListening() {
        mThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listen_gre_word, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
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
