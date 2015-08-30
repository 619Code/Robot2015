/*
 * 
 *This class is here for basic reference of the main class code layout and to be
 *able to just copy and paste the base code. 
 * 
 * 
 */

package org.usfirst.frc.team619.robot;

import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.hardware.Talon;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.logic.mapping.SwerveDriveMappingThread;
import org.usfirst.frc.team619.subsystems.DriverStation;
import org.usfirst.frc.team619.subsystems.drive.SwerveDriveBase;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SwerveTest extends IterativeRobot {
    
	//declare all variables and objects here
	
	//Basics
	ThreadManager threadManager;
	DriverStation driverStation;

	//Logic
	SwerveDriveMappingThread driveThread;
	
	//Subsystems
	SwerveDriveBase driveBase;
	
	//Hardware
	TalonCan driveLeft;
	TalonCan driveRight;
	
	Talon turnLeft;
	Talon turnRight;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("\n");// shows code is working
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println("// Cavalier Robotics                     TEAM 619   //");
        System.out.println("// 2015 Jenga                                       //");
        System.out.println("//////////////////////////////////////////////////////\n");
        
        //Create all robot subsystems (i.e. stuff from/for org.usfirst.frc.team619.subsystems)
        //If you are creating something not from/for org.usfirst.frc.team619.subsystems, YER DOING IT WRONG
        
        //threadManager
        threadManager = new ThreadManager();
        
        //driver station
        driverStation = new DriverStation(1, 2);
        
        //CAN
        driveLeft = new TalonCan(1);
        driveRight = new TalonCan(2);
        
        turnLeft = new Talon(4);
        turnRight = new Talon(3);
        
        //subsystems
        driveBase = new SwerveDriveBase(driveLeft, driveRight, turnLeft, turnRight);
        
    }

    /**
     *This function is called when autonomous is initialized
     */
    public void autonomousInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    	System.out.println("**************************autonomousInit( )************************************");
	
    	//retrieve selected autonomous and run it
    	//autoSelect.startChoice();
    }
    /**
     * This function is called when teleop is initialized
     */
    public void teleopInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    	
    	//sensorThread = new SensorBaseMappingThread(sensorBase, driverStation, 0, threadManager);
    	//driveThread = new SRXTankDriveMappingThread(driveBase, driverStation, 0, threadManager);
    	//flapperThread = new FlapperMappingThread(flapper,driverStation,0,threadManager);
    
    	//start threads
    	//sensorThread.start();
    	//driveThread.start();
    	//flapperThread.start();
    	
    	//start cameras again because they should be killed by threadManager
    	//sensorBase.startCamera("cam0");
    	//sensorBase.startNetworkCamera();\	
    }
    /**
     * This function is called periodically (about every 20 ms) during autonomous
     */
    public void autonomousPeriodic() {
    	//runs the autonomous that was chosen using SmartDashboard (may not need this)
//    	Scheduler.getInstance().run();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	//if ( lift1.getPosition( ) >= 393874.0 ) { lift1.set(0); }
    	//if ( lift1.getPosition( ) <= -250000.0 ) { lift1.set(0); }
    	// 393874   ----   between level3 & level4
 
//    	//flipping override
//    	if(sensorBase.getAthenaAccelerometer().getZ() > 10){
//    		
//    		flapper.setLevel(0);
//    		
//    		while(sensorBase.getAthenaAccelerometer().getZ() > 10)
//    			driveBase.drive(-1);
//    		
//    	}
    	//System.out.println("tPOSITION:  " + lift1.getPosition( ));
    	
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    public void disabledInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    }
    
    public void disabledPeriodic(){}
    
    public void disabledContinuous(){}
    
}
