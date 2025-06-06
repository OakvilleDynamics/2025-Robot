// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.FourbarConstants;
import frc.robot.Constants.MechanismConstants;

public class FourBar extends SubsystemBase {
  private final SparkMax FourbarMotor =
      new SparkMax(MechanismConstants.FourbarMotor, SparkLowLevel.MotorType.kBrushless);

  private SparkClosedLoopController fourbar;

  private RelativeEncoder shaftEncoder = FourbarMotor.getEncoder();

  private SparkClosedLoopController p_fourbar;

  private RelativeEncoder e_fourbar;

  private SparkAbsoluteEncoder e_shaft;

  private SparkMaxConfig c_fourbar;

  private double m_setpoint;

  public FourBar() {

    c_fourbar = Configs.FourbarConfig.FourbarConfig;

    // FourbarMotor.configure(
    //    c_fourbar, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    m_setpoint = FourbarConstants.kStartingPosition;

    p_fourbar = FourbarMotor.getClosedLoopController();

    e_fourbar = FourbarMotor.getEncoder();

    e_shaft = FourbarMotor.getAbsoluteEncoder();

    e_fourbar.setPosition(0);
  }

  /**
   * Checks if the fourbar is at the target position.
   *
   * @return True if the fourbar is at the target position, false otherwise.
   */
  public boolean atTargetPosition() {
    return Math.abs(avgEncoderPos() - m_setpoint) < FourbarConstants.kPositionTolerance;
  }

  /**
   * Gets the current position of the fourbar.
   *
   * @return The current position of the fourbar.
   */
  public double avgEncoderPos() {
    return (e_fourbar.getPosition());
  }

  /**
   * Sets the target position for the fourbar.
   *
   * @param setpoint The desired position to set the fourbar to.
   */
  public void setTargetPosition(double setpoint) {
    m_setpoint = setpoint;
    moveToSetpoint();
  }

  /**
   * Moves the fourbar to the setpoint position using the closed-loop controller. This method uses
   * the MAX Motion Position Control to move the fourbar to the desired position. It is called after
   * setting the target position with {@link #setTargetPosition(double)}.
   */
  private void moveToSetpoint() {
    p_fourbar.setReference(m_setpoint, ControlType.kMAXMotionPositionControl);
  }

  /**
   * Moves the fourbar up at a constant speed. This method is used to manually control the fourbar's
   * movement.
   */
  public void UpBar() {
    FourbarMotor.set(MechanismConstants.FourBarSpeed);
  }

  /**
   * Moves the fourbar down at a constant speed. This method is used to manually control the
   * fourbar's movement.
   */
  public void DownBar() {
    FourbarMotor.set(-MechanismConstants.FourBarSpeed);
  }

  /**
   * Disables the fourbar motor by setting its speed to zero. This method is used to stop the
   * fourbar's movement manually.
   */
  public void disableFourBar() {
    FourbarMotor.set(0);
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

  public void set(double speed) {
    FourbarMotor.set(speed);
  }

  /**
   * Set the speed of the fourbar with a deadband.
   *
   * @param speed Speed to set the fourbar motor to
   */
  public void setFourbarSpeedWithDeadband(double speed) {
    FourbarMotor.set(MathUtil.applyDeadband(speed, 0.025, MechanismConstants.FourBarSpeed));
  }

  @Override
  public void periodic() { // This method will be called once per scheduler run
    SmartDashboard.putNumber("Fourbar/Motor Speed", FourbarMotor.get());
    SmartDashboard.putNumber("Fourbar/Motor Position", e_fourbar.getPosition());
    SmartDashboard.putNumber("Fourbar/Motor Velocity", e_fourbar.getVelocity());
    SmartDashboard.putNumber("Fourbar/Setpoint", m_setpoint);
    SmartDashboard.putNumber("Fourbar/Shaft Position", shaftEncoder.getPosition());
    SmartDashboard.putNumber("Fourbar/Shaft Position 2", e_shaft.getPosition());
    SmartDashboard.putNumber("Fourbar/Shaft Velocity 2", e_shaft.getVelocity());
    SmartDashboard.putBoolean("Fourbar/At Target", atTargetPosition());
  }
}
