package com.droidzilla.moboswatch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import java.util.Calendar;

/**
 * Created by acraciun on 2/6/15.
 */
public class MobosWatchFaceService extends CanvasWatchFaceService {
    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine {

        private boolean isVisible;
        private boolean isInAmbientMode;
        private boolean isRound;
        private int chinSize = 0;
        private Rect cardBounds = new Rect();

        private Paint timePaint;
        private Paint backgroundPaint;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            Log.e("caca", "onCreate");
            timePaint = new Paint();
            timePaint.setColor(Color.GREEN);
            timePaint.setTextSize(getResources().getDimension(R.dimen.time_text_size));
            backgroundPaint = new Paint();
            backgroundPaint.setColor(Color.BLACK);
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);

            boolean lowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
            boolean burnInProtection = properties.getBoolean(PROPERTY_BURN_IN_PROTECTION,
                    false);

            Log.e("caca", "onPropertiesChanged burnInProtection:" + burnInProtection + " lowBitAmbient:" + lowBitAmbient);

        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }


        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            Log.e("caca", "__________________________________________________");
            Log.e("caca", "onDraw bounds:" + bounds);
            Log.e("caca", "onDraw isVisible:" + isVisible);
            Log.e("caca", "onDraw isInAmbientMode:" + isInAmbientMode);
            Log.e("caca", "onDraw isRound:" + isRound);
            Log.e("caca", "onDraw chinSize:" + chinSize);
            Log.e("caca", "onDraw cardBounds:" + cardBounds);

            canvas.drawRect(0, 0, bounds.width(), bounds.height(), backgroundPaint);

            String time = getTimeString();
            Rect textBounds = new Rect();
            timePaint.getTextBounds(time, 0, time.length(), textBounds);

            canvas.drawText(time, (bounds.width() - textBounds.width())/2, 100, timePaint);
        }

        private String getTimeString() {
            Calendar now = Calendar.getInstance();
            int hours = now.get(Calendar.HOUR_OF_DAY);
            String hoursTextPrefix = hours < 10 ? "0":"";
            int minutes = now.get(Calendar.HOUR_OF_DAY);
            String minutesTextPrefix = minutes < 10 ? "0":"";

            StringBuilder timeStringBuilder = new StringBuilder();
            timeStringBuilder.append(hoursTextPrefix);
            timeStringBuilder.append(hours);
            timeStringBuilder.append(":");
            timeStringBuilder.append(minutesTextPrefix);
            timeStringBuilder.append(minutes);
            if (!isInAmbientMode && isVisible) {
                int seconds = now.get(Calendar.HOUR_OF_DAY);
                String secondsTextPrefix = seconds < 10 ? "0":"";
                timeStringBuilder.append(":");
                timeStringBuilder.append(secondsTextPrefix);
                timeStringBuilder.append(seconds);
            }

            return timeStringBuilder.toString();
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
            this.isInAmbientMode = inAmbientMode;
            this.timePaint.setColor(inAmbientMode ? Color.WHITE : Color.GREEN);
            invalidate();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            this.isVisible = visible;
            invalidate();
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);
            this.isRound = insets.isRound();
            this.chinSize = insets.getSystemWindowInsetBottom();
        }

        @Override
        public void onPeekCardPositionUpdate(Rect bounds) {
            super.onPeekCardPositionUpdate(bounds);
            if (!bounds.equals(this.cardBounds)) {
                this.cardBounds.set(bounds);
                invalidate();
            }
        }

    }
}
