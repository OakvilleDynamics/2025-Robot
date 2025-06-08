// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeConstants;
import frc.robot.Constants.FourbarConstants;
import frc.robot.Constants.MechanismConstants;

public class Algae extends SubsystemBase {

  private final SparkMax algaeMotorLeft =
      new SparkMax(MechanismConstants.ALGAE_left, SparkLowLevel.MotorType.kBrushless);
  private final SparkMax algaeMotorRight =
      new SparkMax(MechanismConstants.ALGAE_right, SparkLowLevel.MotorType.kBrushless);
  private final SparkFlex algaeHinge =
      new SparkFlex(MechanismConstants.ALGAE_HINGE, SparkLowLevel.MotorType.kBrushless);
  private final RelativeEncoder hingeEncoder = algaeHinge.getEncoder();
  private final SparkClosedLoopController hingeClosedLoopController =
      algaeHinge.getClosedLoopController();
  private final SparkMaxConfig hingeConfig = new SparkMaxConfig();

  private double setpoint = 0;

  public Algae() {

    // Configure the algae hinge encoder
    hingeConfig.encoder.positionConversionFactor(1).velocityConversionFactor(1);

    // Configure the algae hinge motor
    hingeConfig
        .closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        // Set PID values for position control. We don't need to pass a closed loop
        // slot, as it will default to slot 0.
        .p(0.1)
        .i(0)
        .d(0)
        .outputRange(-AlgaeConstants.kHingeSpeed, AlgaeConstants.kHingeSpeed)
        // Set PID values for velocity control in slot 1
        .p(0.0001, ClosedLoopSlot.kSlot1)
        .i(0, ClosedLoopSlot.kSlot1)
        .d(0, ClosedLoopSlot.kSlot1)
        .velocityFF(1.0 / 5767, ClosedLoopSlot.kSlot1)
        .outputRange(-1, 1, ClosedLoopSlot.kSlot1);

    // Apply the hinge configuration to the algae hinge motor
    algaeHinge.configure(
        hingeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  /**
   * Checks if the algae hinge is at the target position.
   *
   * @return True if the algae hinge is at the target position, false otherwise.
   */
  public boolean atTargetPosition() {
    return Math.abs(avgEncoderPos() - setpoint) < FourbarConstants.kPositionTolerance;
  }

  /**
   * Gets the current position of the algae hinge.
   *
   * @return The current position of the algae hinge.
   */
  public double avgEncoderPos() {
    return (hingeEncoder.getPosition());
  }

  /**
   * Sets the algae hinge to a specific position.
   *
   * @param position The target position for the algae hinge.
   */
  public void setHingePosition(double position) {
    setpoint = position;
    hingeClosedLoopController.setReference(
        position, ControlType.kMAXMotionPositionControl, ClosedLoopSlot.kSlot0);
  }

  /** Shoots the algae. */
  public void shootAlgae() {
    algaeMotorLeft.set(MechanismConstants.ALGAE_SHOOT_FAST);
    algaeMotorRight.set(MechanismConstants.ALGAE_SHOOT_FAST);
  }

  /** Stops the algae motors. */
  public void stopAlgae() {
    algaeMotorLeft.set(0);
    algaeMotorRight.set(0);
  }

  /** Intakes algae by setting the intake motors to a specific speed. */
  public void intakeAlgae() {
    algaeMotorLeft.set(MechanismConstants.ALGAE_INTAKE_SPEED);
    algaeMotorRight.set(MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  @Override
  public void periodic() {
    // Output the hinge position and velocity to SmartDashboard
    SmartDashboard.putNumber("Algae/Hinge Position", hingeEncoder.getPosition());
    SmartDashboard.putNumber("Algae/Hinge Velocity", hingeEncoder.getVelocity());
    SmartDashboard.putNumber("Algae/Hinge Setpoint", setpoint);
    SmartDashboard.putBoolean("Algae/Hinge at Target", atTargetPosition());
    SmartDashboard.putNumber("Algae/Hinge Motor Output", algaeHinge.getAppliedOutput());

    // Output the algae motor states to SmartDashboard
    SmartDashboard.putNumber("Algae/Left Motor Output", algaeMotorLeft.getAppliedOutput());
    SmartDashboard.putNumber("Algae/Right Motor Output", algaeMotorRight.getAppliedOutput());
    SmartDashboard.putNumber(
        "Algae/Left Motor Velocity", algaeMotorLeft.getEncoder().getVelocity());
    SmartDashboard.putNumber(
        "Algae/Right Motor Velocity", algaeMotorRight.getEncoder().getVelocity());
  }
}
