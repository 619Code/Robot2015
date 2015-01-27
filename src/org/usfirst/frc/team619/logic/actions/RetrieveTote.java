package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.drive.MecanumDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;
import edu.wpi.first.wpilibj.Timer;

public class RetrieveTote extends Action{

	protected MecanumDriveBase driveBase;
	protected SensorBase sensorBase;
	
	private boolean isComplete = false;
	
	public RetrieveTote(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, MecanumDriveBase driveBase, SensorBase sensorBase) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		
		this.driveBase = driveBase;
		this.sensorBase = sensorBase;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	protected void cycle() {
		isComplete = true;
		
		driveBase.drive(1);
		Timer.delay(1);
		driveBase.stop();
		
		sensorBase.getUltrasonicSensor(1);
		
		

		
		
	}

}
