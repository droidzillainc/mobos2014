package com.droidzilla.hellowear;

import android.app.Activity;
import android.os.Bundle;

import android.os.Handler;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWearActivity  extends Activity {

    private TextView mTextView;
    private GameView gameView;
    private GestureDetector gestureDetector;
    private DismissOverlayView dismissOverlayView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("mobos","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                gameView = (GameView) stub.findViewById(R.id.gameView);
//                Log.e("caca", "TextView: " + mTextView.getText() + " view=" + mTextView);
            }

        });

//        stub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        stub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("caca","stub touch "+event.getAction());
                return mDetector.onTouchEvent(event);
//                return true;
            }
        });
        try{
            GameManager.getInstance().newGame();
        }
        catch (GameManager.GameOverException goe) {
            Log.e("caca","Game OVER");
        }

        dismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        Log.e("caca", "DOV:" + dismissOverlayView);
        dismissOverlayView.setIntroText("De afisat prima data 2");
        dismissOverlayView.showIntroIfNecessary();

        gestureDetector = new GestureDetector(HelloWearActivity.this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent motionEvent) {
                Log.e("caca", "long press");
                dismissOverlayView.show();
            }
        });


        mDetector = new GestureDetector(this, new MyGestureListener());


    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
//        Log.e("caca","Touch event:"+event.getAction());


        return super.onTouchEvent(event);
    }

    private GestureDetector mDetector;

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("caca","Long press");
            restartGame("Restarting!");
        }

        @Override
        public boolean onDown(MotionEvent event) {
//            Log.e("caca","onDown: " + event.getX() + ":"+event.getY());
            float x = event.getX();
            float y = event.getY();

            boolean up = (x > 95) && (x < 185) && (y < 95);
            boolean down = (x > 95) && (x < 185) && (y > 185);
            boolean left = (y > 95) && (y < 185) && (x < 95);
            boolean right = (y > 95) && (y < 185) && (x > 185);

            try {
                if (up) {
                    Log.e("caca", "UP");
                    GameManager.getInstance().left();
                    gameView.invalidate();
                }
                if (down) {
                    Log.e("caca", "DOWN");
                    GameManager.getInstance().right();
                    gameView.invalidate();
                }
                if (left) {
                    Log.e("caca", "LEFT");
                    GameManager.getInstance().up();
                    gameView.invalidate();
                }
                if (right) {
                    Log.e("caca", "RIGHT");
                    GameManager.getInstance().down();
                    gameView.invalidate();
                }
            }
            catch (GameManager.GameOverException goe) {
                Log.e("caca","Game OVER");
                restartGame("Game over!");
            }

            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
//            Log.e("caca", "onFling: " + event1.toString()+event2.toString());
            return true;
        }
    }

    public void restartGame(String message) {
        CharSequence text = message;
        Toast.makeText(HelloWearActivity.this, text , Toast.LENGTH_SHORT).show();
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
        Log.e("mobos","onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e("mobos","onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e("mobos","onResume");
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.e("mobos","onPosetResume");
        super.onPostResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("mobos","onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.e("mobos","onPause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("mobos","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("mobos","onDestroy");
        super.onDestroy();
    }
}