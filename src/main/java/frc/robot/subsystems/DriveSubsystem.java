package frc.robot.subsystems;

import frc.robot.helpers.common;
import frc.robot.helpers.appendix;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveSubsystem {


    Joystick gamePad = new Joystick(appendix.driveControllerID);
    LimelightSubsystem limelightSubsystem = new LimelightSubsystem();

    WPI_VictorSPX motorleft_01 = new WPI_VictorSPX(appendix.motorLeft1);
    WPI_VictorSPX motorleft_02 = new WPI_VictorSPX(appendix.motorLeft2);
    WPI_VictorSPX motorright_01 = new WPI_VictorSPX(appendix.motorRight1);
    WPI_VictorSPX motorright_02 = new WPI_VictorSPX(appendix.motorRight2);

    MotorControllerGroup motorsLeft = new MotorControllerGroup(motorleft_01, motorleft_02);
    MotorControllerGroup motorsRight = new MotorControllerGroup(motorright_01, motorright_02);

    DifferentialDrive driveBase = new DifferentialDrive(motorsLeft, motorsRight);

    public DriveSubsystem() {
        driveBase.setSafetyEnabled(true);
    }

    public void teleopPeriodic() {
        limelightSubsystem.teleopPeriodic(); // Pull april tag data from limelight

        boolean inverseDrive = gamePad.getRawButton(appendix.buttonX);
        double driveValue = gamePad.getRawAxis(appendix.axisLeftY) * (inverseDrive ? -1 : 1);

        double rotationValue = gamePad.getRawAxis(appendix.axisLeftX);
        if (gamePad.getRawButton(appendix.buttonA)) {
            rotationValue = common.map(limelightSubsystem.deltaX(), -25, 25, -0.3, 0.3, 2);
        }

        driveBase.curvatureDrive(rotationValue, common.quadraticDrive(-driveValue, 0.4), true);
    }
}
