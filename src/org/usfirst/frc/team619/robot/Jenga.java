/*
 * 
 *This class is here for basic reference of the main class code layout and to be
 *able to just copy and paste the base code. 
 * 
 * 
 */

package org.usfirst.frc.team619.robot;

import org.usfirst.frc.team619.hardware.AnalogUltrasonic;
import org.usfirst.frc.team619.hardware.AthenaAccelerometer;
import org.usfirst.frc.team619.hardware.DualInputSolenoid;
import org.usfirst.frc.team619.hardware.LimitSwitch;
import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.logic.AutonomousSelector;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.logic.actions.RetrieveCanTank;
import org.usfirst.frc.team619.logic.actions.RetrieveThreeTotesTank;
import org.usfirst.frc.team619.logic.actions.RetrieveToteAndCanTank;
import org.usfirst.frc.team619.logic.actions.RetrieveToteTank;
import org.usfirst.frc.team619.logic.actions.StackTotesTank;
import org.usfirst.frc.team619.logic.mapping.FlapperMappingThread;
import org.usfirst.frc.team619.logic.mapping.SRXTankDriveMappingThread;
import org.usfirst.frc.team619.logic.mapping.SensorBaseMappingThread;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;
import org.usfirst.frc.team619.subsystems.drive.SRXDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Jenga extends IterativeRobot {
    
	//declare all variables and objects here
	
	//Basics
	ThreadManager threadManager;
	FourStickDriverStation driverStation;
	
	//Autonomous objects
	AutonomousSelector autoSelect;
	StackTotesTank stackTotes;
	RetrieveCanTank retrieveCan;
	RetrieveToteTank retrieveTote;
	RetrieveThreeTotesTank retrieveThreeTotes;
	RetrieveToteAndCanTank retrieveToteAndCan;
	
	//Logic
	SensorBaseMappingThread sensorThread;
	SRXTankDriveMappingThread driveThread;
	//FlapperMappingThread flapperThread;
	
	//Subsystems
	SRXDriveBase driveBase;
	SensorBase sensorBase;
	Flapper flapper;
	
	//Hardware
	
	AthenaAccelerometer accel;
	
	AnalogUltrasonic leftSonic;
	AnalogUltrasonic rightSonic;
	AnalogUltrasonic frontLeftSonic;
	AnalogUltrasonic frontRightSonic;
	
	LimitSwitch bottom;
	LimitSwitch levelOne;
	LimitSwitch levelTwo;
	LimitSwitch levelThree;
	LimitSwitch top;
	
	TalonCan driveLeft;
	TalonCan driveRight;
	
	TalonCan lift1;
	TalonCan lift2;
	
	DualInputSolenoid hands;
	
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
        driverStation = new FourStickDriverStation(1, 2, 3, 4);
        
        //plug into pwm section on Athena
        
        //plug into DIO on Athena
        bottom = new LimitSwitch(0);
        levelOne = new LimitSwitch(1);
        levelTwo = new LimitSwitch(2);
        levelThree = new LimitSwitch(3);
        top = new LimitSwitch(4);
        
        //plug into Analog Input on Athena
        leftSonic = new AnalogUltrasonic(0);
        rightSonic = new AnalogUltrasonic(3);
        frontLeftSonic = new AnalogUltrasonic(1);
        frontRightSonic = new AnalogUltrasonic(2);
        
        //plug into I2C on Athena
        accel = new AthenaAccelerometer();
        
        //plug into pneumatics module
        hands = new DualInputSolenoid(0, 1);
        
        //CAN
        driveLeft = new TalonCan(1);
        driveRight = new TalonCan(2);
        
        lift1 = new TalonCan(3);
        lift2 = new TalonCan(4);
        
        //subsystems
        sensorBase = new SensorBase(accel);
        sensorBase.addUltrasonicSensor(leftSonic);
        sensorBase.addUltrasonicSensor(rightSonic);
        sensorBase.addUltrasonicSensor(frontLeftSonic);
        sensorBase.addUltrasonicSensor(frontRightSonic);
        driveBase = new SRXDriveBase(driveLeft, driveRight);
        flapper = new Flapper(lift1, lift2, hands, bottom, levelOne, levelTwo, levelThree, top);
        
        
        //SmartDashboard setup (all things dealing with the SmartDashboard initialized here)
        autoSelect = new AutonomousSelector();
        
        retrieveCan = new RetrieveCanTank(0, 1, threadManager, driveBase, flapper);
        retrieveTote = new RetrieveToteTank(0, 1, threadManager, driveBase, flapper);
        stackTotes = new StackTotesTank(0, 1, threadManager, driveBase, flapper, sensorBase);
        retrieveThreeTotes = new RetrieveThreeTotesTank(0, 1, threadManager, driveBase, flapper, sensorBase);
        retrieveToteAndCan = new RetrieveToteAndCanTank(0, 1, threadManager, driveBase, flapper, sensorBase);
        
        autoSelect.addAutonomous("Move only our can", retrieveCan);
        autoSelect.addAutonomous("Move only our tote", retrieveTote);
        autoSelect.addAutonomous("Move all three totes", retrieveThreeTotes);
        autoSelect.addAutonomous("Move only our tote and can", retrieveToteAndCan);
        autoSelect.addAutonomous("Begin stacking totes from landfill", stackTotes);
        
        SmartDashboard.putData("Autonomous Selector", autoSelect.getAutoChooser());
        
    }

    /**
     *This function is called when autonomous is initialized
     */
    public void autonomousInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    	
    	//retrieve selected autonomous and run it
    	SmartDashboard.putString("Selected Autonomous", autoSelect.getChoice().getName());
    	autoSelect.startChoice();
    	
    	//start cameras
    	sensorBase.startCamera("cam0");
    	sensorBase.startNetworkCamera();
    	
    }
    /**
     * This function is called when teleop is initialized
     */
    public void teleopInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    	
    	sensorThread = new SensorBaseMappingThread(sensorBase, driverStation, 0, threadManager);
    	driveThread = new SRXTankDriveMappingThread(driveBase, driverStation, 0, threadManager);
    	//flapperThread = new FlapperMappingThread(flapper,driverStation,0,threadManager);
    
    	//start threads
    	sensorThread.start();
    	driveThread.start();
    	//flapperThread.start();
    	
    	//start cameras again because they should be killed by threadManager
    	sensorBase.startCamera("cam0");
    	sensorBase.startNetworkCamera();
    	
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
    	
    	//flipping override
    	if(sensorBase.getAthenaAccelerometer().getZ() > 10){
    		
    		flapper.setLevel(0);
    		
    		while(sensorBase.getAthenaAccelerometer().getZ() > 10)
    			driveBase.drive(-1);
    		
    	}
    	
        //display ultrasonic sensor values during match
    	SmartDashboard.putNumber("Front left sonic", sensorBase.getUltrasonicSensor(1).getDistanceCM());
    	SmartDashboard.putNumber("Front right sonic", sensorBase.getUltrasonicSensor(2).getDistanceCM());
    	SmartDashboard.putNumber("Side left sonic", sensorBase.getUltrasonicSensor(0).getDistanceCM());
    	SmartDashboard.putNumber("Side right sonic", sensorBase.getUltrasonicSensor(3).getDistanceCM());
    	
    	//display tilt and tote level
    	SmartDashboard.putNumber("Tilt", sensorBase.getAthenaAccelerometer().getZ());
    	SmartDashboard.putNumber("Tote Level", flapper.getCurrentSwitch());
    	
    	//display status of camera
    	SmartDashboard.putBoolean("Upper Camera On", sensorBase.getCamera().isOn());
    	
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    public void disabledInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    }
    
    
}
