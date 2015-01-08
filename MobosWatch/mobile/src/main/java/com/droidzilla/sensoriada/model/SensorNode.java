package com.droidzilla.sensoriada.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SensorNode {
	public long id;
	public int voltage;
	public long secondsAgo;
	public Date date;
	public List<Sensor> sensors = new LinkedList<Sensor>();
	
}
