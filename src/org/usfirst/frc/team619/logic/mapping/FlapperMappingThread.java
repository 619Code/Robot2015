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
		
		double scalePercent = driverStation.getFourthJoystick().getAxis(Joystick.Axis.AXIS_Z);
		double yAxis = driverStation.getFourthJoystick().getAxis(Joystick.Axis.AXIS_Y);
		double scalePercent2 = driverStation.getThirdJoystick().getAxis(Joystick.Axis.AXIS_Z);
		
		if(scalePercent < 0.2){
			scalePercent = 0.2;
		}
		if(scalePercent2 < 0.2){
			scalePercent = 0.2;
		}
		
		double liftSpeed = yAxis * scalePercent;
		
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON2)){
			flapper.setIntake(scalePercent2);
		}else if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON4)){
			flapper.setOuttake(scalePercent2);
		}else
			flapper.stopIntake();
        
		
		//Button 3 (main thumb button on saitek aviators) returns lift to its lowest point
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON3)){
			flapper.setLevel(0);
		}
		
		//Button 5 (far left toggle switch (toggle up) also labeled T1) move lift to height needed to move around one tote 
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON5)){
			flapper.setLevel(1);
		}
		
		//Button 6 (far left toggle switch (toggle down) also labeled T2) move lift to height needed to move around two totes 
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON6)){
			flapper.setLevel(2);
		}
		
		//Button 7 (left toggle switch (toggle up) also labeled T3) move lift to height needed to move around three totes
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON7)){
			flapper.setLevel(3);
		}
		
		//Button 8 (left toggle switch (toggle down) also labeled T4) move lift to height needed to move around four totes 
		if(driverStation.getThirdJoystick().getButton(Joystick.Button.BUTTON8)){
			flapper.setLevel(4);
		}
		
		//Trigger on right joystick for Lift override
		if(driverStation.getFourthJoystick().getButton(Joystick.Button.TRIGGER)){
			flapper.setLiftSpeed(0);
			flapper.setLevel(flapper.getCurrentSwitch());//ensures that the lift does not continue once trigger is released
		}
		
		// Red Button on right joystick for manual manipulation of Lift
		if(driverStation.getFourthJoystick().getButton(Joystick.Button.BUTTON2)){
			if(liftSpeed > 0 && flapper.bottomSwitchValue())
				flapper.setLiftSpeed(liftSpeed);
			else if(liftSpeed < 0 && flapper.topSwitchValue())
				flapper.setLiftSpeed(liftSpeed);
			else if(!flapper.topSwitchValue() && !flapper.bottomSwitchValue())
				flapper.setLiftSpeed(liftSpeed);
			else
				flapper.setLiftSpeed(0);
		}
		else
			flapper.setLiftSpeed(0);
//		double lifts= driverStation.getFourthJoystick().getAxis(Joystick.Axis.AXIS_TWIST);
//	    flapper.setLiftSpeed(lifts);
//	    System.out.println("LIFT SPEED:" + lifts);
		
	}
	
}
