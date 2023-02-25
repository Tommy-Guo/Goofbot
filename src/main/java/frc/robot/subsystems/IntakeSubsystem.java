package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class IntakeSubsystem {

    Joystick gamePad = new Joystick(appendix.driveControllerID);
    
    WPI_VictorSPX intakeMotor = new WPI_VictorSPX(appendix.motorIntake);

    double getArmTrigger() {
        double rightValue = gamePad.getRawAxis(appendix.triggerRight);
        double leftValue = -1 * gamePad.getRawAxis(appendix.triggerLeft);
        if (rightValue > appendix.deadzoneJoyarea) {
            return rightValue;
        } else if (leftValue > appendix.deadzoneJoyarea) {
            return leftValue;
        }
        return 0;
    }

    public void teleopPeriodic() {
        intakeMotor.set(common.speedLimit(common.quadraticSpeed(getArmTrigger()), appendix.maxIntakeSpeed));
    }
}
