package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.RobotMap;
import org.usfirst.frc.team6479.robot.commands.Piston1;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	
	private Compressor compressor;
	private DoubleSolenoid dubsolenoid;
	
	public Pneumatics() {
		compressor = new Compressor(RobotMap.compressor);
		dubsolenoid = new DoubleSolenoid(RobotMap.onSolenoid, RobotMap.offSolenoid);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new  Piston1());
    }
    
    public void setCompressor(boolean toggle) {
    	compressor.setClosedLoopControl(toggle);
    }
    
    public void setDoubleSolenoid(Value val) {
    	dubsolenoid.set(val);
    }
    
    public Compressor getCompressor() {
    	return compressor;
    }
    
    public DoubleSolenoid getDoubleSolenoid() {
    	return dubsolenoid;
    }
}

