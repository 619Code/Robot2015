package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.Timer;

public class RetrieveToteAndCanTank extends Action{
	
	protected Flapper flapper;
	protected SRXDriveBase driveBase;
	protected SensorBase sensorBase;

	private boolean isComplete = false;
	
	public RetrieveToteAndCanTank(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
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
		
		//picks up tote
		flapper.setLevel(0);
		flapper.setLevel(1);
	
		//drive toward can
		while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > 30 && sensorBase.getUltrasonicSensor(2).getDistanceCM() > 30)
			driveBase.drive(1);
			
		//Pick up can
		flapper.setLevel(1);
		flapper.setLevel(3);
		
		//turn 90 degrees
		driveBase.turn(2);
		Timer.delay(2);
		
		//drive into autozone
		driveBase.drive(1);
		Timer.delay(3);
		driveBase.stop();
	
	}

}
