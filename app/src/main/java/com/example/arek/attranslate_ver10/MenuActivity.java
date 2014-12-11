package com.example.arek.attranslate_ver10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arek on 2014-11-22.
 */
public class MenuActivity extends Activity {

    private Button translateButton;
    private Button helpButton;
    private Button authorsButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initButtons();
        initButtonListeners();
    }

    void initButtons()
    {
        translateButton = (Button) findViewById(R.id.translateButton);
        helpButton = (Button) findViewById(R.id.helpButton);
        authorsButton = (Button) findViewById(R.id.authorsButton);
        exitButton = (Button) findViewById(R.id.exitButton);
    }

    void initButtonListeners()
    {
        //what if you need to change the order?
        //Better use private static final int CODE_TRANSLATE = 1
        //(at build time get translated to numbers either way - no efficiency decrease but they
        //are easily understandable for anyone who reads the code (and can be reordered at anytime)
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createActivity(1);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createActivity(2);
            }
        });

        authorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createActivity(3);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createActivity(4);
            }
        });
    }

    void createActivity(int choice)
    {
        switch(choice)
        {
            case 1: startActivity(new Intent(this,TranslateActivity.class));break;
            case 2: startActivity(new Intent(this,HelpActivity.class));break;
            case 3: startActivity(new Intent(this,AuthorsActivity.class)); break;

            //what with good old finish()? ;)
            case 4: System.exit(0); break;
        }
    }

}
