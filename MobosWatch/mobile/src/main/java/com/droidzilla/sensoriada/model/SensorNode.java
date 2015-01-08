package com.droidzilla.sensoriada.model;

import java.util.Date;
import java.util.List;

public class SensorNode {
	public long id;
	public int voltage;
	public int secondsAgo;
	public Date date;
	public List<Sensor> sensors;
	
}
