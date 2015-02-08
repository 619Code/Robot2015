package org.usfirst.frc.team619.subsystems.drive;

import org.usfirst.frc.team619.hardware.TalonCan;

/**
 *
 * @author admin
 */
public class SRXDriveBase {
    
	private TalonCan leftMotor, rightMotor, leftMotor2, rightMotor2;

    public SRXDriveBase(int leftMotorTalonID, int rightMotorTalonID) {
        leftMotor = new TalonCan(leftMotorTalonID);
        leftMotor.setReversed(true);
        rightMotor = new TalonCan(rightMotorTalonID);
        rightMotor.setReversed(true);
    }
    
    public SRXDriveBase(int leftMotorTalonID, int rightMotorTalonID, int leftMotorTalonID2, int rightMotorTalonID2){
        leftMotor = new TalonCan(leftMotorTalonID);
        rightMotor = new TalonCan(rightMotorTalonID);
        leftMotor2 = new TalonCan(leftMotorTalonID2);
        rightMotor2 = new TalonCan(rightMotorTalonID2);
    }//end constructor TalonDriveBase
    
    public SRXDriveBase(TalonCan leftMotor, TalonCan rightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }//end constructor TalonDriveBase

    public SRXDriveBase(TalonCan leftMotor1, TalonCan leftMotor2, TalonCan rightMotor1, TalonCan rightMotor2) {
        this.leftMotor = leftMotor1;
        this.leftMotor = leftMotor2;
        this.rightMotor = rightMotor1;
        this.rightMotor = rightMotor2;
    }

    protected SRXDriveBase() {
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

    public void drive(double leftPercent, double rightPercent) {
        leftMotor.set(leftPercent);
        rightMotor.set(rightPercent);
    }
    
    public void drive(double percent){
    	leftMotor.set(percent);
    	rightMotor.set(percent);
    }
    
    public void turn(double turnPercent /*positive number goes right negative number goes left*/){
    	leftMotor.set(turnPercent);
    	rightMotor.set(-turnPercent);
    }
    
    public void stop(){
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    
}
