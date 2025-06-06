package frc.robot;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constants.FourbarConstants;

public final class Configs {

  public static final class FourbarConfig {
    public static final SparkMaxConfig FourbarConfig = new SparkMaxConfig();

    static {
      FourbarConfig.idleMode(IdleMode.kBrake)
          .smartCurrentLimit(FourbarConstants.kCurrentLimit)
          .inverted(false);
      FourbarConfig.absoluteEncoder
          .positionConversionFactor(FourbarConstants.kPositionConversionFactor)
          .velocityConversionFactor(FourbarConstants.kVelocityConversionFactor)
          .inverted(true);
      FourbarConfig.encoder.positionConversionFactor(24).velocityConversionFactor(24);
      FourbarConfig.closedLoop
          .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
          .pid(FourbarConstants.kP, FourbarConstants.kI, FourbarConstants.kD)
          .outputRange(-1, 1)
          .maxMotion
          .maxVelocity(2000)
          .maxAcceleration(1000)
          .positionMode(MAXMotionPositionMode.kMAXMotionTrapezoidal)
          .allowedClosedLoopError(FourbarConstants.kPositionTolerance);
      FourbarConfig.softLimit
          .forwardSoftLimit(FourbarConstants.kFwdSoftLimit)
          .reverseSoftLimit(FourbarConstants.kRevSoftLimit)
          .reverseSoftLimitEnabled(true)
          .forwardSoftLimitEnabled(true);
    }
  }
}
