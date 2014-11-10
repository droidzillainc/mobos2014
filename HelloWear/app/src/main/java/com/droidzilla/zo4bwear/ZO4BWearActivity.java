package com.droidzilla.zo4bwear;

import android.app.Activity;
import android.os.Bundle;

import android.os.Handler;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ZO4BWearActivity extends Activity {

    private static final String TAG = "mobos";
    private GameView gameView;
    private DismissOverlayView dismissOverlayView;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                gameView = (GameView) stub.findViewById(R.id.gameView);
            }

        });
        stub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        try{
            GameManager.getInstance().newGame();
        }
        catch (GameManager.GameOverException goe) {
            Log.e(TAG,"Game OVER");
        }

        dismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        dismissOverlayView.setIntroText("Double tap to exit!");
        dismissOverlayView.showIntroIfNecessary();

//        gestureDetector = new GestureDetector(ZO4BWearActivity.this, new GestureDetector.SimpleOnGestureListener() {
//            public void onLongPress(MotionEvent motionEvent) {
//                dismissOverlayView.show();
//            }
//        });
//

        gestureDetector = new GestureDetector(this, new MyGestureListener());


    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
//        Log.e("caca","Touch event:"+event.getAction());


        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            dismissOverlayView.show();
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            restartGame("Restarting!");
        }

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            double deltaX = event1.getX() - event2.getX();
            double deltaY = event1.getY() - event2.getY();
            try {
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX < 0) {
                        GameManager.getInstance().down();
                        gameView.invalidate();
                    }
                    else {
                        GameManager.getInstance().up();
                        gameView.invalidate();
                    }
                } else {
                    if (deltaY < 0) {
                        GameManager.getInstance().right();
                        gameView.invalidate();
                    }
                    else {
                        GameManager.getInstance().left();
                        gameView.invalidate();
                    }
                }
            }
            catch (GameManager.GameOverException goe) {
                restartGame("Game over!");
            }
            return true;
        }
    }

    public void restartGame(String message) {
        CharSequence text = message;
        Toast.makeText(ZO4BWearActivity.this, text , Toast.LENGTH_SHORT).show();
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    GameManager.getInstance().newGame();
                    gameView.invalidate();
                }
                catch (GameManager.GameOverException ignored) {
                }
            }
        }, 2000);

    }


    @Override
    protected void onStart() {
        Log.e(TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG,"onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.e(TAG,"onPosetResume");
        super.onPostResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.e(TAG,"onPause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }
}