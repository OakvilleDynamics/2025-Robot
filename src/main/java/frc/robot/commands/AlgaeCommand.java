package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Algae;

public class AlgaeCommand extends Command {
  private final Algae m_AlgaeSubsystem;

  private final Joystick algaeJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  public AlgaeCommand(Algae subsystem) {
    m_AlgaeSubsystem = subsystem;
    addRequirements(m_AlgaeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (algaeJoystick.getRawButton(5)) {
      m_AlgaeSubsystem.intakeAlgea();
      System.out.println("Algea taking in");
    } else if (algaeJoystick.getRawButton(4)) {
      m_AlgaeSubsystem.shootAlgea();
      System.out.println("Shooting Algea");
    } else if (algaeJoystick.getRawButton(7)) {
      m_AlgaeSubsystem.UpAlgea();
      System.out.println("Algea going up");
    } else if (algaeJoystick.getRawButton(8)) {
      m_AlgaeSubsystem.DownAlgea();
      System.out.println("Algea going down");
    } else if (algaeJoystick.getRawButton(6)) {
      m_AlgaeSubsystem.scoreAlgea();
      System.out.println("Scoring Algea");
    } else {
      m_AlgaeSubsystem.disableAlgea();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_AlgaeSubsystem.disableAlgea();
    m_AlgaeSubsystem.intakeAlgea();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
