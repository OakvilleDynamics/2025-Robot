// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class Algae extends SubsystemBase {
  private final SparkFlex AlgaeHinge =
      new SparkFlex(MechanismConstants.ALGAE_HINGE, SparkLowLevel.MotorType.kBrushless);
  private final SparkFlex AlgaeIntake1 =
      new SparkFlex(MechanismConstants.ALGAE_INTAKE_1, SparkLowLevel.MotorType.kBrushless);
  private final SparkFlex AlgaeIntake2 =
      new SparkFlex(MechanismConstants.ALGAE_INTAKE_2, SparkLowLevel.MotorType.kBrushless);

  /* public Algea() {
    AlgeaHinge.setInverted(MechanismConstants.ALGEA_HINGE_INVERTED);
    AlgeaIntake1.setInverted(MechanismConstants.ALGEA_INTAKE_1_INVERTED);
    AlgeaIntake2.setInverted(MechanismConstants.ALGEA_INTAKE_2_INVERTED);
  } */

  public void intakeAlgea() {
    AlgaeIntake1.set(MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeIntake2.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  public void UpAlgea() {
    AlgaeHinge.set(MechanismConstants.ALGAE_HINGE_SPEED);
  }

  public void DownAlgea() {
    AlgaeHinge.set(-MechanismConstants.ALGAE_HINGE_SPEED);
  }

  /** Sets the Algea motors to 0% power */
  public void disableAlgea() {
    AlgaeIntake1.set(0);
    AlgaeIntake2.set(0);
  }

  /** Reverses Algea Motors */
  public void scoreAlgea() {
    AlgaeIntake1.set(-MechanismConstants.ALGAE_INTAKE_SPEED);
    AlgaeIntake2.set(MechanismConstants.ALGAE_INTAKE_SPEED);
  }

  public void shootAlgea() {
    AlgaeIntake1.set(-MechanismConstants.ALGAE_SHOOT_FAST);
    AlgaeIntake2.set(MechanismConstants.ALGAE_SHOOT_FAST);
  }
}
