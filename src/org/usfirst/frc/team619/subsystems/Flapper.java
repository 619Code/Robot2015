package org.usfirst.frc.team619.subsystems;

import org.usfirst.frc.team619.hardware.LimitSwitch;
import org.usfirst.frc.team619.hardware.Solenoid;
import org.usfirst.frc.team619.hardware.Talon;

public class Flapper {
	
	Talon lift;
	
	Solenoid leftHand;
	Solenoid rightHand;
	
	LimitSwitch bottomSwitch;
	LimitSwitch levelOneSwitch;
	LimitSwitch levelTwoSwitch;
	LimitSwitch levelThreeSwitch;
	LimitSwitch topSwitch;
	
	public Flapper(Talon lift, Solenoid leftHand, Solenoid rightHand, LimitSwitch bottomSwitch, LimitSwitch levelOneSwitch, LimitSwitch levelTwoSwitch, LimitSwitch levelThreeSwitch,
				LimitSwitch topSwitch){
		this.lift = lift;
		this.leftHand = leftHand;
		this.rightHand = rightHand;
		this.bottomSwitch = bottomSwitch;
		this.levelOneSwitch = levelOneSwitch;
		this.levelTwoSwitch = levelTwoSwitch;
		this.levelThreeSwitch = levelThreeSwitch;
		this.topSwitch = topSwitch;
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
	
	public Talon getLift(){
		return lift;
	}
	
	public void setHands(boolean out){
		leftHand.set(out);
		rightHand.set(out);
	}
	
}
