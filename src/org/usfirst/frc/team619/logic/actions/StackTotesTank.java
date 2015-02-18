package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.drive.SRXDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

public class StackTotesTank extends Action{

	public StackTotesTank(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager, SRXDriveBase driveBase, Flapper flapper, SensorBase sensorBase) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void cycle() {
		// TODO Auto-generated method stub
		
	}

}
