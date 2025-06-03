package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.FourBar;

public class FourBarControl extends Command {

  private final FourBar m_FourBarSubsystem;

  private final Joystick BarJoystick = new Joystick(OperatorConstants.COPILOT_CONTROLLER);

  private String fourbarActivity = "Fourbar Disabled";

  public FourBarControl(FourBar subsystem) {
    m_FourBarSubsystem = subsystem;
    addRequirements(m_FourBarSubsystem);
  }

  @Override
  public void initialize() {}

  @Override

  // Controls for the Algae

  public void execute() {

    if (BarJoystick.getRawButton(1)) {
      m_FourBarSubsystem.L1();
    } else if (BarJoystick.getRawButton(2)) {
      m_FourBarSubsystem.L4();

      // Set the speed of the fourbar to the joystick value, clamped to a set max speed
      // Check inversion of the joystick to ensure proper mechanism control
      // m_FourBarSubsystem.setFourbarSpeedClamped(BarJoystick.getX());
      /**
       * This is the old code that was used to control the fourbar, it only serves as a reference
       * for new code an also to showcase how to use the SmartDashboard to display information
       * SmartDashboard does not slow down the robot code, so it is safe to use it to display
       * information instead of using System.out.println()
       */
      // if (BarJoystick.getRawButton(1)) {
      //  m_FourBarSubsystem.UpBar();
      //  fourbarActivity = "Fourbar Up";
      // } else if (BarJoystick.getRawButton(2)) {
      //  m_FourBarSubsystem.DownBar();
      //  fourbarActivity = "Fourbar Down";
      // } else if (BarJoystick.getRawButton(9)) {
      //  m_FourBarSubsystem.L2();
      //  fourbarActivity = "Fourbar L2";
      // } else if (BarJoystick.getRawButton(10)) {
      //  m_FourBarSubsystem.L3();
      //  fourbarActivity = "Fourbar L3";
      //  // chagne to 9 maybe in the future
      // } else if (BarJoystick.getRawButton(11)) {
      //  m_FourBarSubsystem.L4();
      //  fourbarActivity = "Fourbar L4";

      // More old code from STL Regional

      // Check if the joystick is being moved, if it is, report that the fourbar is moving
      /*  if (BarJoystick.getX() > 0.1) {
        m_FourBarSubsystem.UpBar();
      } else if (BarJoystick.getX() < 0.1) {
        m_FourBarSubsystem.DownBar();
      } else {
        m_FourBarSubsystem.disableFourBar();
        fourbarActivity = "Fourbar Disabled";
      }
      SmartDashboard.putString("Fourbar Activity", fourbarActivity); */
    }
  }
}
