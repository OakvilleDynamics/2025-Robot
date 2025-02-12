package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.FourBar;

public class FourBarControl extends Command {

  private final FourBar m_FourBarSubsystem;

  private final Joystick BarJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  public FourBarControl(FourBar subsystem) {
    m_FourBarSubsystem = subsystem;
    addRequirements(m_FourBarSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for the Algae

  public void execute() {
    if (BarJoystick.getRawButton(8)) {
      m_FourBarSubsystem.UpBar();
      System.out.println("Four Bar Going Up");
    } else if (BarJoystick.getRawButton(9)) {
      m_FourBarSubsystem.DownBar();
      System.out.println("Four Bar going down");
    } else {
      m_FourBarSubsystem.StopFourBar();
    }
  }
}
