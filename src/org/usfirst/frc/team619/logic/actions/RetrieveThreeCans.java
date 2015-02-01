package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.Timer;

public class RetrieveThreeCans extends Action{

	int can = 0;
	
	protected SRXMecanumDriveBase driveBase;
	protected Flapper flapper;
	protected SensorBase sensorBase;
	
	private boolean isComplete = false;
	
	public RetrieveThreeCans(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXMecanumDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		
		this.driveBase = driveBase;
		this.flapper = flapper;
		this.sensorBase = sensorBase;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	//starting position is in front of the can facing away from the alliance wall
	protected void cycle() {
		isComplete = true;
		
	while(can < 3){
	//drive up to pick up can
	driveBase.slide(0.5);
	Timer.delay(1);
	driveBase.stop();
	
	//pick up the first can
	flapper.setLevel(3);
	
	driveBase.slide(1);
	Timer.delay(3);
	driveBase.stop();
	
	flapper.setLevel(0);
	can++;
	
	driveBase.slide(-1);
	Timer.delay(3);
	driveBase.stop();
	
	while(sensorBase.getUltrasonicSensor(1).getDistanceCM() <= sensorBase.getUltrasonicSensor(2).getDistanceCM() + 10)
		driveBase.drive(1);
	
	while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM())
		driveBase.drive(-1);
		
		}
	}

}
