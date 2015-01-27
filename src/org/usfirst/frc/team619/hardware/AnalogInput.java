package org.usfirst.frc.team619.hardware;

/**
 * @author CaRobotics
 */
public class AnalogInput {
    
    private AnalogInput analog;
    
    public AnalogInput(int channel) {
        analog = new AnalogInput(channel);
    }
    
    
//        final int value = getValue(channel);
//        final long LSBWeight = getLSBWeight(channel);
//        final int offset = getOffset(channel);
//        final double voltage = (LSBWeight * 1.0e-9 * value) - (offset * 1.0e-9);
    
    public AnalogInput(int moduleNumber, int channel) {
        analog = new AnalogInput(moduleNumber, channel);
    }
    
    public double get() {
        return analog.getVoltage();
    }
    
    public double getVoltage() {
        return analog.getVoltage();
    }
    
    public int getChannel() {
        return analog.getChannel();
    }
    
//        List<GameUnit>[] map = new ArrayList<GameUnit>[100 * 100];
//        for(List<GameUnit> list : map) {
//            list = new List<GameUnit>();
//        }
//        
//        List<GameUnit> list = map[100 + 63];
}
