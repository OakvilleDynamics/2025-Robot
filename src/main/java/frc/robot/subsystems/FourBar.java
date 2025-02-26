// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class FourBar extends SubsystemBase {
  private final SparkMax FourbarMotor =
      new SparkMax(MechanismConstants.FourbarMotor, SparkLowLevel.MotorType.kBrushless);

  private RelativeEncoder encoder = FourbarMotor.getEncoder();
  private SparkLimitSwitch forwardLimitSwitch = FourbarMotor.getForwardLimitSwitch();
  private SparkLimitSwitch reverseLimitSwitch = FourbarMotor.getReverseLimitSwitch();
  private SparkMaxConfig FourbarConfig;

  // FourbarConfig = new SparkMaxConfig();

  // FourbarConfig.encoder
  // .positionConversionFactor(1)
  // .velocityConversionFactor(1);
  // double targetPosition = SmartDashboard.getNumber("Target Position", 0);
  // closedLoopController.setReference(targetPosition, ControlType.kPosition,
  // ClosedLoopSlot.kSlot0);

  public void UpBar() {
    FourbarMotor.set(MechanismConstants.FourBarSpeed);
  }

  public void DownBar() {
    FourbarMotor.set(-MechanismConstants.FourBarSpeed);
  }

  public void disableFourBar() {
    FourbarMotor.set(0);
  }

  public void L1() {
    encoder.setPosition(0);
  }

  public void L2() {
    encoder.setPosition(0);
  }

  public void L3() {
    encoder.setPosition(0);
  }

  public void L4() {
    encoder.setPosition(0);
  }
}
