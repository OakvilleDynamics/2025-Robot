package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Shooter;

public class ShooterCommand extends Command {

  private final Shooter m_ShooterSubsystem;

  private final Joystick coralJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  public ShooterCommand(Shooter subsystem) {
    m_ShooterSubsystem = subsystem;
    addRequirements(m_ShooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for Shooter Mech

  public void execute() {
    if (coralJoystick.getRawButton(3)) {
      m_ShooterSubsystem.intakeCoral();
      System.out.println("Intaking Coral");
    } else if (coralJoystick.getRawButton(4)) {
      m_ShooterSubsystem.shootCoral();
      System.out.println("Shooting Coral");
    } else {
      m_ShooterSubsystem.holdCoral();
    }
  }
}
