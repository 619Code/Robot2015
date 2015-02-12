package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXDriveBase;

import edu.wpi.first.wpilibj.Timer;



public class RetrieveCanTank extends Action{
	
	protected SRXDriveBase driveBase; 	
	protected Flapper flapper;
	
	private boolean isComplete = false;
	
	public RetrieveCanTank(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXDriveBase driveBase, Flapper flapper) {
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
		
		//pick up can
		flapper.setLevel(1);
		flapper.setLevel(3);
		
		//drive to auto zone
		driveBase.drive(1);
		Timer.delay(3);
		driveBase.stop();
				
		isComplete = true;
		
	}

}
