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

    public DriveSubsystem() {
        driveBase.setSafetyEnabled(true);
    }

    public void teleopPeriodic() {
        int inverse = gamePad.getRawButton(appendix.buttonX) ? -1: 1;
        double yValue = gamePad.getRawAxis(appendix.axisLeftY) * inverse;
        driveBase.curvatureDrive(common.quadraticDrive(gamePad.getRawAxis(appendix.axisLeftX), 0.4), common.quadraticDrive(-yValue, 0.6), true);
    }
}
