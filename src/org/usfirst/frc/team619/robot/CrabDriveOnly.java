/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team619.robot;

import org.usfirst.frc.team619.logic.ThreadManager;
import org.usfirst.frc.team619.logic.mapping.CrabDriveMappingThread;
import org.usfirst.frc.team619.logic.mapping.SRXCrabDriveMappingThread;
import org.usfirst.frc.team619.subsystems.FourStickDriverStation;
import org.usfirst.frc.team619.subsystems.drive.SRXMecanumDriveBase;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CrabDriveOnly extends IterativeRobot{
    
    // Robot Systems (stuff from org.carobotics.subsystems)
    FourStickDriverStation driverStation;
    SRXMecanumDriveBase driveBase;
    
    // Thread Manager	
    ThreadManager threadManager = new ThreadManager();
    
    // Logic Threads (stuff from org.carobotics.logic)
    SRXCrabDriveMappingThread driveThread;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

        System.out.println("\n");
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println("//     Cavalier Robotics                 TEAM 619   //");
        System.out.println("//     2015 Crab Drive Only                         //");
        System.out.println("//////////////////////////////////////////////////////\n");
        
        // Create all robot subsystems (i.e. stuff from org.carobotics.subsystems)
        // If you are creating something not from org.carobotics.subsystems, YER DOING IT WRONG
        
        driverStation = new FourStickDriverStation(1, 2, 3, 4);
        driveBase = new SRXMecanumDriveBase(3, 1, 2, 4);
            
        //autonomousSelector = new AutonomousSelector();
    }

    public void autonomousInit() {
        threadManager.killAllThreads(); // DO NOT REMOVE!!!
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
        threadManager.killAllThreads(); // DO NOT REMOVE!!!

        driveThread = new SRXCrabDriveMappingThread(driveBase, driverStation, 15, threadManager);
        driveThread.start();    
    }
    
    public void teleopPeriodic(){
        SmartDashboard.putNumber("FL: ", driveBase.getTopleftTalon().getSpeed());
        SmartDashboard.putNumber("FR: ", driveBase.getToprightTalon().getSpeed());
        SmartDashboard.putNumber("BL: ", driveBase.getBottomleftTalon().getSpeed());
        SmartDashboard.putNumber("BR: ", driveBase.getBottomrightTalon().getSpeed());
    }

    public void teleopContinuous() {
    }

    public void disabledInit() {
        threadManager.killAllThreads(); // DO NOT REMOVE!!!
    }
    
    public void disabledPeriodic(){
    }
    
    public void disabledContinuous(){
    }
    
    public void testInit(){
    }
    
    public void testPeriodic(){
    }
    
    public void testDisabled(){
    }
}
