package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class RangeFinder extends AnalogInput {

	public RangeFinder(int channel) {
		super(channel);
	}

	//returns the distance to the target in inches
	public double getDistance() {
		
		
		//blue triangle
		//return super.getAverageVoltage() * 113.545817;
		
		//black blank
		return super.getAverageVoltage() * 41.666666666;
	}
}
