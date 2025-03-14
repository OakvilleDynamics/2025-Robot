package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  // Controls for the Algae

  public void execute() {
    SmartDashboard.putNumber("Shaft Encoder", m_AlgaeSubsystem.getShaftEncoderPosition());
    SmartDashboard.putNumber("Internal Encoder", m_AlgaeSubsystem.getInternalEncoderPosition());
    if (algaeJoystick.getRawButton(5)) {
      m_AlgaeSubsystem.intakeAlgae();
      System.out.println("Algae taking in");
    } else if (algaeJoystick.getRawButton(6)) {
      m_AlgaeSubsystem.scoreAlgae();
      System.out.println("Scoring Algae");
      /* } else if (algaeJoystick.getRawButton(10)) {
      m_AlgaeSubsystem.shootAlgae(); */
      System.out.println("Shooting Algae");
    } else if (algaeJoystick.getRawButton(8)) {
      m_AlgaeSubsystem.UpAlgae();
      System.out.println("Algae going up");
    } else if (algaeJoystick.getRawButton(7)) {
      m_AlgaeSubsystem.DownAlgae();
      System.out.println("Algae going down");
    } else {
      m_AlgaeSubsystem.disableAlgae();
      m_AlgaeSubsystem.disablehinge();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_AlgaeSubsystem.disableAlgae();
    m_AlgaeSubsystem.intakeAlgae();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
