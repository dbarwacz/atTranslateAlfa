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

//??SupportedLanguageCodesNames?
public class Language {
    //this:
    private static final String TAG = Language.class.getSimpleName();
    //or even better:
    //private static final String TAG = LogUtils.makeLogTag(Language.class)
    //where makeLogTag accepts class, appends app name at beggining and returns perfect tag
    //like Trans_Language
    private List<String> languageList;
    private List<String> languageShortcuts;
    private String [] languageStringArray;
    private String [] ShortcutsStringArray;

    //Get the Context from TranslateActivity and pass it to get StringArray from Resources languages.xml

    //+1 point for not storing context which could lead to leaks
    public Language(Context context){

        languageStringArray = context.getResources().getStringArray(R.array.languages);
        languageList = Arrays.asList(languageStringArray);

        ShortcutsStringArray = context.getResources().getStringArray(R.array.languagesShortcuts);
        languageShortcuts = Arrays.asList(ShortcutsStringArray);

        Log.i("tag","loaded languages: "+Arrays.toString(languageStringArray));
        //or even better LogUtils.LOGI(TAG,...);
        //where LOGI prints logs depending on build variant (automatically disables logs when
        //build is release) - you dont need to 'hunt' and comment out logs before release +
        //you get to change its implementation in one place - useful when you need to disable
        //information logs and only need debug ones.
        Log.i("info", languageList.get(0));
        Log.i("info", languageShortcuts.get(0));
    }

    String getLanguage(int position){
        return languageShortcuts.get(position);
    }
}
