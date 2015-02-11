package com.droidzilla.moboswatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;

public class MobosWatchWearActivityCommon extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {

    private boolean isRound = false;
    private int bottomChinSize = 0;
    private DelayedConfirmationView delayedConfirmationView;
    private View delayedConfirmLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobos_watch_wear_common);

        delayedConfirmLayout = findViewById(R.id.delayed_confirm_layout);

        delayedConfirmationView =
                (DelayedConfirmationView) findViewById(R.id.delayed_confirm);
        delayedConfirmationView.setListener(this);

        delayedConfirmLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayedConfirmLayout.setVisibility(View.VISIBLE);
                delayedConfirmationView.setTotalTimeMs(5000);
                delayedConfirmationView.start();
            }
        },5000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onTimerFinished(View view) {
        Log.e("caca", "On Timer Finished");
        System.exit(0);
    }

    @Override
    public void onTimerSelected(View view) {
        // cancel the execution
        delayedConfirmLayout.setVisibility(View.INVISIBLE);
    }
}
