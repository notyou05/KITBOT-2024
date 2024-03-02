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
  public static class OperatorConstants {
    // Port numbers for driver and operator gamepads. These correspond with the numbers on the USB
    // tab of the DriverStation
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final int kEverythingControllerPort = 2;

    public static final boolean AutoWithShoot = false;
  }

  public static class DrivetrainConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kLeftRearID = 1;
    public static final int kLeftFrontID = 2;
    public static final int kRightRearID = 3;
    public static final int kRightFrontID = 4;

    // Current limit for drivetrain motors
    public static final int kCurrentLimit = 40;

    public static final double kLeftMultiplier = 1;
    public static final double kRightMultiplier = 1;

    public static final double kDriveSpeed = 1.5;
    public static final double kDriveSensitivity = 1.2;

    public static final double kSlowSpeedMul = 0.5;   

  }

  public static class LauncherConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kBottomID = 8;
    public static final int kTopID = 7;

    // Current limit for launcher and feed wheels
    public static final int kLauncherCurrentLimit = 100;
    public static final int kFeedCurrentLimit = 100;

    // Speeds for wheels when intaking and launching. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kTopLaunchSpeed = -10.0;
    public static final double kBottomLaunchSpeed = 6.1;
    public static final double kTopIntakeSpeed = 1;
    public static final double kBottomIntakeSpeed = -0.3;

    public static final double kAmpLaunchSpeed = 0.02;

    public static final double kLauncherDelay = 0.3;
  }

  public static class VisionConstants {
    public static final int kCrosshairX = 100;
    public static final int kCrosshairY = 75;
  }
}
