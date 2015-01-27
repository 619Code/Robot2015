package org.usfirst.frc.team619.subsystems.sensor;

import java.util.ArrayList;

import org.usfirst.frc.team619.hardware.AnalogPotentiometer;
import org.usfirst.frc.team619.hardware.AnalogUltrasonic;
import org.usfirst.frc.team619.hardware.AnalogAccelerometer;

public class SensorBase {	
	
	protected ArrayList<AnalogUltrasonic> ultrasonicList;
	protected ArrayList<AnalogPotentiometer> potentiometerList;
	protected ArrayList<AnalogAccelerometer> accelerometerList;
	
	public SensorBase(){
		ultrasonicList = new ArrayList<>();
		potentiometerList = new ArrayList <>();
		accelerometerList = new ArrayList <>();
	}
	
	public SensorBase(AnalogUltrasonic ultrasonic){
		ultrasonicList = new ArrayList<>();
		potentiometerList = new ArrayList <>();
		accelerometerList = new ArrayList <>();
		
		ultrasonicList.add(ultrasonic);
		
	}
	
	public SensorBase(ArrayList<AnalogUltrasonic> ultrasonicList){
		this.ultrasonicList = ultrasonicList;
	}
	
	public void addUltrasonicSensor(AnalogUltrasonic sensor){
		ultrasonicList.add(sensor);
	}
	
	public void addAccelerometer(AnalogAccelerometer sensor){
		accelerometerList.add(sensor);
	}
	
	public void addPotentiometer(AnalogPotentiometer sensor){
		potentiometerList.add(sensor);
	}
	
	public AnalogUltrasonic getUltrasonicSensor(int channel){

		for(AnalogUltrasonic sensor : ultrasonicList){
			if(sensor.getChannel() == channel)
				return sensor;
		}
		
		return null;
		
	}
	
	public AnalogAccelerometer getAccelerometer(int channel){
		
		for(AnalogAccelerometer sensor : accelerometerList){
			if(sensor.getChannel() == channel)
				return sensor;
		}
		
		return null;
		
	}
	
	public AnalogPotentiometer getPotentiometer(int channel){
		
		for(AnalogPotentiometer sensor : potentiometerList){
			if(sensor.getChannel() == channel)
				return sensor;
		}
		
		return null;
		
	}
	
}
