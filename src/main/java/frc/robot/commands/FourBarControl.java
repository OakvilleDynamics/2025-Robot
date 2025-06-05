package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.FourBar;

public class FourBarControl extends Command {

  private final FourBar m_FourBarSubsystem;

  private final Joystick BarJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  private String fourbarActivity = "Fourbar Disabled";

  public FourBarControl(FourBar subsystem) {
    m_FourBarSubsystem = subsystem;
    addRequirements(m_FourBarSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for the Fourbar
  public void execute() {

    // Set the speed of the fourbar to the joystick value, clamped to a set max speed
    // Check inversion of the joystick to ensure proper mechanism control
    m_FourBarSubsystem.setFourbarSpeedClamped(-BarJoystick.getY());

    // Check if the joystick is being moved, if it is, report that the fourbar is moving
    if (BarJoystick.getY() > 0.1 || BarJoystick.getY() < -0.1) {
      fourbarActivity = "Fourbar moving at " + BarJoystick.getY();
    } else {
      m_FourBarSubsystem.disableFourBar();
      fourbarActivity = "Fourbar Disabled";
    }
    SmartDashboard.putString("Fourbar Activity", fourbarActivity);
  }
}
