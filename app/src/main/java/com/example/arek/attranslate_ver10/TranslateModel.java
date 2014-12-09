package com.example.arek.attranslate_ver10;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by Arek on 2014-12-05.
 */
public class TranslateModel {

    private String sourceText;
    private String translatedText;

    private String sourceLanguage;
    private String translatedLanguage;

    private String httpStringRequest;

    private Language language;

    public TranslateModel(Context translateActivityContext){
        initLanguage(translateActivityContext);
        initTextStrings();
        initLanguageStrings();
    }

    void initTextStrings(){
        sourceText = new String("");
        translatedText = new String("");
    }

    void initLanguageStrings(){
        setSourceLanguage(0);
        setTranslatedLanguage(0);
    }

    void initLanguage(Context translateActivityContext){
        language = new Language(translateActivityContext);
    }

    void setSourceText(String word){
       sourceText = word;
       sourceText = sourceText.replace(" ","+");
    }

    void setTranslatedText(String word){
        translatedText = word;
    }
    
    String getSourceText()
    {
        return sourceText;
    }

    String getTranslatedText()
    {
        return translatedText;
    }

    void setSourceLanguage(int position){
        sourceLanguage = language.getLanguage(position);
    }

    void setTranslatedLanguage(int position){
        translatedLanguage = language.getLanguage(position);
    }

    String translateWord()
    {
        try {
            setTranslatedText(new TranslatorAsyncTask().execute(httpStringRequest).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return translatedText;
    }

    void buildStringRequest()
    {
        httpStringRequest = "http://www.worldlingo.com/S000.1/api?wl_data=" + sourceText;
        httpStringRequest = httpStringRequest + "&wl_srclang=" + sourceLanguage;
        httpStringRequest = httpStringRequest + "&wl_trglang=" + translatedLanguage;
        httpStringRequest = httpStringRequest + "&wl_password=secret";
        Log.i("info",sourceLanguage);
        Log.i("info",translatedLanguage);
    }
}
