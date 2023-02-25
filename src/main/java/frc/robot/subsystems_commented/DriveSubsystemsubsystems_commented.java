package frc.robot.subsystems_commented;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveSubsystemsubsystems_commented {

    // We must initialize the gamepad here so that we can use it in the teleopPeriodic() function.
    Joystick gamePad = new Joystick(appendix.driveControllerID);

    // In order to control the motors on our drive train, we must initialize the VictorSPX
    // motor controllers here, we are pulling the motor IDs from the appendix class.
    WPI_VictorSPX motorleft_01 = new WPI_VictorSPX(appendix.motorLeft1);
    WPI_VictorSPX motorleft_02 = new WPI_VictorSPX(appendix.motorLeft2);
    WPI_VictorSPX motorright_01 = new WPI_VictorSPX(appendix.motorRight1);
    WPI_VictorSPX motorright_02 = new WPI_VictorSPX(appendix.motorRight2);

    // Here we are creating a MotorControllerGroup for each side of the drive train so that
    // we can control both motors on each side of the drive train at the same time.
    MotorControllerGroup motorsLeft = new MotorControllerGroup(motorleft_01, motorleft_02);
    MotorControllerGroup motorsRight = new MotorControllerGroup(motorright_01, motorright_02);

    DifferentialDrive driveBase = new DifferentialDrive(motorsLeft, motorsRight);

    public DriveSubsystemsubsystems_commented() {
        // motorright_01.setInverted(true);
        // Initializing properties for drive base
        driveBase.setSafetyEnabled(false);
        driveBase.setDeadband(appendix.deadzoneJoyarea);
        driveBase.setMaxOutput(appendix.maxDriveBaseSpeed);
    }

    public void teleopPeriodic() {
        // We want to check if our left axis is within our deadzone, this solves issues
        // such as joystick drift & minute human error.
        if (!common.withinDeadzone(gamePad.getRawAxis(appendix.axisLeftY), gamePad.getRawAxis(appendix.axisLeftX), appendix.deadzoneJoyarea)) {
            double xSpeed = 0;
            // We want to check if our A button is pressed, if it is then we want to invert
            // the X axis so that we can drive inversely on the spot.
            if (gamePad.getRawButton(appendix.buttonA)) {
                xSpeed = common.quadraticSpeed(gamePad.getRawAxis(appendix.axisLeftY)) * -1;
            } else {
                xSpeed = common.quadraticSpeed(gamePad.getRawAxis(appendix.axisLeftY));
            }
            double withSpeedLimit = common.speedLimit(xSpeed, appendix.maxDriveBaseSpeed);
            driveBase.curvatureDrive(withSpeedLimit, gamePad.getRawAxis(appendix.axisLeftX), true);
        }
    }
}
