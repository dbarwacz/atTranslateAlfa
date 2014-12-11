package com.example.arek.attranslate_ver10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arek on 2014-11-22.
 */
public class StartActivity extends Activity {
    View view;
    private Timer timer;
    private TimerTask timerTask;

    private static final int DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        view = findViewById(R.id.textClock);

        //nice try but it will always be null :D
        if(timer !=null)
            timer.cancel();

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //after brief overview of Timer implementation im pretty sure this method is run on
                //worker thread so anything view-related will raise exception

                //checked, it does: ViewRootImpl$CalledFromWrongThreadException
                //views can only be edited by thread that created them (workerThread)
                createNewActivity();
            }
        };
        timer.schedule(timerTask,DELAY_TIME);
    }

    void createNewActivity()
    {
        Intent intent = new Intent(this,MenuActivity.class);
        //uncomment to see it crashes
//        view.setVisibility(View.GONE);
        startActivity(intent);
        onStop();
    }
}
