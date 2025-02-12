// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class FourBar extends SubsystemBase {
  private final SparkMax FourbarMotor =
      new SparkMax(MechanismConstants.FourbarMotor, SparkLowLevel.MotorType.kBrushless);

  private RelativeEncoder encoder = FourbarMotor.getEncoder();
  private SparkLimitSwitch forwardLimitSwitch = FourbarMotor.getForwardLimitSwitch();
  private SparkLimitSwitch reverseLimitSwitch = FourbarMotor.getReverseLimitSwitch();

  public void UpBar() {
    FourbarMotor.set(-MechanismConstants.FourBarSpeed);
  }

  public void DownBar() {
    FourbarMotor.set(MechanismConstants.FourBarSpeed);
  }

  public void StopFourBar() {
    FourbarMotor.set(0);
  }
}
