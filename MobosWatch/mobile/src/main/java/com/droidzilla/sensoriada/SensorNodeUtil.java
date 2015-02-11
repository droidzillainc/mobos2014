package com.droidzilla.sensoriada;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.droidzilla.sensoriada.configuration.SensorConfiguration;
import com.droidzilla.sensoriada.model.Sensor;
import com.droidzilla.sensoriada.model.SensorNode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SensorNodeUtil {

    public static List<SensorNode> parseSensorNodes(String json) {
        try {
            JSONObject sensorNodesJsonObject = new JSONObject(json);
            JSONArray sensorNodesArray = sensorNodesJsonObject.getJSONArray("sensorNodes");
            List<SensorNode> sensorNodes = new LinkedList<SensorNode>();
            for (int i = 0; i < sensorNodesArray.length(); i++) {
                JSONObject sensorNodeObject = sensorNodesArray.getJSONObject(i);

                SensorNode sensorNode = new SensorNode();
                sensorNode.id = sensorNodeObject.getInt("id");
                sensorNode.voltage = sensorNodeObject.getInt("voltage");
//                sensorNode.secondsAgo = System.currentTimeMillis() - sensorNodeObject.getInt("secondsAgo") * 1000;
                sensorNode.secondsAgo = sensorNodeObject.getInt("secondsAgo");

                JSONArray sensorsArray = sensorNodeObject.getJSONArray("sensors");
                for (int j = 0; j < sensorsArray.length(); j++) {
                    JSONObject sensorObject = sensorsArray.getJSONObject(j);
                    final int type = sensorObject.getInt("type");
                    final int version = sensorObject.getInt("version");



                    String className = SensorConfiguration.getInstance().getImplementation(Sensor.Type.getType(type));
                    Sensor sensor = (Sensor) Class.forName(className).newInstance();

                    if (sensor.getMaximumSupportedVersion() < version) {
                        throw new IllegalArgumentException("Invalid sensor version " + version + " for sensor type " + type);
                    }

                    Iterator<String> keysIterator = sensorObject.keys();
                    while (keysIterator.hasNext()) {
                        String key = keysIterator.next();
                        if (!key.equals("type") && !key.equals("version")) {
                            Object value = sensorObject.get(key);
                            sensor.getClass().getMethod("set"+key.substring(0,1).toUpperCase()+key.substring(1), value.getClass()).invoke(sensor, value);
                        }
                    }
                    sensorNode.sensors.add(sensor);
                }
                sensorNodes.add(sensorNode);
            }

            return sensorNodes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
