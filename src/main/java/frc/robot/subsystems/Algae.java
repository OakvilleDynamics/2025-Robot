// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class Algae extends SubsystemBase {

  /**
   * Change Motor controllers depending on if we use Neo Vortexs or not Neo votrex - SparkFlex Other
   * motors - SparkMax Using sparkmaxes for every other motor because we are using the REV ecosystem
   */
  private final SparkMax AlgaeHinge =
      new SparkMax(MechanismConstants.ALGAE_HINGE, SparkLowLevel.MotorType.kBrushless);

  private final SparkFlex AlgaeRight =
      new SparkFlex(MechanismConstants.ALGAE_right, SparkLowLevel.MotorType.kBrushless);
  private final SparkFlex AlgaeLeft =
      new SparkFlex(MechanismConstants.ALGAE_left, SparkLowLevel.MotorType.kBrushless);

  private RelativeEncoder encoder = AlgaeHinge.getEncoder();
  private SparkLimitSwitch forwardLimitSwitch = AlgaeHinge.getForwardLimitSwitch();
  private SparkLimitSwitch reverseLimitSwitch = AlgaeHinge.getReverseLimitSwitch();
  private SparkMaxConfig AlgaeEncoderConfig;

  private DutyCycleEncoder shaftEncoder = new DutyCycleEncoder(MechanismConstants.ALGAE_Encoder);

  private String hingeActivity = "Initialized";

  /** Intakes Algae */
  public void intakeAlgae() {
    AlgaeRight.set(MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeLeft.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  /** Makes hinge go up */
  public void UpAlgae() {
    AlgaeHinge.set(MechanismConstants.ALGAE_HINGE_SPEED);
    hingeActivity = "UP";
  }

  /** Makes hinge go down */
  public void DownAlgae() {
    AlgaeHinge.set(-MechanismConstants.ALGAE_HINGE_SPEED);
    hingeActivity = "DOWN";
  }

  /** Sets the Algae motors to 0% power */
  public void disableAlgae() {
    AlgaeRight.set(0);
    AlgaeLeft.set(0);
  }

  // Stops Algae Hinge
  public void disablehinge() {
    AlgaeHinge.set(0);
    hingeActivity = "STOPPED";
  }

  /** Reverses Algae intake speed to score into processor */
  public void scoreAlgae() {
    AlgaeRight.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeLeft.set(MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  /** Releases Algae at higher power to score into the net */
  public void shootAlgae() {
    AlgaeRight.set(-MechanismConstants.ALGAE_SHOOT_FAST);
    AlgaeLeft.set(MechanismConstants.ALGAE_SHOOT_FAST);
  }

  // Automatically sets algae hinge to deafault position
  public void defaultPosition() {
    AlgaeHinge.getEncoder().setPosition(0);
  }

  // Automatically sets algae hinge to score in processor
  public void processorPosition() {
    AlgaeHinge.getEncoder().setPosition(90);
  }

  // Automatically set algae hinge to pickup algae
  public void pickupPosition() {
    AlgaeHinge.getEncoder().setPosition(0);
  }

  /**
   * Get encoder position from the internal motor controller. This won't be the same as the shaft
   * encoder position, as the motor controller has a gear ratio.
   */
  public double getInternalEncoderPosition() {
    return encoder.getPosition();
  }

  /**
   * This is the encoder position from the shaft encoder, connected to the DIO port on the RoboRIO,
   * this is running as a duty cycle encoder, or absolute encoder.
   */
  public double getShaftEncoderPosition() {
    return shaftEncoder.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Algae/Hinge Power", AlgaeHinge.getAppliedOutput());
    SmartDashboard.putNumber("Algae/Hinge Current", AlgaeHinge.getOutputCurrent());
    SmartDashboard.putString("Algae/Hinge Activity", hingeActivity);
  }
}
