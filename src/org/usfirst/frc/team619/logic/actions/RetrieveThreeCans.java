package org.usfirst.frc.team619.logic.actions;

import org.usfirst.frc.team619.logic.ThreadManager;

public class RetrieveThreeCans extends Action{

	private boolean isComplete = false;
	
	public RetrieveThreeCans(int waitForDependenciesPeriod, int runPeriod, ThreadManager threadManager) {
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
