// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.kCurrentLimit;
import static frc.robot.Constants.DrivetrainConstants.kDriveSensitivity;
import static frc.robot.Constants.DrivetrainConstants.kDriveSpeed;
import static frc.robot.Constants.DrivetrainConstants.kLeftFrontID;
import static frc.robot.Constants.DrivetrainConstants.kLeftMultiplier;
import static frc.robot.Constants.DrivetrainConstants.kLeftRearID;
import static frc.robot.Constants.DrivetrainConstants.kRightFrontID;
import static frc.robot.Constants.DrivetrainConstants.kRightMultiplier;
import static frc.robot.Constants.DrivetrainConstants.kRightRearID;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* This class declares the subsystem for the robot drivetrain if controllers are connected via CAN. Make sure to go to
 * RobotContainer and uncomment the line declaring this subsystem and comment the line for PWMDrivetrain.
 *
 * The subsystem contains the objects for the hardware contained in the mechanism and handles low level logic
 * for control. Subsystems are a mechanism that, when used in conjuction with command "Requirements", ensure
 * that hardware is only being used by 1 command at a time.
 */
public class CANDrivetrain extends SubsystemBase {
  /*Class member variables. These variables represent things the class needs to keep track of and use between
  different method calls. */
  private static double speedMul = 1;
  
  DifferentialDrive m_drivetrain;
    TalonSRX leftFront = new TalonSRX(kLeftFrontID);
    TalonSRX leftRear = new TalonSRX(kLeftRearID);
    TalonSRX rightFront = new TalonSRX(kRightFrontID);
    TalonSRX rightRear = new TalonSRX(kRightRearID);
  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public CANDrivetrain() {
    
    
    

    /*Sets current limits for the drivetrain motors. This helps reduce the likelihood of wheel spin, reduces motor heating
     *at stall (Drivetrain pushing against something) and helps maintain battery voltage under heavy demand */
    

    // Set the rear motors to follow the front motors.
    
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);
    // Invert the left side so both side drive forward with positive motor outputs
    leftFront.setInverted(true);
    rightFront.setInverted(false);
    leftRear.setInverted(true);

    leftFront.configPeakCurrentLimit(kCurrentLimit, 10);
    rightFront.configPeakCurrentLimit(kCurrentLimit, 10);
    leftRear.configPeakCurrentLimit(kCurrentLimit, 10);
    rightRear.configPeakCurrentLimit(kCurrentLimit, 10);
    // Put the front motors into the differential drive object. This will control all 4 motors with
    // the rears set to follow the fronts
    
  }

  /*Method to control the drivetrain using arcade drive. Arcade drive takes a speed in the X (forward/back) direction
   * and a rotation about the Z (turning the robot about it's center) and uses these to control the drivetrain motors */

   public void arcadeDrive(double speed, double rotation) {
    double left = (-speed*kDriveSpeed - rotation*kDriveSensitivity) * speedMul;
    double right = (-speed*kDriveSpeed + rotation*kDriveSensitivity) * speedMul;
    left *= kLeftMultiplier;
    right *= kRightMultiplier;
    leftFront.set(TalonSRXControlMode.PercentOutput,  left);
    rightFront.set(TalonSRXControlMode.PercentOutput, right);

  }

  @Override
  public void periodic() {
    /*This method will be called once per scheduler run. It can be used for running tasks we know we want to update each
     * loop such as processing sensor data. Our drivetrain is simple so we don't have anything to put here */
    //leftFront.set(0.3);
  }

  public void SetSpeedMul(double mul) {
    speedMul = mul;
  }
}
