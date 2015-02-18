package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;

import edu.wpi.first.wpilibj.Timer;



public class RetrieveCanMecanum extends Action{
	
	protected SRXMecanumDriveBase driveBase; 	
	protected Flapper flapper;
	
	private boolean isComplete = false;
	
	public RetrieveCanMecanum(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXMecanumDriveBase driveBase, Flapper flapper) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		
		this.driveBase = driveBase;	
		this.flapper = flapper;
		
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	//Starting position will be lined up with the can facing parallel to the alliance wall
	//Preferred the can be inside the frame already for immediate pick-up action
	protected void cycle() {
		isComplete = true;
		
		while(!flapper.levelOneSwitchValue())
			flapper.setLiftSpeed(-1);
		
		while(!flapper.levelThreeSwitchValue())
			flapper.setLiftSpeed(1);	
		
		driveBase.drive(1);
			
		Timer.delay(3);
		
		driveBase.stop();
				
	}

}
