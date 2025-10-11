package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.MagRelay;

public class RelayCommand extends Command {

  private final MagRelay m_MagRelaySubsystem;

  private final Joystick MagRelayJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  public RelayCommand(MagRelay subsystem) {
    m_MagRelaySubsystem = subsystem;
    addRequirements(m_MagRelaySubsystem);
  }

  public void execute() {
    if (MagRelayJoystick.getRawButton(8)) {
      m_MagRelaySubsystem.RelayOn();
    } else {
      m_MagRelaySubsystem.RelayOff();
    }
  }
}
