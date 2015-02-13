package org.usfirst.frc.team619.hardware;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.TalonSRX;

/**
 * 
 * The CANTalon object is only to be used with the Talon SRX (the small rectangular one)
 * as it is the only talon that uses CAN
 * 
 * @author Student
 */

public class TalonCan {

	CANTalon talon;
	
	public TalonCan(int canID){
		talon = new CANTalon(canID);
	}
	
	public int getID(){
		return talon.getDeviceID();
	}
	
	public double getOutputVoltage(){
		return talon.getOutputVoltage();
	}
	
	public double getInputVoltage(){
		return talon.getBusVoltage();
	}
	
	public double getOutputCurrent(){
		return talon.getOutputCurrent();  
	}
	
	public double getSpeed(){
		return talon.getSpeed();
	}
	
	public void set(double speed){
		talon.set(speed);
	}
	
	//to be used in conjunction with changeControlMode
	public void set(int id){
		talon.set(id);
	}
	
	public void setReversed(boolean reversed){
		talon.reverseOutput(reversed);
	}
	
	public void setFeedbackDevice(FeedbackDevice device){
		talon.setFeedbackDevice(device);
	}
	
	public void changeControlMode(ControlMode controlMode){
		talon.changeControlMode(controlMode);
	}
	
}
