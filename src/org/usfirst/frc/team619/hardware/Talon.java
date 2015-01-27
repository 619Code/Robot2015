package org.usfirst.frc.team619.hardware;

/**
 * 
 * 
 * @author CaRobotics
 */
public class Talon {
    protected  edu.wpi.first.wpilibj.Talon talon;
    protected double factor = 1.0;
    
    public Talon(int channel/*where the talon plugs into on the PWM section of Athena*/) {
        talon = new edu.wpi.first.wpilibj.Talon(channel);
    }
    
    /**
     * @param value Range from -1 to 1
     */
    public void set(double value) {
        talon.set(value * factor);
    }

    public double getSpeed() {
        return talon.getSpeed();
    }
    
    public void setReversed(boolean rev){
        if(rev){
            factor = -1.0;
        }else{
            factor = 1.0;
        }
    }
    
}
