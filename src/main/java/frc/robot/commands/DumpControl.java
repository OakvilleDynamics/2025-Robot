package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Dump;

public class DumpControl extends Command {

  private final Dump m_DumpSubsystem;

  private final Joystick DumpJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  private String fourbarActivity = "Dump Disabled";

  public DumpControl(Dump subsystem) {
    m_DumpSubsystem = subsystem;
    addRequirements(m_DumpSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for the Fourbar
  public void execute() {

    // Set the speed of the fourbar to the joystick value, clamped to a set max speed
    // Check inversion of the joystick to ensure proper mechanism control
    m_DumpSubsystem.setFourbarSpeedClamped(-DumpJoystick.getY());

    // Check if the joystick is being moved, if it is, report that the fourbar is moving
    if (DumpJoystick.getRawButton(3)) {
      m_DumpSubsystem.DumpCoral();
    } else if (DumpJoystick.getRawButton(5)) {
      m_DumpSubsystem.Back();
    } else {
      m_DumpSubsystem.disableFourBar();
    }
    SmartDashboard.putString("Fourbar Activity", fourbarActivity);
  }
}
