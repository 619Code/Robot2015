package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.Timer;

/*
 * Picks up all 3 yellow totes from our side and then moves near scoring platform
 * 
 * @author Wilson
 */

public class RetrieveThreeTotes extends Action{

	protected SRXMecanumDriveBase driveBase;
	protected SensorBase sensorBase;
	protected Flapper flapper;
	protected double start;
	protected double end;
	protected double slideStart;
	protected double slideEnd;

	private boolean isComplete = false;
	
	public RetrieveThreeTotes(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXMecanumDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		
		start = 0.0;
		end = 0.0;
		slideStart = 0.0;
		slideEnd = 0.0;
		
		this.driveBase = driveBase;
		this.sensorBase = sensorBase;
		this.flapper = flapper;
	}

	public void startTime(){
		start = System.currentTimeMillis() / 100;
	}
	public void endTime(){
		end = System.currentTimeMillis() / 100;
	}
	public void slideStartTime(){
		slideStart = System.currentTimeMillis() / 100;
	}
	public void slideEndTime(){
		slideEnd = System.currentTimeMillis() / 100;
	}
	
	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	protected void cycle() {
		isComplete = true;
		
		for(int i = 0; i < 2; i++){
			
			driveBase.slide(.5);
			Timer.delay(.5);
			driveBase.stop();
			
			//Pick up tote
			flapper.setLevel(0);
			flapper.setLevel(1);
			
			//Drive clear of can
			if(sensorBase.getUltrasonicSensor(1).getDistanceCM() <= 100){
				this.slideStartTime();
			}while(sensorBase.getUltrasonicSensor(1).getDistanceCM() <= 100){
				driveBase.drive(1);
			}if(sensorBase.getUltrasonicSensor(1).getDistanceCM() >= 100){
				this.slideEndTime();
			}
			Timer.delay((slideEnd - slideStart) * 0.75);
			driveBase.stop();
			
			while(sensorBase.getUltrasonicSensor(0).getDistanceCM() >= 70){
				driveBase.drive(1);		
			}
			driveBase.slide(1);
			Timer.delay(1);
			driveBase.stop();
			
			//Measuring time to pass by tote
			if(sensorBase.getUltrasonicSensor(0).getDistanceCM() <= 70){
				this.startTime();
			}while(sensorBase.getUltrasonicSensor(0).getDistanceCM() <= 70){
				driveBase.drive(1);
			}if(sensorBase.getUltrasonicSensor(0).getDistanceCM() >= 70){
				this.endTime();
				driveBase.stop();
			}
			
			//Lining up to pick up tote and driving into it
			driveBase.drive(-1);
			Timer.delay((slideEnd - slideStart) * 1.6);
			driveBase.drive(-.1);
			if(sensorBase.getUltrasonicSensor(1).getDistanceCM() >= sensorBase.getUltrasonicSensor(2).getDistanceCM()){
				driveBase.stop();
			}while(sensorBase.getUltrasonicSensor(1).getDistanceCM() >= 30){
				driveBase.slide(1);
			}	
		}
	}

}
