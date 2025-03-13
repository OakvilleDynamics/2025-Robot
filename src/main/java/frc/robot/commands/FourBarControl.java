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
    // SmartDashboard.putNumber("Shaft Encoder", m_FourBarSubsystem.getShaftEncoderPosition());
    // SmartDashboard.putNumber("Internal Encoder",
    // m_FourBarSubsystem.getInternalEncoderPosition());
    if (BarJoystick.getRawButton(1)) {
      m_FourBarSubsystem.UpBar();
      System.out.println("Fourbar Up");
    } else if (BarJoystick.getRawButton(2)) {
      m_FourBarSubsystem.DownBar();
      System.out.println("Fourbar Down");
    } else if (BarJoystick.getRawButton(7)) {
      m_FourBarSubsystem.L2();
      System.out.println("Going to L2");
    } else if (BarJoystick.getRawButton(8)) {
      m_FourBarSubsystem.L3();
      System.out.println("Going to L3");
      // chagne to 9 maybe in the future
    } else if (BarJoystick.getRawButton(7)) {
      m_FourBarSubsystem.L4();
      System.out.println("Going to L4");
    } else {
      m_FourBarSubsystem.disableFourBar();
    }
  }
}
