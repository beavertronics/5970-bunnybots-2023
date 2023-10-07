// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Hardware 
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.SPI;

//Subsystems and control
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The hardware of the robot is defined here, to be manipulated via control systems in RobotHardware.
 */
public class RobotHardware {
  public class Drive extends SubsystemBase {
    //========== MOTORS ==========//
    private CANSparkMax 
      motorL1 = new CANSparkMax(24, MotorType.kBrushless), 
      motorL2 = new CANSparkMax(25, MotorType.kBrushless), 
      motorR1 = new CANSparkMax(21, MotorType.kBrushless), 
      motorR2 = new CANSparkMax(22, MotorType.kBrushless);
    //TODO: Possibly refactor this with spark maxes .follow()ing a leader
    //Would mean less commands sent by the RIO but is vendor specific (only available on spark maxes)

    private MotorControllerGroup 
      motorsL = new MotorControllerGroup(motorL1, motorL2), 
      motorsR = new MotorControllerGroup(motorR1, motorR2);
    public DifferentialDrive drive = new DifferentialDrive(motorsL, motorsR);

    //========== Inertial Measurement Unit ==========//
    public AHRS imu = new AHRS(SPI.Port.kMXP); // Kauailabs NavX, used for orientation, velocity and acceleration data
  }
  //TODO: add flywheel and intake subsystems
  Drive driveSubsys = new Drive();
}
