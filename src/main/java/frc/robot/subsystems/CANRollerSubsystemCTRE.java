// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RollerConstants;
import java.util.function.DoubleSupplier;

/** Class to run the rollers over CAN */
public class CANRollerSubsystemCTRE extends SubsystemBase {
  private final WPI_VictorSPX rollerMotor;

  public CANRollerSubsystemCTRE() {
    // Set up the roller motor as a brushed motor
    rollerMotor = new WPI_VictorSPX(RollerConstants.ROLLER_MOTOR_ID);

    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    rollerMotor.setExpiration(250);

    // Create and apply configuration for roller motor. Voltage compensation helps
    // the roller behave the same as the battery
    // voltage dips. The current limit helps prevent breaker trips or burning out
    // the motor in the event the roller stalls.
    VictorSPXConfiguration rollerConfig = new VictorSPXConfiguration();
    rollerConfig.voltageCompSaturation = RollerConstants.ROLLER_MOTOR_VOLTAGE_COMP;
    rollerMotor.configAllSettings(rollerConfig);
  }

  @Override
  public void periodic() {}

  // Command to run the roller with joystick inputs
  public Command runRoller(
      CANRollerSubsystemCTRE rollerSubsystem, DoubleSupplier forward, DoubleSupplier reverse) {
    return Commands.run(
        () -> rollerMotor.set(forward.getAsDouble() - reverse.getAsDouble()), rollerSubsystem);
  }
}
