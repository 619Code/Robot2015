
/*
 * 
 *This class is here for the testing of sensors and the SmartDashboard
 *both of which  
 * 
 */

package org.usfirst.frc.team619.robot;

import org.usfirst.frc.team619.hardware.AnalogUltrasonic;
import org.usfirst.frc.team619.hardware.DigitalEncoder;
import org.usfirst.frc.team619.hardware.I2CAccelerometer;
import org.usfirst.frc.team619.hardware.LimitSwitch;
import org.usfirst.frc.team619.hardware.TalonCan;
import org.usfirst.frc.team619.logic.AutonomousSelector;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.logic.actions.StackTotesMecanum;
import org.usfirst.frc.team619.logic.actions.StealCanMecanum;
import org.usfirst.frc.team619.logic.mapping.SRXCrabDriveMappingThread;
import org.usfirst.frc.team619.logic.mapping.SensorBaseMappingThread;
import org.usfirst.frc.team619.subsystems.Flapper;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;
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
public class SensorTesting extends IterativeRobot {
    
	//declare all variables and objects here
	
	AutonomousSelector autoSelect;
	StealCanMecanum stealCan;
	StackTotesMecanum stackTotes;
	
	ThreadManager threadManager;
	FourStickDriverStation driverStation;
	
	SensorBaseMappingThread sensorThread;
	SRXCrabDriveMappingThread driveThread;
	
	I2CAccelerometer accel;
	
	AnalogUltrasonic sensor;
	LimitSwitch limitSwitch;	
	
	SensorBase sensorBase;
	SRXMecanumDriveBase driveBase;
	
	Flapper flapper;
	
	TalonCan topL;
	TalonCan topR;
	TalonCan botL;
	TalonCan botR;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("\n");// shows code is working
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println("// Cavalier Robotics                     TEAM 619   //");
        System.out.println("// 2015 Sensor Testing                              //");
        System.out.println("//////////////////////////////////////////////////////\n");
        
        //Create all robot subsystems (i.e. stuff from org.usfirst.frc.team619.subsystems)
        //If you are creating something not from org.usfirst.frc.team619.subsystems, YER DOING IT WRONG
        
        //threadManager
        threadManager = new ThreadManager();
        
        //driver station
        driverStation = new FourStickDriverStation(1, 2, 3, 4);
        
        //plug into pwm section on Athena
        
        //plug into DIO on the Athena
        limitSwitch = new LimitSwitch(0);
        
        //plug into analog input on Athena
        sensor = new AnalogUltrasonic(3);
        
        //plug into I2C port on Athena
        accel = new I2CAccelerometer();
        
        //plug into pneumatics module
        
        //CAN
        topL = new TalonCan(2);
        topR = new TalonCan(3);
        botL = new TalonCan(1);
        botR = new TalonCan(4);
        
        //subsystems
        driveBase = new SRXMecanumDriveBase(topL, topR, botL, botR);
        sensorBase = new SensorBase(accel);
        sensorBase.addUltrasonicSensor(sensor);
        
        autoSelect = new AutonomousSelector();
        
        stealCan = new StealCanMecanum(0, 1, threadManager, sensorBase);
        stackTotes = new StackTotesMecanum(0, 1, threadManager, driveBase, flapper, sensorBase);
        
        autoSelect.addAutonomous("Steal the can", stealCan);
        autoSelect.addAutonomous("Begin stacking totes from landfill", stackTotes);
        
        SmartDashboard.putData("Autonomous Selector", autoSelect.getAutoChooser());
        
    }

    /**
     *This function is called when autonomous is initialized
     */
    public void autonomousInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    	
    }
    /**
     * This function is called when teleop is initialized
     */
    public void teleopInit(){
    	
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    	
    	sensorThread = new SensorBaseMappingThread(sensorBase, driverStation, 0, threadManager);
    	driveThread = new SRXCrabDriveMappingThread(driveBase, driverStation, 0, threadManager);
    	
    	driveThread.start();

    	sensorBase.startNetworkCamera();
    	
    }
    /**
     * This function is called periodically during autonomous
     * In general you shouldn't use this
     */
    public void autonomousPeriodic() {

    }
    /**
     * This function is called periodically during operator control
     * In general you shouldn't use this
     */
    public void teleopPeriodic() {
    	
    	if(sensorBase.getI2CAccelerometer().getX() > 10){
    		
    		driveThread.stopRunning();
    		driveBase.slide(-1);
    		flapper.setLevel(0);
    		driveThread.run();
    		
    	}
    	
    	SmartDashboard.putNumber("ultrasonic", sensorBase.getUltrasonicSensor(3).getDistanceCM());
    	SmartDashboard.putBoolean("LimitSwitch", limitSwitch.get());
    	
    	try{
    		SmartDashboard.putBoolean("Camera on", sensorBase.getCamera().isOn());
    	}catch(Exception e){}
    	
    }
    /**
     * This function is called periodically during test mode
     * In general you shouldn't use this
     */
    public void testPeriodic() {
    
    }
    public void disabledPeriodic(){
    	
    }
    
    public void teleopContinuous(){
    }
    
    public void disabledInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    }
    
    
}
