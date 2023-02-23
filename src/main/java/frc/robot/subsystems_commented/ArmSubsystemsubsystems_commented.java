package frc.robot.subsystems_commented;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ArmSubsystemsubsystems_commented {

    // We must initialize the gamepad here so that we can use it in the teleopPeriodic() function.
    Joystick gamePad = new Joystick(appendix.driveControllerID);

    // In order to control the motors for our arm, we must initialize the VictorSPX
    // motor controllers here, we are pulling the motor IDs from the appendix class.
    WPI_VictorSPX motorArm_01 = new WPI_VictorSPX(appendix.motorArm1);
    WPI_VictorSPX motorArm_02 = new WPI_VictorSPX(appendix.motorArm1);

    // Here we are creating a MotorControllerGroup to group both motors of the
    // arm together because they will always be moving at the same time.
    MotorControllerGroup arm = new MotorControllerGroup(motorArm_01, motorArm_02);

    public void teleopPeriodic() {
        // Checks if the right joystick values is greater than thed deadzone value.
        if (!common.withinDeadzone(appendix.axisRightY, appendix.joyAreaDeadzone)) {
            // For readibility, we'll get the quardratic value of the right joystick Y axis.
            double quadraticValue = common.quadraticSpeed(gamePad.getRawAxis(appendix.axisRightY));
            // Now we'll limit our quadraticSpeed to it's corresponding max speed before
            // sending the value to the arm motor. 
            arm.set(common.speedLimit(quadraticValue, appendix.maxArmSpeed));
        }
    }
}
