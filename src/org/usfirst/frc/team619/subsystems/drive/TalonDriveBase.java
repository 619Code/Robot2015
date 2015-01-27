package org.usfirst.frc.team619.subsystems.drive;

import org.usfirst.frc.team619.hardware.Talon;

/**
 *
 * @author admin
 */
public class TalonDriveBase {
    
	private Talon leftMotor, rightMotor, leftMotor2, rightMotor2;

    public TalonDriveBase(int leftMotorTalonID, int rightMotorTalonID) {
        leftMotor = new Talon(leftMotorTalonID);
        leftMotor.setReversed(true);
        rightMotor = new Talon(rightMotorTalonID);
        rightMotor.setReversed(true);
    }
    
    public TalonDriveBase(int leftMotorTalonID, int rightMotorTalonID, int leftMotorTalonID2, int rightMotorTalonID2){
        leftMotor = new Talon(leftMotorTalonID);
        rightMotor = new Talon(rightMotorTalonID);
        leftMotor2 = new Talon(leftMotorTalonID2);
        rightMotor2 = new Talon(rightMotorTalonID2);
    }//end constructor TalonDriveBase
    
    public TalonDriveBase(Talon leftMotor, Talon rightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }//end constructor TalonDriveBase

    public TalonDriveBase(Talon leftMotor1, Talon leftMotor2, Talon rightMotor1, Talon rightMotor2) {
        this.leftMotor = leftMotor1;
        this.leftMotor = leftMotor2;
        this.rightMotor = rightMotor1;
        this.rightMotor = rightMotor2;
    }

    protected TalonDriveBase() {
        // USE WITH CAUTION
        // Allows child classes to bypass creating the Jaguars
    }

    public Talon getLeftTalon() {
        return leftMotor;
    }

    public Talon getRightTalon() {
        return rightMotor;
    }
    
    public Talon getLeftTalon2(){
        return leftMotor2;
    }//end Talon getLeftTalon2
    
    public Talon getRightTalon2(){
        return rightMotor2;
    }//end Talon getRightTalon2

    public void drive(double leftPercent, double rightPercent) {
        leftMotor.set(leftPercent);
        rightMotor.set(rightPercent);
    }
    
}
