package com.droidzilla.sensoriada.sensors;

import com.droidzilla.sensoriada.model.Sensor;

public class TemperatureSensor implements Sensor {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
	public String getHumanReadableValue() {
        float floatValue = ((float)value)/100;
		return floatValue+" °C";
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

}
