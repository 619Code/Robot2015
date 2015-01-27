/*
 * 
 *This class is here for basic reference of the main class code layout and to be
 *able to just copy and paste the base code. 
 * 
 * 
 */

package org.usfirst.frc.team619.robot;

import org.usfirst.frc.team619.hardware.AnalogUltrasonic;
import org.usfirst.frc.team619.hardware.Solenoid;
import org.usfirst.frc.team619.hardware.Talon;
import org.usfirst.frc.team619.logic.AutonomousSelector;
import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.logic.actions.RetrieveCan;
import org.usfirst.frc.team619.logic.actions.RetrieveThreeCans;
import org.usfirst.frc.team619.logic.actions.RetrieveThreeTotes;
import org.usfirst.frc.team619.logic.actions.RetrieveTote;
import org.usfirst.frc.team619.logic.actions.RetrieveToteAndCan;
import org.usfirst.frc.team619.logic.actions.StackTotes;
import org.usfirst.frc.team619.logic.actions.StealCan;
import org.usfirst.frc.team619.logic.mapping.CrabDriveMappingThread;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;
import org.usfirst.frc.team619.subsystems.drive.MecanumDriveBase;
import org.usfirst.frc.team619.subsystems.sensor.SensorBase;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	StealCan stealCan;
	StackTotes stackTotes;
	RetrieveCan retrieveCan;
	RetrieveTote retrieveTote;
	RetrieveThreeCans retrieveThreeCans;
	RetrieveThreeTotes retrieveThreeTotes;
	RetrieveToteAndCan retrieveToteAndCan;
	
	//Logic
	CrabDriveMappingThread driveThread;
	
	//Subsystems
	MecanumDriveBase driveBase;
	SensorBase sensorBase;
	
	//Hardware
	AnalogUltrasonic armSonic;
	AnalogUltrasonic leftSonic;
	AnalogUltrasonic rightSonic;
	AnalogUltrasonic frontLeftSonic;
	AnalogUltrasonic frontRightSonic;
	
	Talon driveFrontLeft;
	Talon driveFrontRight;
	Talon driveBackLeft;
	Talon driveBackRight;
	
	Talon lift;
	
	Solenoid leftHand;
	Solenoid rightHand;
	
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
        
        //Create all robot subsystems (i.e. stuff from org.usfirst.frc.team619.subsystems)
        //If you are creating something not from org.usfirst.frc.team619.subsystems, YER DOING IT WRONG
        
        //threadManager
        threadManager = new ThreadManager();
        
        //driver station
        driverStation = new FourStickDriverStation(1, 2, 3, 4);
        
        //plug into pwm section on Athena
        
        //plug into DIO on the Athena
        
        //plug into pneumatics module
        
        //subsystems
        
        //SmartDashboard setup (all things dealing with the SmartDashboard initialized here)
        autoSelect = new AutonomousSelector();
        
        stealCan = new StealCan(0, 1, threadManager, sensorBase);
        stackTotes = new StackTotes(0, 1, threadManager);
        retrieveCan = new RetrieveCan(0, 1, threadManager);
        retrieveTote = new RetrieveTote(0, 1, threadManager, driveBase, sensorBase);
        retrieveThreeCans = new RetrieveThreeCans(0, 1, threadManager);
        retrieveThreeTotes = new RetrieveThreeTotes(0, 1, threadManager);
        retrieveToteAndCan = new RetrieveToteAndCan(0, 1, threadManager);
        
        autoSelect.addAutonomous("Steal the can", stealCan);
        autoSelect.addAutonomous("Move only our can", retrieveCan);
        autoSelect.addAutonomous("Move only our tote", retrieveTote);
        autoSelect.addAutonomous("Move all three cans", retrieveThreeCans);
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
    	
    	SmartDashboard.putString("Selected Autonomous", autoSelect.getChoice().getName());
    	autoSelect.startChoice();
    	
    }
    /**
     * This function is called when teleop is initialized
     */
    public void teleopInit(){
    	threadManager.killAllThreads(); // DO NOT EVER REMOVE!!!
    }
    /**
     * This function is called periodically (about every 20 ms) during autonomous
     */
    public void autonomousPeriodic() {
    	//runs the autonomous that was chosen using SmartDashboard (may not need this)
    	Scheduler.getInstance().run();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        //display ultrasonic sensor values during match
    	SmartDashboard.putNumber("Front left sonic", frontLeftSonic.getDistanceCM());
    	SmartDashboard.putNumber("Front right sonic", frontRightSonic.getDistanceCM());
    	SmartDashboard.putNumber("Side left sonic", leftSonic.getDistanceCM());
    	SmartDashboard.putNumber("Side right sonic", rightSonic.getDistanceCM());
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
