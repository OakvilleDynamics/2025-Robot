package frc.robot;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constants.DumpConstants;

public final class Configs {

  public static final class DumpConfig {
    public static final SparkMaxConfig FourbarConfig = new SparkMaxConfig();

    static {
      FourbarConfig.idleMode(IdleMode.kBrake)
          .smartCurrentLimit(DumpConstants.kCurrentLimit)
          .inverted(false);
      FourbarConfig.absoluteEncoder
          .positionConversionFactor(DumpConstants.kPositionConversionFactor)
          .velocityConversionFactor(DumpConstants.kVelocityConversionFactor)
          .inverted(true);
      FourbarConfig.encoder.positionConversionFactor(24).velocityConversionFactor(24);
      FourbarConfig.closedLoop
          .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
          .pid(DumpConstants.kP, DumpConstants.kI, DumpConstants.kD)
          .outputRange(-1, 1)
          .maxMotion
          .maxVelocity(2000)
          .maxAcceleration(1000)
          .positionMode(MAXMotionPositionMode.kMAXMotionTrapezoidal)
          .allowedClosedLoopError(DumpConstants.kPositionTolerance);
      FourbarConfig.softLimit
          .forwardSoftLimit(DumpConstants.kFwdSoftLimit)
          .reverseSoftLimit(DumpConstants.kRevSoftLimit)
          .reverseSoftLimitEnabled(true)
          .forwardSoftLimitEnabled(true);
    }
  }
}
