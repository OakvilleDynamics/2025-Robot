// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class Algae extends SubsystemBase {

  /**
   * Change Motor controllers depending on if we use Neo Vortexs or not Neo votrex - SparkFlex Other
   * motors - SparkMax Using sparkmaxes for every other motor because we are using the REV ecosystem
   */
  private final SparkFlex AlgaeHinge =
      new SparkFlex(MechanismConstants.ALGAE_HINGE, SparkLowLevel.MotorType.kBrushless);

  private final SparkMax AlgaeIntake1 =
      new SparkMax(MechanismConstants.ALGAE_INTAKE_1, SparkLowLevel.MotorType.kBrushless);
  private final SparkMax AlgaeIntake2 =
      new SparkMax(MechanismConstants.ALGAE_INTAKE_2, SparkLowLevel.MotorType.kBrushless);

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
}
