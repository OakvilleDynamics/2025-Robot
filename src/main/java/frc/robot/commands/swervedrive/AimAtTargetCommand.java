// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive;

import org.photonvision.PhotonUtils;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Vision.Cameras;
import swervelib.SwerveDrive;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AimAtTargetCommand extends Command {

  private final SwerveSubsystem swerveSubsystem;

  private final Cameras mainCamera = Cameras.PI_TEST;
  private final AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2025Reefscape);

  // Read in relevant data from the Camera
  double turn, forward, strafe;
  boolean targetVisible = false;
  double targetYaw = 0.0;
  double targetRange = 0.0;

  /** Creates a new AimAtTargetCommand. */
  public AimAtTargetCommand(SwerveSubsystem swerveSubsystem) {
    this.swerveSubsystem = swerveSubsystem;

    addRequirements(this.swerveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    var results = mainCamera.getLatestResult();
    if (!results.isEmpty()) {
      // Camera processed a new frame since last
      // Get the last one in the list.
      var result = results.get().targets.get(results.get().targets.size() - 1);
      if (result != null) {
        // At least one AprilTag was seen by the camera
        for (var target : results.get().targets) {
          if (target != null) {
            int targetId = target.fiducialId;
            targetYaw = target.getYaw();
            targetRange =
              PhotonUtils.calculateDistanceToTargetMeters(
                  0.5, // Measured with a tape measure, or in CAD.
                  aprilTagFieldLayout.getTagPose(targetId).get().getZ(), // Pull ID from from AprilTag field layout and determine height from a pose3d
                  Units.degreesToRadians(-30.0), // Measured with a protractor, or in CAD.
                  Units.degreesToRadians(target.getPitch()));
                  targetVisible = true;
          }
        }
      }
    }

    turn = (Constants.VisionConstants.VISION_DES_ANGLE_deg - targetYaw) * Constants.VisionConstants.VISION_TURN_kP * Constants.DrivebaseConstants.kMAX_AUTO_SPEED;
    forward = (Constants.VisionConstants.VISION_DES_RANGE_m - targetRange) * Constants.VisionConstants.VISION_STRAFE_kP * Constants.DrivebaseConstants.kMAX_AUTO_SPEED;

    swerveSubsystem.drive(new ChassisSpeeds(forward, strafe, turn));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
