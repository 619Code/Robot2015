package org.usfirst.frc.team619.subsystems.drive;

import org.usfirst.frc.team619.hardware.AnalogGyro;

import edu.wpi.first.wpilibj.RobotDrive;

public class MecanumDriveBase_Cartesian {
	
	RobotDrive driveBase;
	AnalogGyro gyro;
	
	public MecanumDriveBase_Cartesian(int topLeft, int topRight, int bottomLeft, int bottomRight, AnalogGyro gyro){
		driveBase = new RobotDrive(topLeft, topRight, bottomLeft, bottomRight);
		this.gyro = gyro;
	}
	
	public void giveValues(double x, double y, double rotation, double gyroAngle){
		driveBase.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
	}
	
	public double getGyroAngle(){
		return gyro.getAngle();
	}
	
}
