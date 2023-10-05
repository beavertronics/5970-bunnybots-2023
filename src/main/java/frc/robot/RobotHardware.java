// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The hardware of the robot is defined here, to be manipulated via control systems in RobotHardware.
 */
public class RobotHardware {


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private CANSparkMax motorL1;
  private CANSparkMax motorL2;
  private CANSparkMax motorR1;
  private CANSparkMax motorR2;


  private MotorControllerGroup motorsL;
  private MotorControllerGroup motorsR;

  public DifferentialDrive drive;

  /**
   * Makes the robot find all the motors and stuff.
   */
  public void initialize() {
    motorL1 = new CANSparkMax(24,MotorType.kBrushless);
    motorL2 = new CANSparkMax(25,MotorType.kBrushless);
    motorR1 = new CANSparkMax(21,MotorType.kBrushless);
    motorR2 = new CANSparkMax(22,MotorType.kBrushless);

    motorsL = new MotorControllerGroup(motorL1, motorL2);
    motorsR = new MotorControllerGroup(motorR1, motorR2);
    drivetrain = new DifferentialDrive(motorsL, motorsR);
  }
}
