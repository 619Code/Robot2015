package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.hardware.Talon;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.DriverStation;
import org.usfirst.frc.team619.subsystems.drive.TalonDriveBase;

/**
 * Map joystick values to motor values
 * @author CaRobotics
 */
public class TalonTankDriveMappingThread extends RobotThread {
    protected TalonDriveBase driveBase;
    protected Talon leftTalon;
    protected Talon rightTalon;
    protected Talon leftTalon2, rightTalon2;
    protected DriverStation driverStation;
    private boolean firstError = true;
    private final static boolean DEBUG = true;
    private long lastTime = 0;

    public TalonTankDriveMappingThread(TalonDriveBase driveBase,
            DriverStation driverStation, int period, ThreadManager threadManager) {
        super(period, threadManager);
        this.driveBase = driveBase;
        this.driverStation = driverStation;
    }

    protected void cycle() {
        double leftScalePercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Z);
        
        if(leftScalePercent < 0.3){
            leftScalePercent = 0.3;
        }

        double rightPercent = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_Y) * leftScalePercent;
        double leftPercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Y) * leftScalePercent;

        driveBase.getLeftTalon().set(leftPercent);
        driveBase.getRightTalon().setReversed(true);
        driveBase.getRightTalon().set(rightPercent);
        
        if(driveBase.getLeftTalon2() != null && driveBase.getRightTalon2() != null){
            driveBase.getLeftTalon2().set(leftPercent);
            driveBase.getRightTalon2().set(rightPercent);
            if(DEBUG) System.out.println("[TalonTankDriveMappingThread] Using 2nd motors");
        }
       
        if(DEBUG) {
            System.out.println("[TalonTankDriveMappingThread] Left Percent: " + leftPercent + " | Right Percent: " + rightPercent);
        }
    }
}
