package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MagRelay extends SubsystemBase {
  private final Relay Relay1 = new Relay(0);
  private final Relay Relay2 = new Relay(1);
  private final Relay Relay3 = new Relay(2);

  public void RelayOn() {
    Relay1.set(Relay.Value.kForward);
    Relay2.set(Relay.Value.kForward);
    Relay3.set(Relay.Value.kForward);
  }

  public void RelayOff() {
    Relay1.set(Relay.Value.kOff);
    Relay2.set(Relay.Value.kOff);
    Relay3.set(Relay.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
