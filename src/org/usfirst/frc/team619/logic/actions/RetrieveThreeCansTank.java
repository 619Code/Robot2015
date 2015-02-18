package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.Timer;

public class RetrieveThreeCansTank extends Action{

	int can = 0;
	
	protected SRXDriveBase driveBase;
	protected Flapper flapper;
	protected SensorBase sensorBase;
	
	private boolean isComplete = false;
	
	public RetrieveThreeCansTank(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
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
		
		//pick up first can
		flapper.setLevel(1);
		flapper.setLevel(3);
		
		while(can < 3){
			
			//drive up to pick up can
			driveBase.drive(0.5);
			Timer.delay(1);
			driveBase.stop();
			
			//pick up the first can
			flapper.setLevel(3);
			
			//move to auto zone
			driveBase.drive(1);
			Timer.delay(3);
			driveBase.stop();
			
			//drop off can
			flapper.setLevel(0);
			can++;
			
			//move back to start zone   
			driveBase.drive(-1);
			Timer.delay(3);
			driveBase.stop();
			
			while(sensorBase.getUltrasonicSensor(1).getDistanceCM() <= sensorBase.getUltrasonicSensor(2).getDistanceCM() + 10)
				driveBase.drive(1);
			
			while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM())
				driveBase.drive(-1);
			
		}
		
		isComplete = true;
		
	}

}
