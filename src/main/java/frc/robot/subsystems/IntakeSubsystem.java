package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class IntakeSubsystem {

    Joystick gamePad = new Joystick(appendix.driveControllerID);
    
    WPI_VictorSPX intakeMotor = new WPI_VictorSPX(appendix.motorIntake);
    WPI_VictorSPX intakeRotationMotor = new WPI_VictorSPX(appendix.motorRotationIntake);

    public void teleopPeriodic() {
        intakePeriodic();
        intakeRotationPeriodic();
    }

    private double getArmTrigger() {
        double rightValue = gamePad.getRawAxis(appendix.triggerRight);
        double leftValue =  gamePad.getRawAxis(appendix.triggerLeft);
        return (rightValue > leftValue ? rightValue * -1 : leftValue);
    }

    private void intakePeriodic() {
        intakeMotor.set(gamePad.getRawButton(appendix.buttonRight) ? appendix.intakeSpeed : -appendix.intakeSpeed);
    }

    private boolean stallPhase = false;
    private void intakeRotationPeriodic() {
        if (Math.abs(getArmTrigger()) < 0.1) {
            intakeRotationMotor.set(stallPhase ? 0.1 : -0.1);
            stallPhase = !stallPhase;
        } else {
            intakeRotationMotor.set(getArmTrigger());
        }
    }
}
