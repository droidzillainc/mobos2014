package com.droidzilla.sensoriada.sensors;

import com.droidzilla.sensoriada.model.Sensor;

public class TemperatureSensor implements Sensor {

	@Override
	public String getHumanReadableValue() {
		return null;
	}

	@Override
	public Type[] getSupportedTypes() {
		Type[] supportedTypes = new Type[1];
		supportedTypes[0] =  Type.TEMPERATURE;
		return supportedTypes;
	}

	@Override
	public boolean isTypeSupported(Type type) {
		return type == Type.TEMPERATURE;
	}

	@Override
	public int getMaximumSupportedVersion() {
		return 1;
	}

	@Override
	public Sensor parseSensorJson(String sensorJson) {
		// TODO Auto-generated method stub
		return null;
	}

}
