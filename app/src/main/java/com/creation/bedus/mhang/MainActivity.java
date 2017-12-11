package com.creation.bedus.mhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    ImageButton bListenWord,bQuiz,bSetting;
    Button bJreWord;
    WebView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new WebView(this);
        mView.loadUrl(getString(R.string.asset));
        setContentView(mView);
        /*
        * checking whether internet is available or not.
        * if available update with database
         */

        setContentView(R.layout.activity_main);
        bJreWord = (Button) findViewById(R.id.id_readDictionary);
        bListenWord = (ImageButton) findViewById(R.id.id_listenDictionary);
        bQuiz=(ImageButton) findViewById(R.id.id_Quiz);
        bSetting=(ImageButton) findViewById(R.id.id_setting);
        bQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getApplicationContext(),Quiz.class);
                startActivity(mIntent);
            }
        });
        bJreWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), GreWord.class);
                startActivity(mIntent);
            }
        });
        bListenWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), ListenGreWord.class);
                startActivity(mIntent);
            }
        });
        bSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getApplicationContext(),Setting.class);
                startActivity(mIntent);
            }
        });

    }
}
