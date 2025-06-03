// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.FourbarConstants;
import frc.robot.Constants.MechanismConstants;

public class FourBar extends SubsystemBase {
  private final SparkMax FourbarMotor =
      new SparkMax(MechanismConstants.FourbarMotor, SparkLowLevel.MotorType.kBrushless);

  private SparkClosedLoopController fourbar;

  private DutyCycleEncoder shaftEncoder = new DutyCycleEncoder(MechanismConstants.FourbarEncoder);

  private SparkClosedLoopController p_fourbar;

  private RelativeEncoder e_fourbar;

  private SparkMaxConfig c_fourbar;

  private double m_setpoint;

  public FourBar() {

    c_fourbar = Configs.FourbarConfig.FourbarConfig;

    FourbarMotor.configure(
        c_fourbar, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    m_setpoint = FourbarConstants.kStartingPosition;

    p_fourbar = FourbarMotor.getClosedLoopController();

    e_fourbar = FourbarMotor.getEncoder();

    e_fourbar.setPosition(shaftEncoder.get());
  }

  public void L1() {
    setTargetPosition(FourbarConstants.L1);
  }

  public void L2() {
    setTargetPosition(FourbarConstants.L2);
  }

  public void L3() {
    setTargetPosition(FourbarConstants.L3);
  }

  public void L4() {
    setTargetPosition(FourbarConstants.L4);
  }

  public void disableFourBar() {
    FourbarMotor.set(0);
  }

  public boolean atTargetPosition() {
    return Math.abs(avgEncoderPos() - m_setpoint) < FourbarConstants.kPositionTolerance;
  }

  public double avgEncoderPos() {
    return (e_fourbar.getPosition());
  }

  public void setTargetPosition(double setpoint) {
    m_setpoint = setpoint;
  }

  private void moveToSetpoint() {
    p_fourbar.setReference(m_setpoint, ControlType.kMAXMotionPositionControl);
  }

  /**
   * Set the speed of the fourbar motor, clamped to the maximum speed.
   *
   * @param speed Clamped speed to set the fourbar motor to
   */
  public void setFourbarSpeedClamped(double speed) {
    FourbarMotor.set(
        MathUtil.clamp(speed, -MechanismConstants.FourBarSpeed, MechanismConstants.FourBarSpeed));
  }

  @Override
  public void periodic() { // This method will be called once per scheduler run
    SmartDashboard.putNumber("Fourbar/Motor Speed", FourbarMotor.get());
    SmartDashboard.putNumber("Fourbar/Motor Position", e_fourbar.getPosition());
    SmartDashboard.putNumber("Fourbar/Motor Velocity", e_fourbar.getVelocity());
    SmartDashboard.putNumber("Fourbar/Setpoint", m_setpoint);
    SmartDashboard.putNumber("Fourbar/Shaft Position", shaftEncoder.get());
    SmartDashboard.putBoolean("Fourbar/At Target", atTargetPosition());
    moveToSetpoint();
  }
}
