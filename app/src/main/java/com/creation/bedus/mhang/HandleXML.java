package com.creation.bedus.mhang;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
public class HandleXML {

    Context mContext;


    private ArrayList<String> eng =new ArrayList<String>();
    private ArrayList<String> pronounce =new ArrayList<String>();
    private ArrayList<String> meaning = new ArrayList<String>();
    private ArrayList<String> example = new ArrayList<String>();
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    public HandleXML(Context context, String url){
        this.mContext=context;
        this.urlString = url;
        new DownloadDataFromInternet().execute(urlString);
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {

        int event;
        String text=null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("word")){
                            eng.add(myParser.getAttributeValue(null,"data"));
                            pronounce.add(myParser.getAttributeValue(null,"pronounce"));
                            meaning.add(myParser.getAttributeValue(null,"meaning"));
                            example.add(myParser.getAttributeValue(null,"example"));
                        }else{
                        }
                        break;
                }
                event = myParser.next();

            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public class DownloadDataFromInternet extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            HttpURLConnection urlConnection=null;
            BufferedReader bufferedReader=null;


            String dataInXml=null;

            try{
                URL url=new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection)
                        url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream stream = conn.getInputStream();

                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser myparser =
                        xmlFactoryObject.newPullParser();

                myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                        , false);
                myparser.setInput(stream, null);
                parseXMLAndStoreIt(myparser);
                stream.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new InsertDataIntoDatabase().execute();
        }
    }

    private class InsertDataIntoDatabase extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            EllewDatabase edb=new EllewDatabase(mContext,"tamang",null,1);
            for(int i=0;i<eng.size();i++){
                /* promt a dialog box new update is available in the internet do you
                * want to updaate or not.
                 */
                if(edb.insertContact(eng.get(i), pronounce.get(i),
                        meaning.get(i),example.get(i))){
                };
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(mContext,"successfully database is updated.",Toast.LENGTH_LONG).show();
        }
    }
}