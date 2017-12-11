package com.creation.bedus.mhang;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Locale;

public class TTSSpeak implements Runnable {
    String text;
    TextToSpeech tts;
    EllewFetchData efdb;
    QuizClass mQuiz;

    public  TTSSpeak(Context mContext,String text){
        this.text=text;
        efdb=new EllewFetchData(mContext,"tamang",null,1);
        efdb.fetch();
        mQuiz=new QuizClass(mContext,"tamang",null,1);
        tts=new TextToSpeech(mContext,new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status!=TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                    tts.setPitch(0.6f);
                    tts.setSpeechRate(0.7f);
                }
            }
        });
    }
    @Override
    public void run() {
        while(true){
            ArrayList<String> mOption=new ArrayList<String>();
            mOption=mQuiz.getAnswer();
            speakLoud("What is the meaning of the word \""+mOption.get(0)+"\" ?");
            tts.playSilence(2000, TextToSpeech.QUEUE_ADD, null);
            speakLoud("The options are :");
            tts.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
            speakLoud("Option 1. \"" + mOption.get(1));
            tts.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
            speakLoud("Option 2. \"" + mOption.get(2));
            tts.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
            speakLoud("Option 3. \"" + mOption.get(3));
            tts.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
            speakLoud("Option 4. \"" + mOption.get(4));
            tts.playSilence(4000, TextToSpeech.QUEUE_ADD, null);
            speakLoud("The answer is. \"" + mOption.get(5));
            tts.playSilence(4000, TextToSpeech.QUEUE_ADD, null);
            try{
                Thread.sleep(20000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void linerPlay(){
        String text = "Hello good Evening bedu ";
        speakLoud(text);
        for (int i = 0; i < efdb.getEng().size() && i<100; i++) {
            speakLoud("Word is:");
            speakLoud(efdb.getEng().get(i));
            speakLoud("the spelling of the word \"" + efdb.getEng().get(i) + "\" is :");
            tts.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
            String eng = efdb.getEng().get(i).toLowerCase();
            for (int j = 0; j < eng.length(); j++) {
                tts.playSilence(500, TextToSpeech.QUEUE_ADD, null);
                speakLoud("\"" + eng.charAt(j));
            }
            speakLoud("The meaning of the word \"" + efdb.getEng().get(i) + "\" is :");
            tts.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
            speakLoud(efdb.getMeaning().get(i));
        }
    }

    private void speakLoud(String text){
            if(text==""){
                tts.speak("Please Enter some Text.",TextToSpeech.QUEUE_ADD,null);
            }else{
                tts.speak(text,TextToSpeech.QUEUE_ADD,null);
            }
    }
    public void stop(){
        if(tts!=null){
            tts.stop();
        }
    }
}
