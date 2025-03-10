package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class CAM extends SubsystemBase {
  private final SparkMax CAMright =
      new SparkMax(MechanismConstants.CAMright, SparkLowLevel.MotorType.kBrushless);

  private final SparkMax CAMleft =
      new SparkMax(MechanismConstants.CAMleft, SparkLowLevel.MotorType.kBrushless);

  // Starts CAM motors to climb
  public void closeCAM() {
    CAMright.set(-MechanismConstants.CAMspeed);
    CAMleft.set(MechanismConstants.CAMspeed);
  }

  // Stops CAM motors
  public void stopCAM() {
    CAMright.set(0);
    CAMleft.set(0);
  }

  // We shouldn't need this one but it is there if needed :)
  /*
  public void openCAM() {
  CAMright.set(MechanismConstants.CAMspeed);
  CAMleft.set(-MechanismConstants.CAMspeed);
  }
  */
}
