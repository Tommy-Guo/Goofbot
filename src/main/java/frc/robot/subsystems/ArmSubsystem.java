package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ArmSubsystem {
    Joystick gamePad = new Joystick(appendix.driveControllerID);

    WPI_VictorSPX motorArm_01 = new WPI_VictorSPX(appendix.motorArm1);
    WPI_VictorSPX motorArm_02 = new WPI_VictorSPX(appendix.motorArm1);

    MotorControllerGroup arm = new MotorControllerGroup(motorArm_01, motorArm_02);

    public void teleopPeriodic() {
        if (!common.withinDeadzone(appendix.axisRightY, appendix.joyAreaDeadzone)) {
            arm.set(common.speedLimit(common.quadraticSpeed(gamePad.getRawAxis(appendix.axisRightY)), appendix.maxArmSpeed));
        }
    }
}
