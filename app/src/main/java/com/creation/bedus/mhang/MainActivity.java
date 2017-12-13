package com.creation.bedus.mhang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * checking whether internet is available or not.
        * if available update with database
         */
        /*
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
        */
    }

    public void startListening(View view) {
        Toast.makeText(this,"Button Clicked !",Toast.LENGTH_LONG).show();
        /*Intent mIntent = new Intent(getApplicationContext(), ListenGreWord.class);
        startActivity(mIntent);
        */
    }
}
