package com.example.arek.attranslate_ver10;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class TranslateActivity extends Activity {

    //why are they static?
    private Spinner leftSpinner;
    static private Spinner rightSpinner;

    //ook, got it, orientation change was messing with settings :P

    static private Button mainTranslateButton;
    static private EditText mainEditText;

    private TextView testTextView;

    private ArrayAdapter<CharSequence> languageAdapter;

    private ListView wordsListView;
    private ArrayAdapter<CharSequence> wordsAdapter;

    private TranslatePresenter translatePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        initTranslatePresenter();
        initTexts();
        initButton();
        initSpinners();
        testListView();
    }

    void initTranslatePresenter() {
        translatePresenter = new TranslatePresenter(this);
    }

    void initTexts() {
        mainEditText = (EditText) findViewById(R.id.mainEditText);
        mainEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //wouldnt it be easier to download this value at button translateButton press?
                translatePresenter.onEditTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        testTextView = (TextView) findViewById(R.id.testTextView);
    }

    void initButton() {
        mainTranslateButton = (Button) findViewById(R.id.mainTranslateButton);
        mainTranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainEditText.getText() passed as parameter to method below?
                translatePresenter.onMainTranslateButtonClicked();
            }
        });
    }

    void initSpinners() {
        leftSpinner = (Spinner) findViewById(R.id.leftSpinner);
        rightSpinner = (Spinner) findViewById(R.id.rightSpinner);
        initSpinnerAdapters();
        initSpinnerListeners();
    }

    void initSpinnerAdapters() {
        languageAdapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(languageAdapter);
        rightSpinner.setAdapter(languageAdapter);
    }

    void initSpinnerListeners() {
        //wild guess but maybe change it to:
//        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (id == R.id.leftSpinner) {
//                    translatePresenter.sourceLanguageSelected(something here but not position as
//                    this introduces too many dependencies (called method might have different
//                  indexes than this one)
//                } else if (id == R.id.rightSpinner) {
//                    translatePresenter.resultLangugaeSelected()
//                }
//            }
//        });
//        leftSpinner.setOnItemClickListener(listener);
//        right.setOnItemClickListener(listener);
        leftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                translatePresenter.onLeftSpinnerItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                translatePresenter.onRightSpinnerItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void testListView() {
        wordsListView = (ListView) findViewById(R.id.wordsListView);
        wordsAdapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_list_item_1);
        wordsListView.setAdapter(wordsAdapter);
    }

    void setTestTextView(String word) {
        testTextView.setText(word);
    }
}
