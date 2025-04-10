// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class Shooter extends SubsystemBase {

  private final SparkMax ShooterMotor =
      new SparkMax(MechanismConstants.ShooterMotor, SparkLowLevel.MotorType.kBrushless);

  // Intake Coral
  public void intakeCoral() {
    ShooterMotor.set(-MechanismConstants.ShooterSpeed);
  }

  // Shoots Coral
  public void shootCoral() {
    ShooterMotor.set(MechanismConstants.ShooterSpeed);
  }

  public Command holdCoral() {
    return Commands.runOnce(() -> ShooterMotor.set(-MechanismConstants.SlowShooter));
  }

  public Command AutoShoot() {
    return Commands.runOnce(() -> ShooterMotor.set(MechanismConstants.ShooterSpeed));
  }

  public Command AutoStop() {
    return Commands.runOnce(() -> ShooterMotor.set(0));
  }

  // Disables motor
  public void disableShooter() {
    ShooterMotor.set(0);
  }
}
