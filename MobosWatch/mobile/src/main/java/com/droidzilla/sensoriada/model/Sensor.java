package com.droidzilla.sensoriada.model;

import java.util.Map;

public interface Sensor {
	public enum Type {
		TEMPERATURE(10),
		HUMIDITY(11),
		PRESSURE(12);

		int code;

		private static Map<Integer, Type> codeToTypeMapping;
		
		private Type(int code) {
			this.code = code;
		}
		
		
	}

	public String getHumanReadableValue();

	public Type[] getSupportedTypes();
	
	public boolean isTypeSupported(Type type);
	
	public int getMaximumSupportedVersion();
	
	public Sensor parseSensorJson(String sensorJson);
}
