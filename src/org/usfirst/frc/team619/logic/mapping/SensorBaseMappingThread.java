package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

public class SensorBaseMappingThread extends RobotThread{

	SensorBase sensorBase;
	FourStickDriverStation driverStation;
	
	public SensorBaseMappingThread(SensorBase sensorBase, FourStickDriverStation driverStation, int period, ThreadManager threadManager){
		super(period, threadManager);
		this.sensorBase = sensorBase;
		this.driverStation = driverStation;
	}

	@Override
	protected void cycle() {
		
		if(driverStation.getFourthJoystick().getButton(Joystick.Button.BUTTON3)){
			sensorBase.startCamera("cam0");
		}else if(driverStation.getFourthJoystick().getButton(Joystick.Button.BUTTON4)){
			sensorBase.startCamera("cam2");
		}
		
	}
	
}
