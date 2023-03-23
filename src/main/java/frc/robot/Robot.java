// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cscore.UsbCamera;
import frc.robot.autonomous.autonomous;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    autonomous autoSystem;

    ArmSubsystem armSubsystem;
    IntakeSubsystem intakeSubsystem;
    DriveSubsystem driveSubsystem;

    @Override
    public void robotInit() {
        this.armSubsystem = new ArmSubsystem();
        this.intakeSubsystem = new IntakeSubsystem();
        this.driveSubsystem = new DriveSubsystem();

        UsbCamera usbCamera = CameraServer.startAutomaticCapture("Main Camera", 0);
        usbCamera.setResolution(160, 120);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autoSystem = new autonomous();
    }

    @Override
    public void autonomousPeriodic() {
        autoSystem.runAuto();
    }

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
        this.armSubsystem.teleopPeriodic();
        this.intakeSubsystem.teleopPeriodic();
        this.driveSubsystem.teleopPeriodic();
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }
}