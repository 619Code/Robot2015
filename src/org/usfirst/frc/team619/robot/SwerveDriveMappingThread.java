package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.hardware.Talon;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.DriverStation;
import org.usfirst.frc.team619.subsystems.drive.SwerveDriveBase;

/**
 * Map joystick values to motor values
 * @author CaRobotics
 */
public class SwerveDriveMappingThread extends RobotThread {
    protected SwerveDriveBase driveBase;
    protected TalonCan leftTalon;
    protected TalonCan rightTalon;
    protected TalonCan leftTalon2, rightTalon2;
    protected Talon leftTalonTurn;
    protected Talon rightTalonTurn;
    protected Talon leftTalonTurn2, rightTalonTurn2;
    protected DriverStation driverStation;
    private boolean firstError = true;
    private final static boolean DEBUG = true;
    private long lastTime = 0;

    public SwerveDriveMappingThread(SwerveDriveBase driveBase,
            DriverStation driverStation, int period, ThreadManager threadManager) {
        super(period, threadManager);
        this.driveBase = driveBase;
        this.driverStation = driverStation;
    }

    protected void cycle() {
        double scalePercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Z);
        
        if(scalePercent < 0.2){
            scalePercent = 0.2;
        }
        
        double xAxis = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_X);
        double yAxis = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Y);
        //when turning Saitek Aviator Joystick that is the throttle axis as determined using the Joystick Explorer UI developed by FIRST
        //double xRightAxis = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_X);
        
        //gets percentages (numbers from -1 to 1) from the joystick's axes used for driving
        double drivePercent = yAxis * scalePercent;
        //double spinPercent = xRightAxis * scalePercent;
        double turnPercent = xAxis * scalePercent;
        
        
        //
        //TODO: figure out logic regarding each wheel when rotating
        //double leftPercent = spinPercent;
        //double rightPercent = spinPercent;
        //double leftPercent2 = spinPercent;
        //double rightPercent2 = spinPercent;
        
        try {
            if(xAxis > -0.2 && xAxis < 0.2 && yAxis > -0.2 && yAxis < 0.2){
                driveBase.getLeftTalon().set(0);
                driveBase.getRightTalon().set(0);
                driveBase.getLeftTalon2().set(0);
                driveBase.getRightTalon2().set(0);
                driveBase.getLeftTurn().set(0);
                driveBase.getRightTurn().set(0);
                driveBase.getLeftTurn2().set(0);
                driveBase.getRightTurn2().set(0);
            }else{
                driveBase.getLeftTalon().set(drivePercent);
                driveBase.getRightTalon().set(-drivePercent);
                driveBase.getLeftTalon2().set(drivePercent);
                driveBase.getRightTalon2().set(-drivePercent);
                driveBase.getLeftTurn().set(turnPercent);
                driveBase.getRightTurn().set(-turnPercent);
                driveBase.getLeftTurn2().set(turnPercent);
                driveBase.getRightTurn2().set(-turnPercent);
            }
        } catch (Exception e) {
            if (firstError || DEBUG) {
                e.printStackTrace();
            }
        }
        
        if(DEBUG){
        //Write debug code    
        }
            
    }

}
