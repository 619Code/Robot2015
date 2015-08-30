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

    public SwerveDriveBase(int leftMotorTalonID, int rightMotorTalonID) {
        leftMotor = new TalonCan(leftMotorTalonID);
        rightMotor = new TalonCan(rightMotorTalonID);
        rightMotor.setReversed(true);
    }
    
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
    
    public SwerveDriveBase(TalonCan leftMotor, TalonCan rightMotor, Talon leftTurn, Talon rightTurn){
        this.leftMotor = leftMotor;
        this.leftTurn = leftTurn;
        this.rightMotor = rightMotor;
        this.rightTurn = rightTurn;
        rightMotor.setReversed(true);
    }//end constructor TalonDriveBase

    public SwerveDriveBase(TalonCan leftMotor1, TalonCan leftMotor2, TalonCan rightMotor1, TalonCan rightMotor2, Talon rightMotorTurn1, Talon rightMotorTurn2, Talon leftMotorTurn1, Talon leftMotorTurn2) {
        this.leftMotor = leftMotor1;
        this.leftMotor = leftMotor2;
        this.rightMotor = rightMotor1;
        this.rightMotor = rightMotor2;
        this.leftTurn = leftMotorTurn1;
        this.leftTurn = leftMotorTurn2;
        this.rightTurn = rightMotorTurn1;
        this.rightTurn = rightMotorTurn2;
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
    
    public Talon getLeftTalon2(){
        return leftTurn;
    }//end Talon getLeftTalon2
    
    public Talon getRightTalon2(){
        return rightTurn;
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
