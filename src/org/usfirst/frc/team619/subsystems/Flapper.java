package org.usfirst.frc.team619.subsystems;

import org.usfirst.frc.team619.hardware.DualInputSolenoid;
import org.usfirst.frc.team619.hardware.LimitSwitch;
import org.usfirst.frc.team619.hardware.Solenoid;
import org.usfirst.frc.team619.hardware.TalonCan;

import edu.wpi.first.wpilibj.CANTalon;

public class Flapper {
	
	private int lastSwitch = 0;
	/*
	 * 0 = bottom
	 * 1 = level one
	 * 2 = level two
	 * 3 = level three
	 * 4 = top
	 */
	
	private TalonCan lift1;
	private TalonCan lift2;
	
	private DualInputSolenoid hands;
	
	private LimitSwitch bottomSwitch;
	private LimitSwitch levelOneSwitch;
	private LimitSwitch levelTwoSwitch;
	private LimitSwitch levelThreeSwitch;
	private LimitSwitch topSwitch;
	
	public Flapper(TalonCan lift1, TalonCan lift2, DualInputSolenoid hands, LimitSwitch bottomSwitch, LimitSwitch levelOneSwitch, LimitSwitch levelTwoSwitch, LimitSwitch levelThreeSwitch,
				LimitSwitch topSwitch){
		this.lift1 = lift1;
		this.lift2 = lift2;
		this.hands = hands;
		this.bottomSwitch = bottomSwitch;
		this.levelOneSwitch = levelOneSwitch;
		this.levelTwoSwitch = levelTwoSwitch;
		this.levelThreeSwitch = levelThreeSwitch;
		this.topSwitch = topSwitch;
		
		lift2.setReversed(true);
		lift2.changeControlMode(CANTalon.ControlMode.Follower);
		lift2.set(lift1.getID());
		
	}

	public boolean bottomSwitchValue(){
		return bottomSwitch.get();
	}
	
	public boolean levelOneSwitchValue(){
		return levelOneSwitch.get();
	}
	
	public boolean levelTwoSwitchValue(){
		return levelTwoSwitch.get();
	}
	
	public boolean levelThreeSwitchValue(){
		return levelThreeSwitch.get();
	}
	
	public boolean topSwitchValue(){
		return topSwitch.get();
	}
	
	public void setLiftSpeed(double speed){
		lift1.set(speed);
	}
	
	public void setLevel(int level){
		
		if(level == lastSwitch)
			return;
			
		double speed = 1;
		boolean stopLift = false;
		
		LimitSwitch currentLimit = null;
		
		switch(level){
			case 0: currentLimit = bottomSwitch;
			break;
			case 1: currentLimit = levelOneSwitch;
			break;
			case 2: currentLimit = levelTwoSwitch;
			break;
			case 3: currentLimit = levelThreeSwitch;
			break;
			case 4: currentLimit = topSwitch;
			break;
		}
		
		while(!stopLift){
			if(currentLimit.get())
				stopLift = true;
			else if(lastSwitch > level)
				setLiftSpeed(-speed);
			else if(lastSwitch < level)
				setLiftSpeed(speed);
		}
		
		if(stopLift){
			lastSwitch = level;
			stopLift = false;
		}
		
	}
	
	public void setLevel(int level, double speed){
		
		if(level == lastSwitch)
			return;
		
		boolean stopLift = false;
		
		LimitSwitch currentLimit = null;
		
		switch(level){
			case 0: currentLimit = bottomSwitch;
			break;
			case 1: currentLimit = levelOneSwitch;
			break;
			case 2: currentLimit = levelTwoSwitch;
			break;
			case 3: currentLimit = levelThreeSwitch;
			break;
			case 4: currentLimit = topSwitch;
			break;
		}
		
		while(!stopLift){
			if(currentLimit.get())
				stopLift = true;
			else if(lastSwitch > level)
				setLiftSpeed(-speed);
			else if(lastSwitch < level)
				setLiftSpeed(speed);
		}
		
		if(stopLift){
			lastSwitch = level;
			stopLift = false;
		}
		
	}
	
	public void setHands(boolean out){
		hands.set(out);
	}
	
	public int getCurrentSwitch(){
		return lastSwitch;
	}
	
}
