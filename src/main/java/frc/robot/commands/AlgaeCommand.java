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

    // Shoot or intake the algae based on the buttons pressed
    if (algaeJoystick.getTrigger()) {
      // Shoot the algae if the trigger is pressed
      m_AlgaeSubsystem.shootAlgae();
    } else if (algaeJoystick.getTop()) {
      // Intake the algae if the top button is pressed
      m_AlgaeSubsystem.intakeAlgae();
    } else {
      m_AlgaeSubsystem.disableAlgae();
    }

    // Move the algae hinge up or down based on the buttons pressed
    if (algaeJoystick.getRawButton(3)) {
      // Button 3 is used to move the algae down
      m_AlgaeSubsystem.DownAlgae();
    } else if (algaeJoystick.getRawButton(5)) {
      // Button 5 is used to move the algae up
      m_AlgaeSubsystem.UpAlgae();
    } else {
      m_AlgaeSubsystem.disableAlgae();
    }
  }
 
  @Override
  public void end(boolean interrupted) {
    m_AlgaeSubsystem.disableAlgae();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
