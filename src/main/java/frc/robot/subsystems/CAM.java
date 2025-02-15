package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class CAM extends SubsystemBase {
  private final SparkMax CAMmotor1 =
      new SparkMax(MechanismConstants.CAMmotor1, SparkLowLevel.MotorType.kBrushless);

  private final SparkMax CAMmotor2 =
      new SparkMax(MechanismConstants.CAMmotor2, SparkLowLevel.MotorType.kBrushless);

  public void closeCAM() {
    CAMmotor1.set(MechanismConstants.CAMspeed);
    CAMmotor2.set(-MechanismConstants.CAMspeed);
  }

  public void stopCAM() {
    CAMmotor1.set(0);
    CAMmotor2.set(0);
  }

  // We shouldn't need this one but it is there if needed :)
  public void openCAM() {
    CAMmotor1.set(-MechanismConstants.CAMspeed);
    CAMmotor2.set(MechanismConstants.CAMspeed);
  }
}
