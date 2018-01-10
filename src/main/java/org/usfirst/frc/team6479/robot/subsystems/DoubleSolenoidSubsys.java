package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DoubleSolenoidSubsys extends Subsystem {
	private DoubleSolenoid dubsolenoid;
	
	public DoubleSolenoidSubsys() {
		dubsolenoid = new DoubleSolenoid(RobotMap.onSolenoid, RobotMap.offSolenoid);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setDoubleSolenoid(Value val) {
    	dubsolenoid.set(val);
    }
    
    public DoubleSolenoid getDoubleSolenoid() {
    	return dubsolenoid;
    }
}

