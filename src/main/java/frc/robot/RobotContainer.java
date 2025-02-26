// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AlgaeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.misc.Alerts;
import frc.robot.subsystems.Algae;
import frc.robot.subsystems.Shooter;

// import frc.robot.commands.autoCommands.algae;
// import frc.robot.commands.auto.ShootCoral;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  XboxController driverXbox = new XboxController(0);
  CommandJoystick driverController = new CommandJoystick(1);

  private final Algae algae = new Algae();
  private final Shooter shooter = new Shooter();

  // Named Commands

  public RobotContainer() {

    algae.setDefaultCommand(new AlgaeCommand(algae));
    shooter.setDefaultCommand(new ShooterCommand(shooter));

    NamedCommands.registerCommand("ShootCoral", new InstantCommand(() -> shooter.shootCoral()));
    NamedCommands.registerCommand(
        "StopShooter", new InstantCommand(() -> shooter.disableShooter()));

    configureBindings();

    if (BuildConstants.DIRTY == 1) {
      Alerts.gitDirtyAlert.set(true);
    }

    if (BuildConstants.GIT_BRANCH != "main") {
      if (BuildConstants.GIT_BRANCH.contains("event/")) {
        Alerts.gitEventBranchAlert.set(true);
      } else {
        Alerts.gitMainBranchAlert.set(true);
      }
    }
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  // An example command will be run in autonomous

}
