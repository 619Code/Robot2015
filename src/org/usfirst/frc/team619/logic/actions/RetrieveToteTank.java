package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXDriveBase;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;

import edu.wpi.first.wpilibj.Timer;

/*
 * Picks up one tote and brings it close to the scoring platform
 * 
 * @author Wilson 
*/
public class RetrieveToteTank extends Action{

	protected SRXDriveBase driveBase;
	protected Flapper flapper;
	
	private boolean isComplete = false;
	
	public RetrieveToteTank(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXDriveBase driveBase, Flapper flapper) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
		
		this.driveBase = driveBase;
		this.flapper = flapper;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	protected void cycle() {
		
		//pick up tote
		flapper.setLevel(0);
		flapper.setLevel(1);
		
		//turn 90 degrees
		driveBase.turn(1);
		Timer.delay(2);
		driveBase.stop();
		
		//drive forward to auto zone
		driveBase.drive(1);
		Timer.delay(3);
		
		isComplete = true;
		
	}

}
