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

    //model describes how data look like, getting response is another kind of responsibility
    private String httpStringRequest;

    private Language language;

    public TranslateModel(Context translateActivityContext) {
        initLanguage(translateActivityContext);
        initTextStrings();
        initLanguageStrings();
    }

    void initTextStrings() {
        sourceText = new String("");
        translatedText = new String("");
    }

    void initLanguageStrings() {
        setSourceLanguage(0);
        setTranslatedLanguage(0);
    }

    void initLanguage(Context translateActivityContext) {
        language = new Language(translateActivityContext);
    }

    void setSourceText(String word) {
        sourceText = word;
        //what if api changes and starts to require %+ instead?
        //it would be better to create String CallParserUtils.parse(String)
        //that would take care of everything at once
        sourceText = sourceText.replace(" ", "+");
    }

    void setTranslatedText(String word) {
        translatedText = word;
    }

    String getSourceText() {
        return sourceText;
    }

    String getTranslatedText() {
        return translatedText;
    }

    //once again, what if someone accidentally increases position anywhere in project?
    //maybe enum would be better?
    void setSourceLanguage(int position) {
        sourceLanguage = language.getLanguage(position);
    }

    void setTranslatedLanguage(int position) {
        translatedLanguage = language.getLanguage(position);
    }

    String translateWord() {
        try {
            //check what get() does or look up why button is yellow so long after being clicked ;)
            //and what happens when there is no internet connection

            //answer: whole app freezes until standard 10s noresponse is reached

            //what if someone forget to call buildStringRequest?
            //its up to this class to provide result of specific task (which this job isnt by the way)
            setTranslatedText(new TranslatorAsyncTask().execute(httpStringRequest).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return translatedText;
    }

    void buildStringRequest() {
        //request builder maybe?
        //new TranslateRequest.Builder().from(sourceLan).to(resultLan).what(word).build()
        httpStringRequest = "http://www.worldlingo.com/S000.1/api?wl_data=" + sourceText;
        httpStringRequest = httpStringRequest + "&wl_srclang=" + sourceLanguage;
        httpStringRequest = httpStringRequest + "&wl_trglang=" + translatedLanguage;
        httpStringRequest = httpStringRequest + "&wl_password=secret";

        //you just created 7 immutable objects ;)
        Log.i("info", sourceLanguage);
        Log.i("info", translatedLanguage);
    }
}
