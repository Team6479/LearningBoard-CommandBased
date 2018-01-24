
package org.usfirst.frc.team6479.robot;

import org.usfirst.frc.team6479.robot.connection.JetsonServer;
import org.usfirst.frc.team6479.robot.custom.ButtonTracker;
import org.usfirst.frc.team6479.robot.subsystems.Motors;
import org.usfirst.frc.team6479.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import communication.JetsonPacket.ModePacket;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//Subsystems
	public static Motors motors;
	public static Pneumatics pneumatics;
	
	public static OI oi;
	
	public static JetsonServer server;
	


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Init Subsystems
		motors = new Motors();
		pneumatics = new Pneumatics();
		
		oi = new OI();
		
		server = new JetsonServer(1182);
		server.setMode(ModePacket.Mode.CUBE);
	}

	@Override
	public void robotPeriodic() {
		//run commands
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	@Override
	public void teleopInit() {
		
		oi.compressor.setClosedLoopControl(false);
		
	}
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		System.out.println("Current distance from the center" + server.getDistance());
		
		if(oi.leftBumper.wasJustPressed())
		{	
			if(oi.compressor.enabled())
			{
	 			oi.compressor.stop();
			}
			else
			{
				oi.compressor.start();
			}
		}
		
		ButtonTracker.updateAll();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}
