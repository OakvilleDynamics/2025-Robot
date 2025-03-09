// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class FourBar extends SubsystemBase {
  private final SparkMax FourbarMotor =
      new SparkMax(MechanismConstants.FourbarMotor, SparkLowLevel.MotorType.kBrushless);

  private RelativeEncoder encoder = FourbarMotor.getEncoder();
  private SparkLimitSwitch forwardLimitSwitch = FourbarMotor.getForwardLimitSwitch();
  private SparkLimitSwitch reverseLimitSwitch = FourbarMotor.getReverseLimitSwitch();
  private SparkMaxConfig FourbarEncoderConfig;

  private DutyCycleEncoder shaftEncoder = new DutyCycleEncoder(MechanismConstants.FourbarEncoder);

  // FourbarEncoderConfig.encoder
  // .positionConversionFactor(1);
  // .velocityConversionFactor(1);

  // Makes Fourbar go up manually
  public void UpBar() {
    FourbarMotor.set(MechanismConstants.FourBarSpeed);
  }

  // Makes Fourbar go down manually
  public void DownBar() {
    FourbarMotor.set(-MechanismConstants.FourBarSpeed);
  }

  // Stops Fourbar motors
  public void disableFourBar() {
    FourbarMotor.set(0);
  }

  // Automatically sets fourbar to deafault position to score L1
  public void L1() {
    encoder.setPosition(0);
  }

  // Automatically sets fourbar to score L2
  public void L2() {
    encoder.setPosition(0);
  }

  // Automatically sets fourbar to score L3
  public void L3() {
    encoder.setPosition(0);
  }

  // Automatically set fourbar to score L4
  public void L4() {
    encoder.setPosition(0);
  }

  /**
   * Get encoder position from the internal motor controller. This won't be the same as the shaft
   * encoder position, as the motor controller has a gear ratio.
   *
   * @return encoder position from the motor
   */
  public double getInternalEncoderPosition() {
    return encoder.getPosition();
  }

  /**
   * This is the encoder position from the shaft encoder, connected to the DIO port on the RoboRIO,
   * this is running as a duty cycle encoder, or absolute encoder.
   *
   * @return encoder position from the shaft
   */
  public double getShaftEncoderPosition() {
    return shaftEncoder.get();
  }
}
