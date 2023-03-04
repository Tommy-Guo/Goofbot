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
        //driveBase.setDeadband(appendix.deadzoneJoyarea);
        driveBase.setMaxOutput(1);
    }
    public void teleopPeriodic() {
        int inverse = gamePad.getRawButton(appendix.buttonX) ? -1: 1;
        double X = gamePad.getRawAxis(appendix.axisLeftX);
        double Y = gamePad.getRawAxis(appendix.axisLeftY) * inverse;


        X = Math.sqrt(Math.pow(Math.abs(X),3)) * (X<0 ? -1:1);
        Y = Math.sqrt(Math.pow(Math.abs(Y),3)) * (Y<0 ? -1:1);
        driveBase.curvatureDrive(X, Y , true);
    }
}
