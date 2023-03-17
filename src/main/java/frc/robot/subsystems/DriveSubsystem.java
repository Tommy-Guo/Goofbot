package frc.robot.subsystems;

import frc.robot.helpers.common;
import frc.robot.helpers.appendix;
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

    private static double sensitivity1 = 0.45;
    private static double sensitivity2 = 0.35;

    private double currentRotationSpeed = sensitivity1;

    public DriveSubsystem() {
        driveBase.setSafetyEnabled(true);
    }

    public void teleopPeriodic() {
        // switch rotation profiles;
        currentRotationSpeed = gamePad.getRawButton(appendix.buttonBack) ? sensitivity1 : sensitivity2;

        System.out.println(currentRotationSpeed);

        double driveValue = gamePad.getRawAxis(appendix.axisLeftY);
        double rotationValue = gamePad.getRawAxis(appendix.axisLeftX);

        double driveSpeed = 0.8;
        if (gamePad.getRawButton(appendix.buttonY)) {
            driveSpeed = 1;
        } else if (gamePad.getRawButton(appendix.buttonB)) {
            driveSpeed = 0.6;
        }
        driveSpeed = gamePad.getRawButton(appendix.buttonX) ? driveSpeed * -1 : driveSpeed;

        driveBase.curvatureDrive(common.quadraticDrive(rotationValue, currentRotationSpeed), common.quadraticDrive(-driveValue, driveSpeed), true);
    }
}
