package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.Timer;

public class RetrieveToteAndCan extends Action{
	
	protected Flapper flapper;
	protected SRXMecanumDriveBase driveBase;
	protected SensorBase sensorBase;

	private boolean isComplete = false;
	
	public RetrieveToteAndCan(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXMecanumDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		
		this.flapper = flapper;
		this.driveBase = driveBase;
		this.sensorBase = sensorBase;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	//Start out facing parallel to alliance wall facing the can, inside for immediate pickup 
	//Not using hooks
	protected void cycle() {
		isComplete = true;
		
		//Pick up can
		flapper.setLevel(1);
		flapper.setLevel(3);
	
		while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > 30 && sensorBase.getUltrasonicSensor(2).getDistanceCM() > 30)
			driveBase.slide(1);
		
		driveBase.slide(0.5);
		Timer.delay(1);
		driveBase.stop();
			
		//picks up tote
		flapper.setLevel(0);
		flapper.setLevel(1);
		
		driveBase.drive(1);
		Timer.delay(3);
		driveBase.stop();
	
	}

}
