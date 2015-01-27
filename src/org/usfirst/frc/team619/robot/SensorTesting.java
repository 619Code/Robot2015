
/*
 * 
 *This class is here for the testing of sensors and the SmartDashboard
 *both of which  
 * 
 */

package org.usfirst.frc.team619.robot;

import org.usfirst.frc.team619.hardware.AnalogUltrasonic;
import org.usfirst.frc.team619.hardware.LimitSwitch;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;
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
	
	ThreadManager threadManager;
	FourStickDriverStation driverStation;
	
	AnalogUltrasonic sensor;
	LimitSwitch limitSwitch;
	
	SensorBase sensorBase;
	
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
        
        //plug into pneumatics module
        
        //subsystems
        sensorBase = new SensorBase(sensor);
        
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
    	SmartDashboard.putNumber("ultrasonic", sensorBase.getUltrasonicSensor(3).getDistanceCM());
    	SmartDashboard.putBoolean("LimitSwitch", limitSwitch.get());
    }
    
    public void disabledInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    }
    
    
}
