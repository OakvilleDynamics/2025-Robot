package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.CAM;

public class CAMcommand extends Command {

  private final CAM m_CAMSubsystem;

  private final Joystick CAMJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  private boolean isClosing = false;

  public CAMcommand(CAM subsystem) {
    m_CAMSubsystem = subsystem;
    addRequirements(m_CAMSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for Shooter Mech

  public void execute() {
    if (CAMJoystick.getRawButton(7)) {
      m_CAMSubsystem.closeCAM();
      isClosing = true;
    } else {
      m_CAMSubsystem.stopCAM();
    }
    SmartDashboard.putBoolean("CAM is closing", isClosing);
  }
}
