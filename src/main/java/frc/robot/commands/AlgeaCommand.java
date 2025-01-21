package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Algea;

public class AlgeaCommand extends Command {
  private final Algea m_AlgeaSubsystem;

  private final Joystick algeaJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  public AlgeaCommand(Algea subsystem) {
    m_AlgeaSubsystem = subsystem;
    addRequirements(m_AlgeaSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (algeaJoystick.getRawButton(6)) {
      m_AlgeaSubsystem.intakeAlgea();
      System.out.println("Algea taking in");
    } else if (algeaJoystick.getRawButton(5)) {
      m_AlgeaSubsystem.reverseAlgea();
      System.out.println("Algea spitting out");
    } else if (algeaJoystick.getRawButton(4) || algeaJoystick.getRawButton(8)) {
      m_AlgeaSubsystem.UpAlgea();
      System.out.println("Algea going up");
    } else if (algeaJoystick.getRawButton(3)) {
      m_AlgeaSubsystem.DownAlgea();
      System.out.println("Algea going down");
    }
    else {
      m_AlgeaSubsystem.disableAlgea();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_AlgeaSubsystem.disableAlgea();
    m_AlgeaSubsystem.intakeAlgea();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
