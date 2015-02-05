/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.hardware.Talon;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.DriverStation;
import org.usfirst.frc.team619.subsystems.drive.MecanumDriveBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author caadmin
 */
public class CrabDriveMappingThread extends RobotThread {
    protected MecanumDriveBase mecanumDriveBase;
    protected DriverStation driverStation;
    private boolean firstError = true;
    private final static boolean DEBUG = false;
    private long lastTime = 0;
    protected final double angularConstant = 0.03;
    protected Talon topLeftTalon;
    protected Talon topRightTalon;
    protected Talon bottomLeftTalon;
    protected Talon bottomRightTalon;
    
   // Talon topLefttalon, topRighttalon, bottomLefttalon, bottomRighttalon;

    public CrabDriveMappingThread(MecanumDriveBase mecanumDriveBase,
            DriverStation driverStation, int period, ThreadManager threadManager) {
        super(period, threadManager);
        this.mecanumDriveBase = mecanumDriveBase;
        this.driverStation = driverStation;
    }

    protected void cycle() {
        
        ///System.out.println(System.currentTimeMillis()-lastTime);
        ///lastTime = System.currentTimeMillis();
        
        double scalePercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_THROTTLE);  //fix
        if(scalePercent < 0.3){
            scalePercent = 0.3;
        }
        
        double xAxis = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_Y)*-1;
        double yAxis = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_X)*-1;
        double twistAxis = (driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Y)*-1);
        
        //gets percentages (numbers from -1 to 1) from the joystick's axes used for driving
        double percent = yAxis;
        double sidepercent = xAxis;
        double turnpercent = twistAxis;
        
        double topLeftpercent = 0;
        double topRightpercent = 0;
        double bottomLeftpercent = 0;
        double bottomRightpercent = 0;
        
        if(xAxis > -0.2 && xAxis < 0.2 && yAxis > -0.2 && yAxis < 0.2 && twistAxis > -0.2 && twistAxis < 0.2){
            topLeftpercent = 0;
            topRightpercent = 0;
            bottomLeftpercent = 0;
            bottomRightpercent = 0;
        } else {

            topLeftpercent = -sidepercent + percent + turnpercent;
            topRightpercent = sidepercent + percent - turnpercent;
            bottomLeftpercent = sidepercent + percent + turnpercent;
            bottomRightpercent = -sidepercent + percent - turnpercent;

            topLeftpercent *= scalePercent;
            topRightpercent *= scalePercent;
            bottomLeftpercent *= scalePercent;
            bottomRightpercent *= scalePercent;
            
        }
        
        try {
            mecanumDriveBase.getTopleftTalon().set(topLeftpercent);
            mecanumDriveBase.getToprightTalon().set(topRightpercent);
            mecanumDriveBase.getBottomleftTalon().set(bottomLeftpercent);
            mecanumDriveBase.getBottomrightTalon().set(bottomRightpercent);
        } catch (Exception e) {
            if (firstError || DEBUG) {
                e.printStackTrace();
            }
        }
        
        if(DEBUG){
            
            System.out.println("[MechanumDriveMappingThread] percent: "+ percent);
            System.out.println("[MechanumDriveMappingThread] sidepercent: "+ sidepercent);
            System.out.println("[MechanumDriveMappingThread] turnpercent: "+ turnpercent);
            
            SmartDashboard.putNumber("percent", percent);
            SmartDashboard.putNumber("Side Percent", sidepercent);
            SmartDashboard.putNumber("Turn Percent", turnpercent);
            
        }
            
        shift();
    }
    
    protected void shift(){
        if(driverStation.getLeftJoystick().getButton(Joystick.Button.BUTTON11)){
            System.out.println("[MechanumDriveMappingThread] Shifting High");
            mecanumDriveBase.shiftHigh();
        }
        if(driverStation.getLeftJoystick().getButton(Joystick.Button.BUTTON10)){
            System.out.println("[MechanumDriveMappingThread] Shifting Low");
            mecanumDriveBase.shiftLow();
        }
    }
}
