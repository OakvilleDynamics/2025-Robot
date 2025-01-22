// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class Algea extends SubsystemBase {
  private final SparkMax AlgeaHinge =
      new SparkMax(MechanismConstants.ALGEA_HINGE, SparkLowLevel.MotorType.kBrushless);
  private final SparkMax AlgeaIntake1 =
      new SparkMax(MechanismConstants.ALGEA_INTAKE_1, SparkLowLevel.MotorType.kBrushless);
  private final SparkMax AlgeaIntake2 =
      new SparkMax(MechanismConstants.ALGEA_INTAKE_2, SparkLowLevel.MotorType.kBrushless);

  public Algea() {
    AlgeaHinge.setInverted(MechanismConstants.ALGEA_HINGE_INVERTED);
    AlgeaIntake1.setInverted(MechanismConstants.ALGEA_INTAKE_1_INVERTED);
    AlgeaIntake2.setInverted(MechanismConstants.ALGEA_INTAKE_2_INVERTED);
  }

  public void intakeAlgea() {
    AlgeaIntake1.set(MechanismConstants.ALGEA_INTAKE_1_SPEED);
    AlgeaIntake2.set(MechanismConstants.ALGEA_INTAKE_2_SPEED);
  }

  public void UpAlgea() {
    AlgeaHinge.set(MechanismConstants.ALGEA_HINGE_SPEED);
  }

  public void DownAlgea() {
    AlgeaHinge.set(-MechanismConstants.ALGEA_HINGE_SPEED);
  }

  /** Sets the Algea motors to 0% power */
  public void disableAlgea() {
    AlgeaIntake1.set(0);
    AlgeaIntake2.set(0);
  }

  /** Reverses Algea Motors */
  public void shootAlgea() {
    AlgeaIntake1.set(-MechanismConstants.ALGEA_INTAKE_1_SPEED);
    AlgeaIntake2.set(-MechanismConstants.ALGEA_INTAKE_2_SPEED);
  }
}
