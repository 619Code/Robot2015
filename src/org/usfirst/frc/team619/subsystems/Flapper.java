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
	private TalonCan intakeLeft;
	private TalonCan intakeRight;
	
	private DualInputSolenoid hands;
	
	private LimitSwitch bottomSwitch;
	private LimitSwitch levelOneSwitch;
	private LimitSwitch levelTwoSwitch;
	private LimitSwitch levelThreeSwitch;
	private LimitSwitch topSwitch;
	
	
	
	public Flapper(TalonCan lift1, TalonCan lift2, DualInputSolenoid hands, LimitSwitch bottomSwitch, LimitSwitch levelOneSwitch, LimitSwitch levelTwoSwitch, LimitSwitch levelThreeSwitch,
				LimitSwitch topSwitch, TalonCan intakeLeft, TalonCan intakeRight){
		this.lift1 = lift1;
		this.lift2 = lift2;
		this.intakeLeft = intakeLeft;
		this.intakeRight = intakeRight;
		this.hands = hands;
		this.bottomSwitch = bottomSwitch;
		this.levelOneSwitch = levelOneSwitch;
		this.levelTwoSwitch = levelTwoSwitch;
		this.levelThreeSwitch = levelThreeSwitch;
		this.topSwitch = topSwitch;
		
		lift2.changeControlMode(CANTalon.ControlMode.Follower);
		lift2.set(lift1.getID());
		
		lift1.changeControlMode(CANTalon.ControlMode.PercentVbus);
		lift1.setReverseSoftLimit(-25000);
		lift1.enableReverseSoftLimit(false);

		//lift1.changeControlMode(CANTalon.ControlMode.Position);
		//lift1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		//lift1.setPID(0.01, 1, 0);
		//lift1.enableControl();
		//lift1.setForwardSoftLimit(192247);    //!!!!!!!!!!!!!!!!!!!!!!! very top == 394751
		//lift1.setForwardSoftLimit(9900);
		//lift1.setReverseSoftLimit(-9900);
		
	}
	
	public double getIntake(){
		return intakeRight.get();
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
	
	public void setIntake(){
		intakeLeft.set(0.2);
		intakeRight.set(0.2);
	}
	
	public void stopIntake(){
		intakeLeft.set(0);
		intakeRight.set(0);
	}
	
	public void setLevel(int level){
		
//		if(level == lastSwitch)
//			return;
//			
//		double speed = 0.5;
//		boolean stopLift = false;
//		
//		LimitSwitch currentLimit = null;
//		
//		switch(level){
//			case 0: currentLimit = bottomSwitch;
//			break;
//			case 1: currentLimit = levelOneSwitch;
//			break;
//			case 2: currentLimit = levelTwoSwitch;
//			break;
//			case 3: currentLimit = levelThreeSwitch;
//			break;
//			case 4: currentLimit = topSwitch;
//			break;
//		}
		
//		while(!stopLift){
//		if(currentLimit.get()){
//			stopLift = true;
//			setLiftSpeed(0);
//			System.out.println("STOPPING!!!!");
//		}else if(lastSwitch > level){
//			
//			if(checkLevel(true, level))
//				setLiftSpeed(-speed);
//			else
//				setLevel(level);
//		
//		}else if(lastSwitch < level){
//		
//			if(checkLevel(false, level))
//				setLiftSpeed(speed);
//			else
//				setLevel(level);
//		}
//		System.out.println("Going to: " + level);
//		System.out.println("Limit reached: " + currentLimit.get());
//		
//	}
//	
//	if(stopLift){
//		lastSwitch = level;
//		System.out.println("Done.");
//	}

		////////////////////////////////////////////////////////////////////////////////////////////////
		if(level == lastSwitch)
			return;

		double speed = 0.5;
		boolean up = level > lastSwitch ? true : false; 
		
		if ( ( ! up && bottomSwitch.get()) || (up && topSwitch.get())) {
			lastSwitch = up ? 4 : 0;
			return;
		}
		
		if ( level > lastSwitch ) 
			setLiftSpeed(speed);
		else
			setLiftSpeed(-speed); 
		
		int lastLevel = lastSwitch;
		int currentLevel = lastSwitch; 
		
		while ( currentLevel != level ) {
			if ( ( ! up && bottomSwitch.get()) || (up && topSwitch.get())) { break; }
			if ( levelOneSwitch.get() ) {
				lastLevel = currentLevel;
				currentLevel = 1;
			}
			else if ( levelTwoSwitch.get() ) {
				lastLevel = currentLevel;
				currentLevel = 2;
			}
			else if ( levelThreeSwitch.get() ) {
				lastLevel = currentLevel;
				currentLevel = 3;
			}
			if ( currentLevel != lastLevel && Math.abs(currentLevel - lastLevel) != 1 ) {
				lastSwitch = currentLevel;
				setLevel( level );
			}
		}
		setLiftSpeed(0);
		lastSwitch = currentLevel;
		///////////////////////////////////////////////////////////////////////////////////////////////////		
		
	}
	
	//possibly deprecated
	public boolean checkLevel(boolean up, int level){
		
		//Check which switch the lift is actually at
		if(bottomSwitch.get()){
			if(up){
				if(level + 1 != 0){
					lastSwitch = level;
					return false;
				}
			}
			if(!up){
				if(level - 1 != 0){
					lastSwitch = level;
					return false;
				}
			}
		}
		else if(levelOneSwitch.get()){
			if(up){
				if(level + 1 != 1){
					lastSwitch = level;
					return false;
				}
			}
			if(!up){
				if(level - 1 != 1){
					lastSwitch = level;
					return false;
				}
			}
		}
		else if(levelTwoSwitch.get()){
			if(up){
				if(level + 1 != 2){
					lastSwitch = level;
					return false;
				}
			}
			if(!up){
				if(level - 1 != 2){
					lastSwitch = level;
					return false;
				}
			}
		}
		else if(levelThreeSwitch.get()){
			if(up){
				if(level + 1 != 3){
					lastSwitch = level;
					return false;
				}
			}
			if(!up){
				if(level - 1 != 3){
					lastSwitch = level;
					return false;
				}
			}
		}
		else if(topSwitch.get()){
			if(up){
				if(level + 1 != 4){
					lastSwitch = level;
					return false;
				}
			}
			if(!up){
				if(level - 1 != 4){
					lastSwitch = level;
					return false;
				}
			}
		}
		return true;
	}

	public void setHands(boolean out){
		hands.set(out);
	}
	
	public int getCurrentSwitch(){
		return lastSwitch;
	}
	
	public void setCurrentSwitch(int switchNum){
		lastSwitch = switchNum;
	}
	
}
