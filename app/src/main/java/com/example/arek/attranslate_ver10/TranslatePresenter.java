package com.example.arek.attranslate_ver10;

import android.content.Context;
import android.util.Log;

/**
 * Created by Arek on 2014-12-04.
 */
public class TranslatePresenter {

    private TranslateActivity translateActivity;
    private TranslateModel translateModel;
    private Context translateActivityContext;

    public TranslatePresenter(TranslateActivity translateActivity){
        //not nice, this should be interface for callbacks
        this.translateActivity = translateActivity;
        translateActivityContext = this.translateActivity.getApplicationContext();
        translateModel = new TranslateModel(translateActivityContext);
    }

    //method name should suggest purpose, not condition when its called ie. translateWordAndDeliverResult()
    void onMainTranslateButtonClicked(){
        translateModel.buildStringRequest();
        translateModel.translateWord();
        translateActivity.setTestTextView(translateModel.getTranslatedText());
    }

    void onLeftSpinnerItemSelected(int position){
       translateModel.setSourceLanguage(position);
    }

    void onRightSpinnerItemSelected(int position){
       translateModel.setTranslatedLanguage(position);
    }

    void onEditTextChanged(CharSequence s){
        translateModel.setSourceText(s.toString());
    }

    void onListViewItemSelected(){
        //TODO: Przechwyt wyboru itemu ListView
    }
}
