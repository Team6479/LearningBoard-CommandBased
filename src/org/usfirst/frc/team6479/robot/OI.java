package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XboxController xbox;
	
	public OI() {
		xbox = new XboxController(RobotMap.xbox);
	}
}
