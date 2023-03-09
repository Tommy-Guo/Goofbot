package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class IntakeSubsystem {

    Joystick gamePad = new Joystick(appendix.driveControllerID);
    
    WPI_VictorSPX intakeMotor = new WPI_VictorSPX(appendix.motorIntake);

    double getArmTrigger() {
        double rightValue = gamePad.getRawAxis(appendix.triggerRight);
        double leftValue =  gamePad.getRawAxis(appendix.triggerLeft);
        return (rightValue > leftValue ? rightValue * -1 : leftValue);
    }

    public void teleopPeriodic() {
        intakeMotor.set(getArmTrigger());
    }
}
