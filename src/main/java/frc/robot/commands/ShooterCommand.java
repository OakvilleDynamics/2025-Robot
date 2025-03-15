package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Shooter;

public class ShooterCommand extends Command {

  private final Shooter m_ShooterSubsystem;

  private final Joystick coralJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  private String shooterActivity = "Shooter Disabled";

  public ShooterCommand(Shooter subsystem) {
    m_ShooterSubsystem = subsystem;
    addRequirements(m_ShooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for Shooter Mech

  public void execute() {
    if (coralJoystick.getPOV() == 180) {
      m_ShooterSubsystem.intakeCoral();
      shooterActivity = "Intaking Coral";
    } else if (coralJoystick.getPOV() == 0) {
      m_ShooterSubsystem.shootCoral();
      shooterActivity = "Shooting Coral";
    } else {
      m_ShooterSubsystem.disableShooter();
    }
    SmartDashboard.putString("Shooter", shooterActivity);
  }
}
