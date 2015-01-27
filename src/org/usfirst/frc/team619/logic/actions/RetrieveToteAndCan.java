package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;

public class RetrieveToteAndCan extends Action{

	private boolean isComplete = false;
	
	public RetrieveToteAndCan(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager) {
		super(waitForDependenciesPeriod, runPeriod, threadManager);
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	protected void cycle() {
		isComplete = true;
	}

}
