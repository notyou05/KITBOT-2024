// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANLauncher;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShootGroundGoVroom extends SequentialCommandGroup {
  CANDrivetrain m_drive;
  CANLauncher m_launcher;
  /** Creates a new AutoShootGroundGoVroom. */
  public AutoShootGroundGoVroom(CANDrivetrain drive, CANLauncher launcher) {
    m_drive = drive;
    m_launcher = launcher;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new PrepareLaunch(launcher, () -> 1.0).withTimeout(1),
      new LaunchNote(launcher).withTimeout(0.5),
      new RunCommand(() -> m_drive.arcadeDrive(0.2, 0), m_drive).withTimeout(5),
      new RunCommand(() -> m_drive.arcadeDrive(0,0), m_drive)
    );
  }
}
