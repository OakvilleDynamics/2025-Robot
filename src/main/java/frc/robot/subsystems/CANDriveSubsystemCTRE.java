// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import java.util.function.DoubleSupplier;

public class CANDriveSubsystemCTRE extends SubsystemBase {
  private final WPI_TalonSRX leftLeader;
  private final WPI_TalonSRX leftFollower;
  private final WPI_TalonSRX rightLeader;
  private final WPI_TalonSRX rightFollower;

  private final DifferentialDrive drive;

  public CANDriveSubsystemCTRE() {
    // create brushed motors for drive
    leftLeader = new WPI_TalonSRX(DriveConstants.LEFT_LEADER_ID);
    leftFollower = new WPI_TalonSRX(DriveConstants.LEFT_FOLLOWER_ID);
    rightLeader = new WPI_TalonSRX(DriveConstants.RIGHT_LEADER_ID);
    rightFollower = new WPI_TalonSRX(DriveConstants.RIGHT_FOLLOWER_ID);

    // set up differential drive class
    drive = new DifferentialDrive(leftLeader, rightLeader);

    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    leftLeader.setExpiration(250);
    rightLeader.setExpiration(250);
    leftFollower.setExpiration(250);
    rightFollower.setExpiration(250);

    // Create the configuration to apply to motors. Voltage compensation
    // helps the robot perform more similarly on different
    // battery voltages (at the cost of a little bit of top speed on a fully charged
    // battery). The current limit helps prevent tripping
    // breakers.
    // TalonSRXConfiguration config = new TalonSRXConfiguration();
    // config.voltageCompSaturation = 12;
    // config.continuousCurrentLimit = DriveConstants.DRIVE_MOTOR_CURRENT_LIMIT;

    // Set configuration to follow leader and then apply it to corresponding
    // follower. Resetting in case a new controller is swapped
    // in and persisting in case of a controller reset due to breaker trip
    // leftFollower.set(ControlMode.Follower, DriveConstants.LEFT_LEADER_ID);
    // leftFollower.configAllSettings(config);
    // rightFollower.set(ControlMode.Follower, DriveConstants.RIGHT_LEADER_ID);
    // rightFollower.configAllSettings(config);

    // rightLeader.configAllSettings(config);
    // Set conifg to inverted and then apply to left leader. Set Left side inverted
    // so that postive values drive both sides forward
    // leftLeader.setInverted(true);
    // leftLeader.configAllSettings(config);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    leftLeader.setInverted(false);
    leftFollower.setInverted(false);
    rightLeader.setInverted(true);
    rightFollower.setInverted(true);
  }

  @Override
  public void periodic() {}

  // Command to drive the robot with joystick inputs
  public Command driveArcade(
      CANDriveSubsystemCTRE driveSubsystem, DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    return Commands.run(
        () -> drive.arcadeDrive(xSpeed.getAsDouble(), zRotation.getAsDouble()), driveSubsystem);
  }

  public Command driveTank(
      CANDriveSubsystemCTRE driveSubsystem, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
    return Commands.run(
        () -> drive.tankDrive(leftSpeed.getAsDouble(), rightSpeed.getAsDouble()), driveSubsystem);
  }
}
