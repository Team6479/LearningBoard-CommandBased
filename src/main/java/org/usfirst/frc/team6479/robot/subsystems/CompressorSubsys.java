package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CompressorSubsys extends Subsystem {

	Compressor compressor;
    
	public CompressorSubsys() {
		compressor = new Compressor(RobotMap.compressor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setCompressor(boolean toggle) {
    	//compressor.setClosedLoopControl(toggle);
    	if (toggle) {
    		compressor.start();
    	}
    	else {
    		compressor.stop();
    	}
    }
    
    public Compressor getCompressor() {
    	return compressor;
    }
}

