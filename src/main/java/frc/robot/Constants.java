// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final double kROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter kCHASSIS =
      new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), kROBOT_MASS);
  public static final double kLOOP_TIME = 0.13; // s, 20ms + 110ms sprk max velocity lag
  public static final double kMAX_SPEED = Units.feetToMeters(14.5);

  public static final class HardwareConstants {
    public static final int REV_PDH_ID = 20;
  }

  public static class OperatorConstants {
    public static final int DRIVER_CONTROLLER = 0;
    public static final int COPILOT_CONTROLLER = 1;

    // Joystick Deadband
    public static final double kDEADBAND = 0.1;
    public static final double kLEFT_Y_DEADBAND = 0.1;
    public static final double kRIGHT_X_DEADBAND = 0.1;
    public static final double kTURN_CONSTANT = 6;
  }

  public static final class DrivebaseConstants {

    // Hold time on motor brakes when disabled
    public static final double kWHEEL_LOCK_TIME = 10; // seconds
    public static final double kMAX_AUTO_SPEED = 0.5;
  }

  public static class MechanismConstants {

    // Algae Mech Constants

    // Algae Motor IDs
    public static final int ALGAE_right = 14;
    public static final int ALGAE_left = 13;
    public static final int ALGAE_HINGE = 15;
    public static final int ALGAE_Encoder = 1;

    // Shooter Motor ID
    public static final int ShooterMotor = 17;

    // Four Bar ID
    public static final int FourbarMotor = 16;
    public static final int FourbarEncoder = 0;

    // Inverts
    public static final boolean ALGAE_HINGE_INVERTED = false;
    public static final boolean ALGAE_INTAKE_1_INVERTED = false;
    public static final boolean ALGAE_INTAKE_2_INVERTED = false;
    public static final boolean FourBar_Inverted = false;
    public static final boolean Shooter_Inverted = false;
    public static final boolean CAMinverted = false;

    // Motor Speeds for Algae
    public static final double ALGAE_HINGE_SPEED = 0.2;
    public static final double ALGAE_INTAKE_SPEED = 0.5;
    public static final double ALGAE_SHOOT_FAST = 1;

    // Motor speed for Shooter
    public static final double ShooterSpeed = -.3;
    public static final double SlowShooter = .15;

    // Motor Speed for FourBar
    public static final double FourBarSpeed = .3;

    // CAM motor IDs
    public static final int CAMleft = 18;
    public static final int CAMright = 19;

    // CAM motor speed
    public static final double CAMspeed = .1;
  }

  public static final class FourbarConstants {
    public static final int kStartingPosition = 0;
    public static final int kCurrentLimit = 40;

    public static final int kPositionConversionFactor = 360;
    public static final int kVelocityConversionFactor = 1000;

    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double kFwdSoftLimit = 3600;
    public static final double kRevSoftLimit = -3600;

    public static final double kPositionTolerance = 0.5;

    public static final double L1 = 1000;
    public static final double L2 = 2000;
    public static final double L3 = 3000;
    public static final double L4 = 360;
  }
}
