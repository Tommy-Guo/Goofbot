package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveSubsystem {
    Joystick gamePad = new Joystick(appendix.driveControllerID);

    WPI_VictorSPX motorleft_01 = new WPI_VictorSPX(appendix.motorLeft1);
    WPI_VictorSPX motorleft_02 = new WPI_VictorSPX(appendix.motorLeft2);
    WPI_VictorSPX motorright_01 = new WPI_VictorSPX(appendix.motorRight1);
    WPI_VictorSPX motorright_02 = new WPI_VictorSPX(appendix.motorRight2);

    MotorControllerGroup motorsLeft = new MotorControllerGroup(motorleft_01, motorleft_02);
    MotorControllerGroup motorsRight = new MotorControllerGroup(motorright_01, motorright_02);

    DifferentialDrive driveBase = new DifferentialDrive(motorsLeft, motorsRight);

    public DriveSubsystem() {
        driveBase.setSafetyEnabled(false);
        driveBase.setDeadband(appendix.deadzoneJoyarea);
        driveBase.setMaxOutput(appendix.maxDriveBaseSpeed);
    }

    public void teleopPeriodic() {
        if (!common.withinDeadzone(gamePad.getRawAxis(appendix.axisLeftY), gamePad.getRawAxis(appendix.axisLeftX), appendix.deadzoneJoyarea)) {
            double xSpeed = 0;
            if (gamePad.getRawButton(appendix.buttonA)) {
                xSpeed = common.quadraticSpeed(gamePad.getRawAxis(appendix.axisLeftY)) * -1;
            } else {
                xSpeed = common.quadraticSpeed(gamePad.getRawAxis(appendix.axisLeftY));
            }
            driveBase.curvatureDrive(common.speedLimit(xSpeed, appendix.maxDriveBaseSpeed), gamePad.getRawAxis(appendix.axisLeftX), true);
        }
    }
}
