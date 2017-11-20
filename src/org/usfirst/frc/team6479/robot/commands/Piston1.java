package org.usfirst.frc.team6479.robot.commands;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Piston1 extends Command {

    public Piston1() {
    	requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(Robot.oi.xbox.getRawAxis(2)) > 0) {
    		Robot.pneumatics.setCompressor(true);
    		System.out.println(Robot.pneumatics.getCompressor().getClosedLoopControl());
    	}
    	if (Math.abs(Robot.oi.xbox.getRawAxis(3)) > 0) {
    		Robot.pneumatics.setCompressor(false);
    		System.out.println(Robot.pneumatics.getCompressor().getClosedLoopControl());
    	}
    	if (Robot.oi.xbox.getRawButton(5)) {
    		Robot.pneumatics.setDoubleSolenoid(Value.kForward);
    	}
    	if (Robot.oi.xbox.getRawButton(6)) {
    		Robot.pneumatics.setDoubleSolenoid(Value.kReverse);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
