// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//TODO: possibly implement a power limit- FRC battery cannot support more than 1750 Watts
//Paper on the subject: https://www.chiefdelphi.com/uploads/default/original/3X/f/2/f2c7116c9b8c3dd1bb1f95250289778af1ca36dc.pdf
//Practical example: https://www.reddit.com/r/FRC/comments/62m9sq/limiting_current_draw_via_code/dfo79v1/?utm_source=share&utm_medium=web3x&utm_name=web3xcss&utm_term=1&utm_content=share_button

/**
 * This file links together all the code to Control the robot.
 */

public class RobotController extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotHardware bot = new RobotHardware();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private CommandXboxController controllerOperator = new CommandXboxController(Constants.Station.operatorControllerPort);
  private CommandJoystick 
    joyDriverL = new CommandJoystick(Constants.Station.driverJoystickPortL),
    joyDriverR = new CommandJoystick(Constants.Station.driverJoystickPortR);


  private CommandBase teleopDrive = Commands.run(()->{
    bot.driveSubsys.drive.tankDrive(joyDriverL.getY(),joyDriverR.getY());
  }, bot.driveSubsys);
  private CommandBase teleopDriveTurbo = Commands.run(()->{
    bot.driveSubsys.drive.tankDrive(joyDriverL.getY(),joyDriverR.getY());
  }, bot.driveSubsys);
  

  //Robot Initialization- do things need to reset?
  @Override
  public void robotInit() {
    Shuffleboard.getTab("Robot").getLayout("Drivetrain",BuiltInLayouts.kList).add(bot.driveSubsys.drive);
    Shuffleboard.getTab("Robot").getLayout("Drivetrain",BuiltInLayouts.kList).add(bot.driveSubsys.imu);
  }
  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  @Override
  public void robotPeriodic() {  //This function runs every 20ms, no matter what.
    // THE ALMIGHTY SCHEDULER- it runs commands
    CommandScheduler.getInstance().run();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //TODO: Get autonomous command!

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void teleopInit() {
    // stop autonomous when teleop starts!!
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    bot.driveSubsys.setDefaultCommand(teleopDrive);
    joyDriverL.trigger().whileTrue(teleopDriveTurbo);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
