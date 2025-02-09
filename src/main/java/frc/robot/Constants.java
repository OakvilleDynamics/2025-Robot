// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class HardwareConstants {
    public static final int REV_PDH_ID = 1;
  }

  public static class OperatorConstants {
    public static final int DRIVER_CONTROLLER = 0;
    public static final int COPILOT_CONTROLLER = 1;
  }

  public static class MechanismConstants {

    // Algae Mech Constants

    // Algae Motor IDs
    public static final int ALGAE_INTAKE_1 = 2;
    public static final int ALGAE_INTAKE_2 = 3;
    public static final int ALGAE_HINGE = 4;

    // Inverts
    public static final boolean ALGAE_HINGE_INVERTED = false;
    public static final boolean ALGAE_INTAKE_1_INVERTED = false;
    public static final boolean ALGAE_INTAKE_2_INVERTED = false;

    // Motor Speeds
    public static final double ALGAE_HINGE_SPEED = 0.1;
    public static final double ALGAE_INTAKE_SPEED = 0.5;
    public static final double ALGAE_SHOOT_FAST = 1;

    // Shooter Mech Constants

    // Shooter Motor ID
    public static final int ShooterMotor = 5;

    // Inverts
    public static final boolean Shooter_Inverted = false;

    // Motor speed
    public static final double ShooterSpeed = .3;
  }
}
