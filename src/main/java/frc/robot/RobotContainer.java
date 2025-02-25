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
import frc.robot.commands.CAMcommand;
import frc.robot.commands.FourBarControl;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.Algae;
import frc.robot.subsystems.CAM;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

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
  private final FourBar fourbar = new FourBar();
  private final CAM cam = new CAM();

  // Named Commands

  public RobotContainer() {

    algae.setDefaultCommand(new AlgaeCommand(algae));
    shooter.setDefaultCommand(new ShooterCommand(shooter));
    fourbar.setDefaultCommand(new FourBarControl(fourbar));
    cam.setDefaultCommand(new CAMcommand(cam));

    NamedCommands.registerCommand("ShootCoral", new InstantCommand(() -> shooter.shootCoral()));
    NamedCommands.registerCommand(
        "StopShooter", new InstantCommand(() -> shooter.disableShooter()));
    NamedCommands.registerCommand("L2", new InstantCommand(() -> fourbar.L2()));
    NamedCommands.registerCommand("L3", new InstantCommand(() -> fourbar.L3()));
    NamedCommands.registerCommand("L4", new InstantCommand(() -> fourbar.L4()));

    configureBindings();
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
