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

public class TalonCan extends CANTalon {
	public TalonCan(int canID){
		super(canID);
	}
	public TalonCan(int canID,boolean doLogging) {
		super(canID);
	}
	
	public int getID(){
		return getDeviceID();
	}
		
	public double getInputVoltage(){
		return getBusVoltage();
	}
	
	public void setReversed(boolean reversed){
		reverseOutput(reversed);
	}
		
}
