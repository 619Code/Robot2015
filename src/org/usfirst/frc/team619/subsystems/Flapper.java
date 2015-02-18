package org.usfirst.frc.team619.subsystems;

import org.usfirst.frc.team619.hardware.DualInputSolenoid;
import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.hardware.LimitSwitch;
import org.usfirst.frc.team619.hardware.Solenoid;
import org.usfirst.frc.team619.hardware.TalonCan;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;

public class Flapper {
	
	private int lastSwitch = 0;
	private boolean stopLift = false;
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
	
	FourStickDriverStation driverStation;
	private final String position_string = "position";
	private Preferences prefs = Preferences.getInstance( );
	
	public Flapper(FourStickDriverStation driverStation, TalonCan lift1, TalonCan lift2, DualInputSolenoid hands, LimitSwitch bottomSwitch, LimitSwitch levelOneSwitch, LimitSwitch levelTwoSwitch, LimitSwitch levelThreeSwitch,
				LimitSwitch topSwitch){
		this.lift1 = lift1;
		this.lift2 = lift2;
		this.hands = hands;
		this.bottomSwitch = bottomSwitch;
		this.levelOneSwitch = levelOneSwitch;
		this.levelTwoSwitch = levelTwoSwitch;
		this.levelThreeSwitch = levelThreeSwitch;
		this.topSwitch = topSwitch;
		
		this.driverStation = driverStation;
		
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

		double speed = 1.0;
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
				setLiftSpeed(0);            // just in case...
				setLevel(level);
			}
		}
		setLiftSpeed(0);
		lastSwitch = currentLevel;
		///////////////////////////////////////////////////////////////////////////////////////////////////		
		
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
	
	public void setCurrentSwitch(int switchNum){
		lastSwitch = switchNum;
	}
	
	public void interruptSetLevel(){
		stopLift = true;
	}

	private int last_position = -999999;
	// initialize talon position from preferences
	public void initializeTalonPosition( ) {
		if ( prefs.getInt("team", 0) != 619 )
			System.err.println("ERROR: it seems like RoboRIO preferences failed to initalize....");
		int new_position = prefs.getInt(position_string,0);
		lift1.setPosition(new_position);
		last_position = new_position;
	}

	// set talon position in preferences to a specific value
	public void setTalonPosition( int resetValue ) {
		lift1.setPosition(resetValue);
		storeTalonPosition( );
	}
	
	// update talon position with position from talon
	public void updateTalonPosition( ) {
		int current_position = (int) lift1.getPosition( );
		// 256 position values per revolution... avoid writing to the flash
		// memory incessantly... 
		if ( Math.abs(last_position - current_position) >= 256 ) {
			prefs.putInt(position_string, current_position);
			last_position = current_position;
		}
	}

	public void storeTalonPosition( ) {
		int current_position = (int) lift1.getPosition( );
		prefs.putInt(position_string, current_position);
		last_position = current_position;
	}
	
	public void setTalonForwardPositionLimit( int limit ) {
		lift1.setForwardSoftLimit(limit);
	}
	public void setTalonReversePositionLimit( int limit ) {
		lift1.setReverseSoftLimit(limit);
	}
	
}
