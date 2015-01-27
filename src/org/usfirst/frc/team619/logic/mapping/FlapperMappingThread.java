package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;

public class FlapperMappingThread extends RobotThread{
	
	Flapper flapper;
	FourStickDriverStation driverStation;
	
	double liftSpeed = 1;//the speed at which the lift moves to go the the next level
	
	int lastSwitch = 0;//the last limit switch that was activated
	/*
	 * 0 = bottom
	 * 1 = level one
	 * 2 = level two
	 * 3 = level three
	 * 4 = top
	 */
	
	public FlapperMappingThread(Flapper flapper, FourStickDriverStation driverStation, int period, ThreadManager threadManager){
		super(period, threadManager);
		this.flapper = flapper;
		this.driverStation = driverStation;
	}

	@Override
	protected void cycle() {
		
		boolean stopLift = false;
		
		//Button 2 (red button on saitek aviators) shoots out both pneumatics
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON2)){
			flapper.setHands(true);
		}
		
		//Button 3 (main thumb button on saitek aviators) returns lift to its lowest point
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON3)){
			while(!stopLift){
				if(flapper.bottomSwitchValue())
					stopLift = true;
				else
					flapper.getLift().set(-liftSpeed);
			}
			
			if(stopLift){
				lastSwitch = 0;
				stopLift = false;
			}
			
		}
		
		//Button 5 (far left toggle switch (toggle up) also labeled T1) move lift to height needed to move around one tote 
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON5)){
			while(!stopLift){
				if(flapper.levelOneSwitchValue())
					stopLift = true;
				else if(lastSwitch > 1)
					flapper.getLift().set(-liftSpeed);
				else if(lastSwitch < 1)
					flapper.getLift().set(liftSpeed);
			}
			
			if(stopLift){
				lastSwitch = 1;
				stopLift = false;
			}
			
		}
		
		//Button 6 (far left toggle switch (toggle down) also labeled T2) move lift to height needed to move around two totes 
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON6)){
			while(!stopLift){
				if(flapper.levelOneSwitchValue())
					stopLift = true;
				else if(lastSwitch > 2)
					flapper.getLift().set(-liftSpeed);
				else if(lastSwitch < 2)
					flapper.getLift().set(liftSpeed);
			}
			
			if(stopLift){
				lastSwitch = 2;
				stopLift = false;
			}
			
		}
		
		//Button 7 (left toggle switch (toggle up) also labeled T3) move lift to height needed to move around three totes
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON5)){
			while(!stopLift){
				if(flapper.levelOneSwitchValue())
					stopLift = true;
				else if(lastSwitch > 3)
					flapper.getLift().set(-liftSpeed);
				else if(lastSwitch < 3)
					flapper.getLift().set(liftSpeed);
			}
			
			if(stopLift){
				lastSwitch = 3;
				stopLift = false;
			}
			
		}
		
		//Button 8 (left toggle switch (toggle down) also labeled T4) move lift to height needed to move around four totes 
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON5)){
			while(!stopLift){
				if(flapper.topSwitchValue())
					stopLift = true;
				else
					flapper.getLift().set(liftSpeed);
			}
			
			if(stopLift){
				lastSwitch = 4;
				stopLift = false;
			}
			
		}
		
	}
	
}
