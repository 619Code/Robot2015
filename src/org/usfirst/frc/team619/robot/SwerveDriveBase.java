package org.usfirst.frc.team619.subsystems.drive;

import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.hardware.Talon;

/**
 *
 * @author admin
 */
public class SwerveDriveBase {
    
	private TalonCan leftMotor, rightMotor, leftMotor2, rightMotor2;
	private Talon leftTurn, rightTurn, leftTurn2, rightTurn2;
    
    public SwerveDriveBase(int leftMotorTalonID, int rightMotorTalonID, int leftMotorTalonID2, int rightMotorTalonID2, int leftTurnTalonID, int rightTurnTalonID, int leftTurnTalonID2, int rightTurnTalonID2){
        leftMotor = new TalonCan(leftMotorTalonID);
        rightMotor = new TalonCan(rightMotorTalonID);
        leftMotor2 = new TalonCan(leftMotorTalonID2);
        rightMotor2 = new TalonCan(rightMotorTalonID2);
        leftTurn = new Talon(leftTurnTalonID);
        rightTurn = new Talon(rightTurnTalonID);
        leftTurn2 = new Talon(leftTurnTalonID2);
        rightTurn2 = new Talon(rightTurnTalonID2);
    }//end constructor TalonDriveBase
    
    public SwerveDriveBase(TalonCan leftMotor, TalonCan rightMotor, TalonCan leftMotor2, TalonCan rightMotor2){
        this.leftMotor = leftMotor;
        this.leftMotor2 = leftMotor2;
        this.rightMotor = rightMotor;
        this.rightMotor2 = rightMotor2;
        rightMotor.setReversed(true);
    }//end constructor TalonDriveBase

    public SwerveDriveBase(TalonCan leftMotor, TalonCan leftMotor2, TalonCan rightMotor, TalonCan rightMotor2, Talon rightMotorTurn, Talon rightMotorTurn2, Talon leftMotorTurn, Talon leftMotorTurn2) {
        this.leftMotor = leftMotor;
        this.leftMotor2 = leftMotor2;
        this.rightMotor = rightMotor;
        this.rightMotor2 = rightMotor2;
        this.leftTurn = leftMotorTurn;
        this.leftTurn2 = leftMotorTurn2;
        this.rightTurn = rightMotorTurn;
        this.rightTurn2 = rightMotorTurn2;
    }

    protected SwerveDriveBase() {
        // USE WITH CAUTION
        // Allows child classes to bypass creating the Talons
    }

    public TalonCan getLeftTalon() {
        return leftMotor;
    }

    public TalonCan getRightTalon() {
        return rightMotor;
    }
    
    public TalonCan getLeftTalon2(){
        return leftMotor2;
    }//end Talon getLeftTalon2
    
    public TalonCan getRightTalon2(){
        return rightMotor2;
    }//end Talon getRightTalon2
    
    public Talon getLeftTurn(){
    	return leftTurn;
    }
    
    public Talon getRightTurn(){
    	return rightTurn;
    }
    
    public Talon getLeftTurn2(){
    	return leftTurn2;
    }
    
    public Talon getRightTurn2(){
    	return rightTurn2;
    }

    
    public void drive(double percent){
    	leftMotor.set(percent);
    	leftMotor2.set(percent);
    	rightMotor.set(-percent);
    	rightMotor2.set(-percent);
    }
    
    public void turn(double turnPercent /*positive number goes right negative number goes left*/){
    	leftTurn.set(turnPercent);
    	leftTurn2.set(turnPercent);
    	rightTurn.set(-turnPercent);
    	rightTurn2.set(-turnPercent);
    }
    
    public void stop(){
    	leftMotor.set(0);
    	rightMotor.set(0);
    	leftTurn.set(0);
    	rightTurn.set(0);
    }
    
}
