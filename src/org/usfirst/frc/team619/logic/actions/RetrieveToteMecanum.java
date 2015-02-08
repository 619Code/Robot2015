package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;

import edu.wpi.first.wpilibj.Timer;

/*
 * Picks up one tote and brings it close to the scoring platform
 * 
 * @author Wilson 
*/
public class RetrieveToteMecanum extends Action{

	protected SRXMecanumDriveBase driveBase;
	protected Flapper flapper;
	
	private boolean isComplete = false;
	
	public RetrieveToteMecanum(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXMecanumDriveBase driveBase, Flapper flapper) {
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
		isComplete = true;
		//Assumes the bot is preset around the tote and is at preset 1 with the flapper.
		
		flapper.setLevel(0);
		flapper.setLevel(1);
		driveBase.slide(1);
		Timer.delay(3);
		driveBase.stop();				
	}

}
