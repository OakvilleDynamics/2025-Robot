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

  private final SparkFlex AlgaeIntake1 =
      new SparkFlex(MechanismConstants.ALGAE_INTAKE_1, SparkLowLevel.MotorType.kBrushless);
  private final SparkFlex AlgaeIntake2 =
      new SparkFlex(MechanismConstants.ALGAE_INTAKE_2, SparkLowLevel.MotorType.kBrushless);

  private RelativeEncoder encoder = AlgaeHinge.getEncoder();
  private SparkLimitSwitch forwardLimitSwitch = AlgaeHinge.getForwardLimitSwitch();
  private SparkLimitSwitch reverseLimitSwitch = AlgaeHinge.getReverseLimitSwitch();
  private SparkMaxConfig AlgaeEncoderConfig;

  /** Intakes Algae */
  public void intakeAlgae() {
    AlgaeIntake1.set(MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeIntake2.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
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
    AlgaeIntake1.set(0);
    AlgaeIntake2.set(0);
  }

  // Stops Algae Hinge
  public void disablehinge() {
    AlgaeHinge.set(0);
  }

  /** Reverses Algae intake speed to score into processor */
  public void scoreAlgae() {
    AlgaeIntake1.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeIntake2.set(MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  /** Releases Algae at higher power to score into the net */
  public void shootAlgae() {
    AlgaeIntake1.set(-MechanismConstants.ALGAE_SHOOT_FAST);
    AlgaeIntake2.set(MechanismConstants.ALGAE_SHOOT_FAST);
  }

  public void defaultPosition() {
    AlgaeHinge.getEncoder().setPosition(0);
  }

  public void processorPosition() {
    AlgaeHinge.getEncoder().setPosition(90);
  }

  public void pickupPosition() {
    AlgaeHinge.getEncoder().setPosition(0);
  }
}
