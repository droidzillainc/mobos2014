package com.droidzilla.sensoriada.model;

import java.util.HashMap;
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

        public static Type getType(int code) {
            if (codeToTypeMapping == null) {
                initMapping();
            }
            return codeToTypeMapping.get(code);
        }

        private static void initMapping() {
            codeToTypeMapping = new HashMap<Integer, Type>();
            for (Type s : values()) {
                codeToTypeMapping.put(s.code, s);
            }
        }

    }

	public String getHumanReadableValue();

	public Type[] getSupportedTypes();
	
	public boolean isTypeSupported(Type type);
	
	public int getMaximumSupportedVersion();
	
	public Sensor parseSensorJson(String sensorJson);
}
