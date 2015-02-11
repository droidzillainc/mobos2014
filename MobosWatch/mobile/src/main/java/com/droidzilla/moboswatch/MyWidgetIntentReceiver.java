package com.droidzilla.moboswatch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RemoteViews;

import com.droidzilla.sensoriada.SensorNodeUtil;
import com.droidzilla.sensoriada.model.SensorNode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;



public class MyWidgetIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;

    private Handler uiHandler = new Handler();
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals("com.droidzilla.intent.action.REFRESH")){
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(final Context context) {
        new Thread() {
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet("https://agent.electricimp.com/YTMCD13qDSB6");

                HttpResponse response;
                try {
                    response = httpclient.execute(httpget);
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {

                        InputStream instream = entity.getContent();

                        String result= convertStreamToString(instream);
                        instream.close();

                        final List<SensorNode> sensorNodes = SensorNodeUtil.parseSensorNodes(result);


                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_mobos_demo);
                                remoteViews.setTextViewText(R.id.widget_inside_temperature, sensorNodes.get(0).sensors.get(0).getHumanReadableValue());
                                remoteViews.setTextViewText(R.id.widget_outside_temperature, sensorNodes.get(1).sensors.get(0).getHumanReadableValue());

                                remoteViews.setOnClickPendingIntent(R.id.widget_button, MyWidgetProvider.buildButtonPendingIntent(context));

                                MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
                            }
                        });
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
	}
    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
