package org.usfirst.frc.team619.subsystems.drive;

import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.hardware.Talon;

public class SwerveDriveBase {

	private Talon leftMotor, rightMotor, leftMotor2, rightMotor2;
	
	public SwerveDriveBase(Talon leftMotor, Talon rightMotor, Talon leftMotor2, Talon rightMotor2){
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.leftMotor2 = leftMotor2;
		this.rightMotor2 = rightMotor2;
	}
	
	public Talon getLeftTalon(){
		return leftMotor;
	}
	
	public Talon getRightTalon(){
		return rightMotor;
	}
	
	public Talon getLeftTalon2(){
		return leftMotor2;
	}
	
	public Talon getRightTalon2(){
		return rightMotor2;
	}
}
