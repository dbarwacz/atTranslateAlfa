package com.example.arek.attranslate_ver10;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Arek on 2014-12-05.
 */
public class Language {

    private List<String> languageList;
    private List<String> languageShortcuts;
    private String [] languageStringArray;
    private String [] ShortcutsStringArray;

    //Get the Context from TranslateActivity and pass it to get StringArray from Resources languages.xml
    public Language(Context context){

        languageStringArray = context.getResources().getStringArray(R.array.languages);
        languageList = Arrays.asList(languageStringArray);

        ShortcutsStringArray = context.getResources().getStringArray(R.array.languagesShortcuts);
        languageShortcuts = Arrays.asList(ShortcutsStringArray);

        Log.i("info", languageList.get(0));
        Log.i("info", languageShortcuts.get(0));
    }

    String getLanguage(int position){
        return languageShortcuts.get(position);
    }
}
