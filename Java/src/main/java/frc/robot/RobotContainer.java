// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
import frc.robot.commands.autos.AutoGoVroom;
import frc.robot.commands.autos.AutoShootGroundGoVroom;
import frc.robot.subsystems.CANDrivetrain;
// import frc.robot.subsystems.CANDrivetrain;
// import frc.robot.subsystems.CANLauncher;
import frc.robot.subsystems.CANLauncher;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here.
  private final CANDrivetrain m_drivetrain = new CANDrivetrain();
  // private final CANDrivetrain m_drivetrain = new CANDrivetrain();
  private final CANLauncher m_launcher = new CANLauncher();
  // private final CANLauncher m_launcher = new CANLauncher();

  /*The gamepad provided in the KOP shows up like an XBox controller if the mode switch is set to X mode using the
   * switch on the top.*/
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);
    private final CommandXboxController m_everythingController =
      new CommandXboxController(OperatorConstants.kEverythingControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    smartDashboardSetup();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be accessed via the
   * named factory methods in the Command* classes in edu.wpi.first.wpilibj2.command.button (shown
   * below) or via the Trigger constructor for arbitary conditions
   */
  private void configureBindings() {

    
    // Set the default command for the drivetrain to drive using the joysticks
    if(m_everythingController != null) {
      //m_everythingController.leftTrigger().onTrue(new RunCommand(() -> m_drivetrain.SetSpeedMul(DrivetrainConstants.kSlowSpeedMul), m_drivetrain));
      //m_everythingController.leftTrigger().onFalse(new RunCommand(() -> m_drivetrain.SetSpeedMul(1), m_drivetrain));
      m_drivetrain.setDefaultCommand(
          new RunCommand(
              () ->
                  m_drivetrain.arcadeDrive(
                      m_everythingController.getLeftY()/2.5, m_everythingController.getLeftX()/2.5),
              m_drivetrain));
    } else {
      //m_driverController.leftTrigger().onTrue(new RunCommand(() -> m_drivetrain.SetSpeedMul(DrivetrainConstants.kSlowSpeedMul), m_drivetrain));
      //m_driverController.leftTrigger().onFalse(new RunCommand(() -> m_drivetrain.SetSpeedMul(1), m_drivetrain));
      m_drivetrain.setDefaultCommand(
          new RunCommand(
              () ->
                  m_drivetrain.arcadeDrive(
                      m_driverController.getLeftY()/2.5, m_driverController.getLeftX()/2.5),
              m_drivetrain));
    }

    /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
    
    if (m_everythingController != null) {
      m_everythingController
        .a()
        .whileTrue(
            new PrepareLaunch(m_launcher, () -> 1.0)
            .withTimeout(LauncherConstants.kLauncherDelay)
            .andThen(new LaunchNote(m_launcher)).
            handleInterrupt(() -> m_launcher.stop()));
      m_everythingController
        .x()
        .whileTrue(
            new PrepareLaunch(m_launcher, () -> 1.0)
            .withTimeout(LauncherConstants.kLauncherDelay)
            .andThen(new LaunchNote(m_launcher,LauncherConstants.kAmpLaunchSpeed)).
            handleInterrupt(() -> m_launcher.stop()));
      m_everythingController.leftBumper().whileTrue(m_launcher.getIntakeCommand());
    } else { 
      m_operatorController
        .a()
        .whileTrue(
            new PrepareLaunch(m_launcher, () -> 1.0)
            .withTimeout(LauncherConstants.kLauncherDelay)
            .andThen(new LaunchNote(m_launcher)).
            handleInterrupt(() -> m_launcher.stop()));
             
      m_operatorController
        .x()
        .whileTrue(
            new PrepareLaunch(m_launcher, () -> 1.0)
            .withTimeout(LauncherConstants.kLauncherDelay)
            .andThen(new LaunchNote(m_launcher,LauncherConstants.kAmpLaunchSpeed)).
            handleInterrupt(() -> m_launcher.stop()));
      m_operatorController.leftBumper().whileTrue(m_launcher.getIntakeCommand());
    }
     //m_operatorController.leftTrigger().whileTrue(new PrepareLaunch(m_launcher, () -> m_operatorController.getLeftTriggerAxis()));

    // Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper
  }

  private void smartDashboardSetup() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
  //   An example command will be run in autonomous
    /*
    Command autoCommand = new AutoGoVroom(m_drivetrain);
    if(OperatorConstants.AutoWithShoot) {
      autoCommand = new AutoShootGroundGoVroom(m_drivetrain, m_launcher);
    }
    return autoCommand;
    */

    if(OperatorConstants.AutoWithShoot) {
      return new AutoShootGroundGoVroom(m_drivetrain, m_launcher);
    } else {
      return new AutoGoVroom(m_drivetrain);
    }
    //return new AutoGoVroom(m_drivetrain);
  }
}
