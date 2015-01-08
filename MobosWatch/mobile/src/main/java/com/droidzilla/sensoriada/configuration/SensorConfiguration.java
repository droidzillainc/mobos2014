package com.droidzilla.sensoriada.configuration;

import java.util.HashMap;
import java.util.Map;

import com.droidzilla.sensoriada.model.Sensor;

public class SensorConfiguration {

    private static SensorConfiguration instance = null;
	private Map<Sensor.Type, String> implementations;

    private SensorConfiguration() {

    }

    public static SensorConfiguration getInstance() {
        if (instance == null) {
            instance = new SensorConfiguration();
        }
        return instance;
    }

	private Map<Sensor.Type, String> getDefaultImplementations() {
		Map<Sensor.Type, String> defaultImplementations = new HashMap<Sensor.Type, String>();
		defaultImplementations.put(Sensor.Type.TEMPERATURE, "com.droidzilla.sensoriada.sensors.TemperatureSensor");
		defaultImplementations.put(Sensor.Type.HUMIDITY, "com.droidzilla.sensoriada.sensors.HumiditySensor");
		defaultImplementations.put(Sensor.Type.PRESSURE, "com.droidzilla.sensoriada.sensors.PressureSensor");
		return defaultImplementations;
	}

    public String getImplementation(Sensor.Type type) {
        if (implementations == null) {
            implementations = getDefaultImplementations();
        }
        return implementations.get(type);
    }

}
