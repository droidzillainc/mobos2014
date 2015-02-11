package com.droidzilla.moboswatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;

public class MobosWatchWearActivity extends Activity {

    private boolean isRound = false;
    private int bottomChinSize = 0;
    private GestureDetector gestureDetector;
    private DismissOverlayView dismissOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobos_watch_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        stub.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                stub.onApplyWindowInsets(insets);
                isRound = insets.isRound();
                bottomChinSize = insets.getSystemWindowInsetBottom();
                return insets;
            }
        });

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);


            }
        });

        dismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        Log.e("caca", "DOV:" + dismissOverlayView);
        dismissOverlayView.setIntroText("Long press to exit!");
        dismissOverlayView.showIntroIfNecessary();

        gestureDetector = new GestureDetector(MobosWatchWearActivity.this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent motionEvent) {
                dismissOverlayView.show();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.dispatchTouchEvent(event);
    }

}
