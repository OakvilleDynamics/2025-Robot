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

  // Maximum speed of the robot in meters per second, used to limit acceleration.

  //  public static final class AutonConstants
  //  {
  //
  //    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
  //    public static final PIDConstants ANGLE_PID       = new PIDConstants(0.4, 0, 0.01);
  //  }

  public static final class DrivebaseConstants {

    // Hold time on motor brakes when disabled
    public static final double kWHEEL_LOCK_TIME = 10; // seconds
    public static final double kMAX_AUTO_SPEED = 0.5;
  }

  public static class OperatorConstants {

    // Joystick Deadband
    public static final double kDEADBAND = 0.1;
    public static final double kLEFT_Y_DEADBAND = 0.1;
    public static final double kRIGHT_X_DEADBAND = 0.1;
    public static final double kTURN_CONSTANT = 6;
  }

  public static class HardwareConstants {
    public static final int kREV_PDH_ID = 0;
  }
}
