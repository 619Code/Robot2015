package org.usfirst.frc.team619.logic.mapping;

import org.usfirst.frc.team619.hardware.Joystick;
import org.usfirst.frc.team619.logic.RobotThread;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.DriverStation;
import org.usfirst.frc.team619.subsystems.drive.MecanumDriveBase_Cartesian;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CrabMappingThread_Cartesian extends RobotThread{

	MecanumDriveBase_Cartesian driveBase;
	DriverStation driverStation;
	
	public CrabMappingThread_Cartesian(MecanumDriveBase_Cartesian driveBase, DriverStation driverStation, int period, ThreadManager threadManager){
		super(period, threadManager);
		this.driveBase = driveBase;
		this.driverStation = driverStation;
	}

	@Override
	protected void cycle() {
		
		///System.out.println(System.currentTimeMillis()-lastTime);
        ///lastTime = System.currentTimeMillis();
        
        double scalePercent = driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_THROTTLE);
        if(scalePercent < 0.3){
            scalePercent = 0.3;
        }
        
        double xAxis = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_Y)*-1;
        double yAxis = driverStation.getRightJoystick().getAxis(Joystick.Axis.AXIS_X)*-1;
        double twistAxis = (driverStation.getLeftJoystick().getAxis(Joystick.Axis.AXIS_Y)*-1);
        
        if(xAxis > -0.2 && xAxis < 0.2 && yAxis > -0.2 && yAxis < 0.2 && twistAxis > -0.2 && twistAxis < 0.2)
            driveBase.giveValues(0, 0, 0, 0);
        else
        	driveBase.giveValues(xAxis, yAxis, twistAxis, driveBase.getGyroAngle());
        
        
        
	}
	
}
