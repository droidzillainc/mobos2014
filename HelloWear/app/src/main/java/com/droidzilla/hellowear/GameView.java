package com.droidzilla.hellowear;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.gms.internal.b;

/**
 * Created by root on 7/1/2014.
 */
public class GameView extends View {

    Paint squarePaint = new Paint();
    Paint textPaint = new Paint();


    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.rgb(0x76,0x78,0x7a));
        canvas.drawRect(0, 0, 280, 280, paint);
        paint.setColor(Color.rgb(0xa8,0xab,0xad));
        paint.setTextSize(20);
        canvas.drawLine(0, 70, 280, 70, paint);
        canvas.drawLine(0, 140, 280, 140, paint);
        canvas.drawLine(0, 210, 280, 210, paint);
        canvas.drawLine(70, 280, 70, 0, paint);
        canvas.drawLine(140, 280, 140, 0, paint);
        canvas.drawLine(210, 280, 210, 0, paint);


        int[][] matrix = GameManager.getInstance().getMatrix();

//        matrix[0][0] = 2;
//        matrix[0][1] = 4;
//        matrix[0][3] = 8;
//        matrix[1][0] = 16;
//        matrix[1][1] = 32;
//        matrix[1][2] = 64;
//        matrix[1][3] = 128;
//        matrix[2][0] = 256;
//        matrix[2][1] = 512;
//        matrix[2][2] = 1024;
//        matrix[2][3] = 2048;
//        matrix[3][0] = 4096;
//        matrix[3][1] = 8192;
//        matrix[3][2] = 16384;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (matrix[i][j] != 0) {
                    String text = "" + matrix[i][j];
                    int digits = (int)Math.floor(Math.log(matrix[i][j]) / Math.log(10) + 1);

                    Log.e("caca", matrix[i][j]+ " " +digits);

                    switch (matrix[i][j]){
                        case 2:
                            squarePaint.setColor(Color.rgb(0xdd,0xdd,0xdd));
                            textPaint.setColor(Color.rgb(0x4b,0x4b,0x4b));
                            textPaint.setTextSize(40);
                            break;
                        case 4:
                            squarePaint.setColor(Color.rgb(0xdd,0xdd,0xdd));
                            textPaint.setColor(Color.rgb(0x4b,0x4b,0x4b));
                            textPaint.setTextSize(40);
                            break;
                        case 8:
                            squarePaint.setColor(Color.rgb(0xff,0x99,0x80));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(40);
                            break;
                        case 16:
                            squarePaint.setColor(Color.rgb(0xff,0x70,0x4d));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(35);
                            break;
                        case 32:
                            squarePaint.setColor(Color.rgb(0xff,0x33,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(35);
                            break;
                        case 64:
                            squarePaint.setColor(Color.rgb(0xff,0x00,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(35);
                            break;
                        case 128:
                            squarePaint.setColor(Color.rgb(0xff,0x99,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(21);
                            break;
                        case 256:
                            squarePaint.setColor(Color.rgb(0xff,0xaa,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(21);
                            break;
                        case 512:
                            squarePaint.setColor(Color.rgb(0xff,0xbb,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(21);
                            break;
                        case 1024:
                            squarePaint.setColor(Color.rgb(0xff,0xcc,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(19);
                            break;
                        case 2048:
                            squarePaint.setColor(Color.rgb(0xff,0xee,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(19);
                            break;
                        case 4096:
                            squarePaint.setColor(Color.rgb(0x00,0x00,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(19);
                            break;
                        case 8192:
                            squarePaint.setColor(Color.rgb(0x00,0x00,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(19);
                            break;
                        case 16834:
                            squarePaint.setColor(Color.rgb(0x00,0x00,0x00));
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(17);
                            break;
                    }

                    int xPos = (int) (70 - textPaint.getTextSize() * Math.abs(text.length() / 2)) / 2;
                    int yPos = (int) ((70 / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
                    //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.

                    paint.setColor(Color.rgb(0x76,0x78,0x7a));
                    canvas.drawRect(70*i + 1, 70*j + 1,70*(i+1), 70*(j+1), squarePaint);

                    paint.setColor(Color.WHITE);
                    int delta1Digit = 0;
                    if (digits == 1) {
                        delta1Digit = 3;
                    }
                    canvas.drawText(text, 70*i + xPos - ((digits%2) * 7) - delta1Digit, 70*j + yPos, textPaint);

                }

            }
        }

    }
}
