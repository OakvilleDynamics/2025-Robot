package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.CAM;

public class CAMcommand extends Command {

  private final CAM m_CAMSubsystem;

  private final Joystick CAMJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  public CAMcommand(CAM subsystem) {
    m_CAMSubsystem = subsystem;
    addRequirements(m_CAMSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for Shooter Mech

  public void execute() {
    if (CAMJoystick.getThrottle() >= 0.75) {
      System.out.println("Closing CAM");
      m_CAMSubsystem.closeCAM();
      new WaitCommand(5.0);
      m_CAMSubsystem.stopCAM();
    } else {
      m_CAMSubsystem.stopCAM();
    }
  }
}
