// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase =
      new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandJoystick driverController = new CommandJoystick(1);

  // CommandJoystick driverController   = new
  // CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  XboxController driverXbox = new XboxController(0);
  // Connect

  private final LoggedDashboardChooser<Command> autoChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();

    autoChooser =
        new LoggedDashboardChooser<>("Autonomous Chooser", AutoBuilder.buildAutoChooser());

    autoChooser.addDefaultOption("Do nothing", null);

    final AbsoluteDriveAdv closedAbsoluteDriveAdv =
        new AbsoluteDriveAdv(
            drivebase,
            () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
            () ->
                MathUtil.applyDeadband(driverXbox.getRightX(), OperatorConstants.RIGHT_X_DEADBAND),
            driverXbox::getYButtonPressed,
            driverXbox::getAButtonPressed,
            driverXbox::getXButtonPressed,
            driverXbox::getBButtonPressed);

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle =
        drivebase.driveCommand(
            () -> MathUtil.applyDeadband(-driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(-driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
            () ->
                MathUtil.applyDeadband(-driverXbox.getRightX(), OperatorConstants.RIGHT_X_DEADBAND),
            () ->
                MathUtil.applyDeadband(
                    -driverXbox.getRightY(), OperatorConstants.RIGHT_Y_DEADBAND));

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity =
        drivebase.driveCommand(
            () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
            () -> driverXbox.getRightX() * 0.5);

    // Checks if the git repo is dirty and output warnings as errors
    if (BuildConstants.DIRTY != 0) {
      DriverStation.reportError(
          "WARNING! THERE ARE CHANGES THAT CURRENTLY IS NOT COMMITTED! PLEASE COMMIT THOSE CHANGES TO GIT/GITHUB OR REVERT THOSE CHANGES!",
          false);
      DriverStation.reportError("To see the changes, run `git status` in the terminal", false);
      DriverStation.reportError(
          "To commit the changes, run `git add .` to stage the changes, then run `git commit -m \"<commit message>\"` to commit the changes",
          false);
      DriverStation.reportError(
          "To revert the changes, run `git reset --hard` to revert the changes. This will permanently delete those changes",
          false);
      DriverStation.reportError(
          "You can also open the GitHub Desktop application to perform these actions", false);
      DriverStation.reportError("Remember to push your changes after committing", false);
    }
    // Checks if the branch is currently not on 'main' and output the warnings as errors
    if (!BuildConstants.GIT_BRANCH.equals("main")) {
      DriverStation.reportError(
          "WARNING! YOU ARE NOT ON THE MAIN BRANCH! PLEASE MERGE YOUR CHANGES TO MAIN OR REVERT THOSE CHANGES!",
          false);
      DriverStation.reportError(
          "To see the current branch, run `git branch` in the terminal", false);
      DriverStation.reportError(
          "To merge your changes to main, push your changes to GitHub and go to the GitHub repository and create a pull request",
          false);
      DriverStation.reportError("Wait for the pull request to be reviewed and merged", false);
    } else if (BuildConstants.GIT_BRANCH.contains("event")) {
      DriverStation.reportWarning(
          "You are currently on an `event` branch. After an event, please merge your changes to main as the event progresses or after the event is over",
          false);
      DriverStation.reportWarning(
          "With event branches, changes made on the fly here should be committed before each build/deploy to the robot",
          false);
      DriverStation.reportWarning(
          "If you are done with the event, please merge and delete the event branch", false);
      DriverStation.reportWarning(
          "If you are not done with the event, please keep the event branch and continue working on it",
          false);
      DriverStation.reportWarning(
          "If you are not sure, please ask your team leader or mentor for help", false);
    }

    drivebase.setDefaultCommand(driveFieldOrientedDirectAngle);
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

    new JoystickButton(driverXbox, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    new JoystickButton(driverXbox, 3).whileTrue(Commands.runOnce(() -> doNothing()));
    new JoystickButton(driverXbox, 2)
        .whileTrue(
            Commands.deferredProxy(
                () ->
                    drivebase.driveToPose(
                        new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))));
    new JoystickButton(driverXbox, 4).whileTrue(new InstantCommand(drivebase::lock, drivebase));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    if (autoChooser.get() == null) {
      doNothing();
    }
    // An example command will be run in autonomous
    if (autoChooser.get() == null) {
      return doNothing();
    }
    return drivebase.getAutonomousCommand(autoChooser.get().getName());
  }

  public void setDriveMode() {
    // drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake) {
    drivebase.setMotorBrake(brake);
  }

  public Command doNothing() {
    Command nothing = new InstantCommand();
    nothing.setName("NOTHING");
    return nothing;
  }
}
