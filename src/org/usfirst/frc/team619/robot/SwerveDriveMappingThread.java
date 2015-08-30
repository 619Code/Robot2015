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
        double leftScalePercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Z);
        
        if(leftScalePercent < 0.2){
            leftScalePercent = 0.2;
        }

        double rightPercent = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_Y) * -leftScalePercent;
        double leftPercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Y) * leftScalePercent;

        driveBase.getLeftTalon().set(leftPercent);
        driveBase.getRightTalon().setReversed(true);
        driveBase.getRightTalon().set(rightPercent);
        
        if(driveBase.getLeftTalon2() != null && driveBase.getRightTalon2() != null){
            driveBase.getLeftTalon2().set(leftPercent);
            driveBase.getRightTalon2().set(rightPercent);
            if(DEBUG) System.out.println("[TalonTankDriveMappingThread] Using 2nd motors");
        }
        
//        double lifts= driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_TWIST);
//	    driveBase.getLiftTalon1().set(lifts);
//	    driveBase.getLiftTalon2().set(lifts);
//	    System.out.println("LIFT SPEED:" + lifts);
       
        if(DEBUG) {
            System.out.println("[TalonTankDriveMappingThread] Left Percent: " + leftPercent + " | Right Percent: " + rightPercent );
        }
    }
}
