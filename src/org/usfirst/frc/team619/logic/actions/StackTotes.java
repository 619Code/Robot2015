package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.Timer;

public class StackTotes extends Action{

	private boolean isComplete = false;
	
	private Flapper flapper;
	private SensorBase sensorBase;
	private SRXMecanumDriveBase driveBase;
	
	public StackTotes(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXMecanumDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		this.flapper = flapper;
		this.sensorBase = sensorBase;
		this.driveBase = driveBase;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	//TO DO
	//Find out the final design for the hooks so we can drag out totes
	protected void cycle() {
		
		double startTime = 0.0;
		double startDistance = 0.0;
		
		//manipulate two sideways totes out of landfill
		for(int i = 0; i < 2; i++){
		
			//move hooks to grab tote
			flapper.setLevel(0);
			flapper.setLevel(1);
			
			//drive backwards for one second
			driveBase.slide(-0.5);
			Timer.delay(1);
			
			//stop moving
			driveBase.stop();
			
			//release tote
			flapper.setLevel(0);
			
			//move away from tote
			driveBase.slide(-.05);
			Timer.delay(1);
			
			//turn left (90 degrees), so right side is facing tote
			driveBase.turn(-0.5);
			Timer.delay(0.5);
			
			//keep track of start time to measure how long it takes for half of robot to move along the tote
			startTime = System.currentTimeMillis();
			
			//keep track of initial distance from robot to tote for more flexibility in the code
			startDistance = sensorBase.getUltrasonicSensor(3).getDistanceCM();
			
			//move backwards (parallel to tote) for as along as the sensor is giving a distance less than or equal to the initial reading +5 (for some wiggle room)
			while(sensorBase.getUltrasonicSensor(3).getDistanceCM() <= startDistance+5)
				driveBase.slide(-1);
			
			//continue going backwards for the same amount of time (for the front half of bot to pass by tote)
			driveBase.slide(-1);
			Timer.delay(System.currentTimeMillis() - startTime);
			
			//sideways until tote detected
			while(sensorBase.getUltrasonicSensor(1).getDistanceCM() == sensorBase.getUltrasonicSensor(2).getDistanceCM() && sensorBase.getUltrasonicSensor(1).getDistanceCM() > 50)
				driveBase.drive(1);
			
			//get ready to pick up tote
			driveBase.stop();
			flapper.setLevel(2);
			
			//move forward towards tote
			while(sensorBase.getUltrasonicSensor(1).getDistanceCM() == sensorBase.getUltrasonicSensor(2).getDistanceCM() && sensorBase.getUltrasonicSensor(1).getDistanceCM() > 30)
				driveBase.slide(1);
			
			//pick up tote
			driveBase.stop();
			flapper.setLevel(0);
			
			//turn right 90 degrees
			driveBase.turn(0.5);
			Timer.delay(0.5);
			
			//only want this segment executed once
			if(i == 0){
			
				//center on next tote
				while(sensorBase.getUltrasonicSensor(1).getDistanceCM() != sensorBase.getUltrasonicSensor(2).getDistanceCM()){
					
					if(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM()){
						
						//drive left 
						while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM())
							driveBase.drive(-1);
						
					}
					
					if(sensorBase.getUltrasonicSensor(1).getDistanceCM() < sensorBase.getUltrasonicSensor(2).getDistanceCM()){
						
						//drive right 
						while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM())
							driveBase.drive(1);
						
					}
					
				}//end while
				
				//move flapper up
				driveBase.stop();
				flapper.setLevel(1);
				
				//drive towards next tote
				while(sensorBase.getUltrasonicSensor(1).getDistanceCM() == sensorBase.getUltrasonicSensor(2).getDistanceCM() && sensorBase.getUltrasonicSensor(1).getDistanceCM() > 30)
					driveBase.slide(1);
				
			}//end if
			
		}//end for
		
		//line up with short side of final tote
		while(sensorBase.getUltrasonicSensor(1).getDistanceCM() != sensorBase.getUltrasonicSensor(2).getDistanceCM()){
			
			if(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM()){
				
				//drive right
				while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM())
					driveBase.drive(1);
				
			}
			
			if(sensorBase.getUltrasonicSensor(1).getDistanceCM() < sensorBase.getUltrasonicSensor(2).getDistanceCM()){
				
				//drive left 
				while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > sensorBase.getUltrasonicSensor(2).getDistanceCM())
					driveBase.drive(-1);
				
			}
			
		}
		
		flapper.setLevel(2);
		
		while(sensorBase.getUltrasonicSensor(1).getDistanceCM() > 30)
			driveBase.slide(1);
		
		//pick up third tote
		flapper.setLevel(0);
		flapper.setLevel(1);
		
		//go back until scoring ramp detected
		while(sensorBase.getI2CAccelerometer().getX() < 3)
			driveBase.drive(-1);
		
		//keep driving until flat on scoring ramp
		while(sensorBase.getI2CAccelerometer().getX() != 0)
			driveBase.drive(-1);
		
		//drive along ramp to the end
		while(sensorBase.getUltrasonicSensor(0).getDistanceCM() > 30)
			driveBase.slide(-1);
		
		driveBase.stop();
		
		//drop stack of totes
		flapper.setLevel(0);
		
		//get off ramp
		do{
			driveBase.slide(-1);
		}while(sensorBase.getI2CAccelerometer().getX() != 0);
		
		driveBase.stop();
		
		isComplete = true;
		
	}

}
