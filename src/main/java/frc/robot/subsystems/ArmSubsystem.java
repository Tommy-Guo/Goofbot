package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ArmSubsystem {
    Joystick gamePad = new Joystick(appendix.driveControllerID);

    WPI_VictorSPX motorArm_01 = new WPI_VictorSPX(appendix.motorArm1);
    WPI_VictorSPX motorArm_02 = new WPI_VictorSPX(appendix.motorArm2);

    MotorControllerGroup arm = new MotorControllerGroup(motorArm_01, motorArm_02);
    
    public void teleopPeriodic() {
        arm.set(gamePad.getRawAxis(appendix.axisRightY) * -0.65);
    }
}
