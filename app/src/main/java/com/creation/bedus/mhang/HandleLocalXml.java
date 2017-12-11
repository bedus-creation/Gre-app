package com.creation.bedus.mhang;


import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class HandleLocalXml {

    private ArrayList<String> eng = new ArrayList<String>();
    private ArrayList<String> pronounce = new ArrayList<String>();
    private ArrayList<String> meaning = new ArrayList<String>();
    private ArrayList<String> example = new ArrayList<String>();
    public volatile boolean parsingComplete = true;

    public HandleLocalXml() {

    }

    public ArrayList<String> getEng() {
        return eng;
    }

    public ArrayList<String> getProunce() {
        return pronounce;
    }

    public ArrayList<String> getMeaning() {
        return meaning;
    }

    public ArrayList<String> getExample() {
        return example;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {

        int event;
        String text = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("word")) {
                            eng.add(
                                    myParser.getAttributeValue(null, "data"));
                            pronounce.add(myParser.getAttributeValue(null, "pronounce"));
                            meaning.add(myParser.getAttributeValue(null, "meaning"));
                            example.add(myParser.getAttributeValue(null, "example"));
                        } else {
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


}