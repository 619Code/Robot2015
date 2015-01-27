package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;

public class RetrieveThreeTotes extends Action{

	private boolean isComplete = false;
	
	public RetrieveThreeTotes(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager) {
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
