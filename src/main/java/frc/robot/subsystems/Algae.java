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

  /** Intakes Algae */
  public void intakeAlgae() {
    AlgaeRight.set(MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeLeft.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  /** Makes hinge go up */
  public void UpAlgae() {
    AlgaeHinge.set(MechanismConstants.ALGAE_HINGE_SPEED);
  }

  /** Makes hinge go down */
  public void DownAlgae() {
    AlgaeHinge.set(-MechanismConstants.ALGAE_HINGE_SPEED);
  }

  /** Sets the Algae motors to 0% power */
  public void disableAlgae() {
    AlgaeRight.set(0);
    AlgaeLeft.set(0);
  }

  // Stops Algae Hinge
  public void disablehinge() {
    AlgaeHinge.set(0);
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
}
