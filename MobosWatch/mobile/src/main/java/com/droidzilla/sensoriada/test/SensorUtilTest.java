package com.droidzilla.sensoriada.test;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.droidzilla.sensoriada.SensorNodeUtil;
import com.droidzilla.sensoriada.model.SensorNode;
import com.droidzilla.sensoriada.sensors.TemperatureSensor;

import java.util.List;
import java.util.Locale;


//03100131082362
/**
 * Created by acraciun on 1/8/15.
 */
public class SensorUtilTest extends InstrumentationTestCase {
//    public void test() throws Exception {
//        final int expected = 1;
//        final int reality = 5;
//        assertEquals(expected, reality);
//    }

    public void test2NodesX1TemperatureSensorV1() {

        String sensorData = "{\"sensorNodes\":[{\"id\":0,\"voltage\":2848, \"secondsAgo\":2, \"date\":\"2015-01-08 13:30:16\", \"sensors\":[{\"type\":10,\"version\":1,\"value\":-875}]},{\"id\":1,\"voltage\":3143, \"secondsAgo\":18, \"date\":\"2015-01-08 13:30:16\", \"sensors\":[{\"type\":10,\"version\":1,\"value\":2156}]}]}";
        List<SensorNode> sensorNodes =  SensorNodeUtil.parseSensorNodes(sensorData);

        assertNotNull(sensorNodes);
        assertEquals(2, sensorNodes.size());
        assertEquals(0, sensorNodes.get(0).id);
        assertEquals(2848, sensorNodes.get(0).voltage);
        assertEquals(2, sensorNodes.get(0).secondsAgo);
        assertEquals(1, sensorNodes.get(0).sensors.size());
        assertEquals("com.droidzilla.sensoriada.sensors.TemperatureSensor", sensorNodes.get(0).sensors.get(0).getClass().getName());
        TemperatureSensor temperatureSensor = (TemperatureSensor) sensorNodes.get(0).sensors.get(0);
        assertEquals("-8.75 °C", temperatureSensor.getHumanReadableValue());


        assertEquals(1, sensorNodes.get(1).sensors.size());
    }
}