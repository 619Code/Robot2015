package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.hardware.Talon;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.DriverStation;
import org.usfirst.frc.team619.subsystems.drive.SwerveDriveBase;
//import org.usfirst.frc.team619.subsystems.drive.TurndriveBase;

/**
 * Map joystick values to motor values
 * @author CaRobotics
 */
public class SwerveDriveMappingThread extends RobotThread {
//    protected TurndriveBase driveBase;
	protected SwerveDriveBase driveBase;
    protected Talon leftTalon;
    protected Talon rightTalon;
    protected Talon leftTalon2, rightTalon2;
    protected TalonCan leftTalonTurn;
    protected TalonCan rightTalonTurn;
    protected TalonCan leftTalonTurn2, rightTalonTurn2;
    protected DriverStation driverStation;
    private boolean firstError = true;
    private final static boolean DEBUG = true;

//    public SwerveDriveMappingThread(TurndriveBase driveBase,
    public SwerveDriveMappingThread(SwerveDriveBase driveBase,
            DriverStation driverStation, int period, ThreadManager threadManager) {
        super(period, threadManager);
//        this.driveBase = driveBase;
        this.driverStation = driverStation;
    }

    protected void cycle() {
        double scalePercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Z);
        
        if(scalePercent < 0.2){
            scalePercent = 0.2;
        }
        
        double xAxis = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_X);
        double yAxis = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Y);
        //double xRightAxis = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_X);
        
        
        double drivePercent = yAxis * scalePercent;
        //double spinPercent = xRightAxis * scalePercent;
        double turnPercent = xAxis * scalePercent;
        

        try {
            if(xAxis > -0.3 && xAxis < 0.3 && yAxis > -0.3 && yAxis < 0.3){
            	
                }else{
                	
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
