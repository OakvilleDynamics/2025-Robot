// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DumpConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AlgaeCommand;
import frc.robot.commands.CAMcommand;
import frc.robot.commands.DumpControl;
import frc.robot.commands.ShooterCommand;
import frc.robot.misc.Alerts;
import frc.robot.subsystems.Algae;
import frc.robot.subsystems.CAM;
import frc.robot.subsystems.Dump;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveSubsystem;
import java.io.File;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;
import swervelib.SwerveInputStream;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  CommandXboxController driverXbox = new CommandXboxController(0);
  CommandJoystick driverController = new CommandJoystick(1);

  LoggedDashboardChooser autoChooser = new LoggedDashboardChooser("AutoChooser");

  private final SwerveSubsystem drivebase =
      new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  /**
   * Converts driver input into a field-relative ChassisSpeeds that is controlled by angular
   * velocity.
   */
  SwerveInputStream driveAngularVelocity =
      SwerveInputStream.of(
              drivebase.getSwerveDrive(),
              () -> driverXbox.getLeftY() * -0.8,
              () -> driverXbox.getLeftX() * -0.8)
          .withControllerRotationAxis(() -> -driverXbox.getRightX())
          .deadband(OperatorConstants.kDEADBAND)
          .scaleTranslation(0.8)
          .allianceRelativeControl(false);

  /**
   * Same as driveAngularVelocity, but slower full outputs at 50% speed. This is used for more
   * precise control.
   */
  SwerveInputStream driveAngularVelocitySlowed =
      SwerveInputStream.of(
              drivebase.getSwerveDrive(),
              () -> driverXbox.getLeftY() * -0.5,
              () -> driverXbox.getLeftX() * -0.5)
          .withControllerRotationAxis(() -> -driverXbox.getRightX())
          .deadband(OperatorConstants.kDEADBAND)
          .scaleTranslation(0.8)
          .allianceRelativeControl(true);

  /** Clone's the angular velocity input stream and converts it to a fieldRelative input stream. */
  SwerveInputStream driveDirectAngle =
      driveAngularVelocity
          .copy()
          .withControllerHeadingAxis(driverXbox::getRightX, driverXbox::getRightY)
          .headingWhile(true);

  SwerveInputStream driveAngularVelocityKeyboard =
      SwerveInputStream.of(
              drivebase.getSwerveDrive(),
              () -> -driverXbox.getLeftY(),
              () -> -driverXbox.getLeftX())
          .withControllerRotationAxis(() -> driverXbox.getRawAxis(2))
          .deadband(OperatorConstants.kDEADBAND)
          .scaleTranslation(0.8)
          .allianceRelativeControl(true);
  // Derive the heading axis with math!
  SwerveInputStream driveDirectAngleKeyboard =
      driveAngularVelocityKeyboard
          .copy()
          .withControllerHeadingAxis(
              () -> Math.sin(driverXbox.getRawAxis(2) * Math.PI) * (Math.PI * 2),
              () -> Math.cos(driverXbox.getRawAxis(2) * Math.PI) * (Math.PI * 2))
          .headingWhile(true);

  private final Algae algae = new Algae();
  private final Shooter shooter = new Shooter();
  private final Dump dump = new Dump();
  private final CAM cam = new CAM();

  // Named Commands

  public RobotContainer() {

    algae.setDefaultCommand(new AlgaeCommand(algae));
    shooter.setDefaultCommand(new ShooterCommand(shooter));
    cam.setDefaultCommand(new CAMcommand(cam));
    dump.setDefaultCommand(new DumpControl(dump));

    configureBindings();

    if (BuildConstants.DIRTY == 1) {
      Alerts.gitDirtyAlert.set(true);
    }

    if (BuildConstants.GIT_BRANCH.equals("main")) {
      if (BuildConstants.GIT_BRANCH.contains("event/")) {
        Alerts.gitEventBranchAlert.set(true);
      } else {
        Alerts.gitMainBranchAlert.set(true);
      }
    }

    autoChooser =
        new LoggedDashboardChooser<Command>("Autonomous Chooser", AutoBuilder.buildAutoChooser());
    autoChooser.addDefaultOption("Nothing", nothing);
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
    Command driveFieldOrientedAnglularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
    Command driveFieldOrientedAnglularVelocitySlowed =
        drivebase.driveFieldOriented(driveAngularVelocitySlowed);
    Command driveFieldOrientedDirectAngleKeyboard =
        drivebase.driveFieldOriented(driveDirectAngleKeyboard);

    drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocitySlowed);

    if (Robot.isSimulation()) {
      driverXbox
          .start()
          .onTrue(
              Commands.runOnce(() -> drivebase.resetOdometry(new Pose2d(3, 3, new Rotation2d()))));
      driverXbox.button(1).whileTrue(drivebase.sysIdDriveMotorCommand());
    }
    if (DriverStation.isTest()) {
      drivebase.setDefaultCommand(
          driveFieldOrientedAnglularVelocity); // Overrides drive command above!

      driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
      driverXbox.y().whileTrue(drivebase.driveToDistanceCommand(1.0, 0.2));
      driverXbox.start().onTrue((Commands.runOnce(drivebase::zeroGyro)));
      driverXbox.back().whileTrue(drivebase.centerModulesCommand());
      driverXbox.leftBumper().onTrue(Commands.none());
      driverXbox.rightBumper().onTrue(Commands.none());
    } else {
      driverXbox.a().onTrue((Commands.runOnce(drivebase::zeroGyro)));
      driverXbox.x().whileTrue(drivebase.driveFieldOriented(driveAngularVelocity));
      driverXbox
          .b()
          .whileTrue(
              drivebase.driveToPose(
                  new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0))));
      driverXbox.start().whileTrue(Commands.none());
      driverXbox.back().whileTrue(Commands.none());
      driverXbox.leftBumper().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
      driverXbox.rightBumper().onTrue(Commands.none());

      driverController
          .button(11)
          .toggleOnTrue(new RunCommand(() -> dump.setTargetPosition(DumpConstants.L1), dump));
      driverController
          .button(12)
          .toggleOnTrue(new RunCommand(() -> dump.setTargetPosition(DumpConstants.L2), dump));
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // An example command will be run in autonomous
    // return drivebase.getAutonomousCommand("Straight");

    /* if (autoChooser.get() == null) {
      doNothing();
    }
    // An example command will be run in autonomous
    if (autoChooser.get() == null) {
      return doNothing();
    } */
    // return drivebase.getAutonomousCommand(autoChooser.get().getName());
    return drivebase
        .driveCommand(() -> -0.5, () -> 0, () -> 0)
        .repeatedly()
        .withTimeout(.5)
        .andThen(drivebase.driveCommand(() -> 0, () -> 0, () -> 0))
        .andThen(shooter.AutoShoot());
  }

  public void setDriveMode() {
    // drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake) {
    drivebase.setMotorBrake(brake);
  }

  public Command nothing = Commands.none();
}
