
package org.usfirst.frc.team619.subsystems.drive;

import org.usfirst.frc.team619.hardware.DigitalEncoder;
import org.usfirst.frc.team619.hardware.TalonCan;
/**
 * Mecanum drive base that is based off the code for the tank drive base, just has extra motors and servos added
 * @author admin
 */
public class SRXMecanumDriveBase{
    
	protected TalonCan topLeftmotor, topRightmotor, bottomLeftmotor, bottomRightmotor;
	protected DigitalEncoder tL, tR, bL, bR;
    
    public SRXMecanumDriveBase(int topLeftmotorChannel, int topRightmotorChannel, int bottomLeftmotorChannel, int bottomRightmotorChannel){
        topLeftmotor = new TalonCan(topLeftmotorChannel);
        topRightmotor = new TalonCan(topRightmotorChannel);
        bottomLeftmotor = new TalonCan(bottomLeftmotorChannel);
        bottomRightmotor = new TalonCan(bottomRightmotorChannel);
    }
    
    public SRXMecanumDriveBase(TalonCan topLeftmotor, TalonCan topRightmotor, TalonCan bottomLeftmotor, TalonCan bottomRightmotor){
    	this.topLeftmotor = topLeftmotor;
    	this.topRightmotor = topRightmotor;
    	this.bottomLeftmotor = bottomLeftmotor;
    	this.bottomRightmotor = bottomRightmotor;
    }
    
    public SRXMecanumDriveBase(TalonCan topLeftmotor, TalonCan topRightmotor, TalonCan bottomLeftmotor, TalonCan bottomRightmotor, 
    		DigitalEncoder tL, DigitalEncoder tR, DigitalEncoder bL, DigitalEncoder bR){
    	
    	this.topLeftmotor = topLeftmotor;
    	this.topRightmotor = topRightmotor;
    	this.bottomLeftmotor = bottomLeftmotor;
    	this.bottomRightmotor = bottomRightmotor;
    	
    	this.tL = tL;
    	this.tR = tR;
    	this.bL = bL;
    	this.bR = bR;
    	
    }
    
    public TalonCan getTopleftTalon() {
        return topLeftmotor;
    }

    public TalonCan getToprightTalon() {
        return topRightmotor;
    }
    
    public TalonCan getBottomleftTalon() {
        return bottomLeftmotor;    
    }
    
    public TalonCan getBottomrightTalon() {
        return bottomRightmotor;    
    }
    
    public DigitalEncoder getTL(){
    	return tL;
    }
    
    public DigitalEncoder getTR(){
    	return tR;
    }
    
    public DigitalEncoder getBL(){
    	return bL;
    }
    
    public DigitalEncoder getBR(){
    	return bR;
    }
    
    //drive forward and bakcwards by having all motors go in same direction
    public void drive(double percent){
        topLeftmotor.set(percent);
        topRightmotor.set(percent);
        bottomLeftmotor.set(percent);
        bottomRightmotor.set(percent);
    }
    
    //slide left to right by having front motors go in the opposite direction of the back motors
    public void slide(double sidepercent){
        topLeftmotor.set(-sidepercent);
        topRightmotor.set(-sidepercent);
        bottomLeftmotor.set(sidepercent);
        bottomRightmotor.set(sidepercent);
    }
    
    //turn left and right by having left-side motors go in opposite direction of right-side motors
    public void turn(double turnpercent){
        topLeftmotor.set(-turnpercent);
        topRightmotor.set(turnpercent);
        bottomLeftmotor.set(-turnpercent);
        bottomRightmotor.set(turnpercent);
    }

    public void stop(){
    	topLeftmotor.set(0);
    	topRightmotor.set(0);
    	bottomLeftmotor.set(0);
    	bottomRightmotor.set(0);
    }
        
}
