package frc.robot.subsystems_commented;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class IntakeSubsystemsubsystems_commented {

    // We must initialize the gamepad here so that we can use it in the teleopPeriodic() function.
    Joystick gamePad = new Joystick(appendix.driveControllerID);
    
    // In order to control the motor on our intake, we must initialize the VictorSPX
    // motor controller here, we are pulling the motor IDs from the appendix class.
    WPI_VictorSPX intakeMotor = new WPI_VictorSPX(appendix.motorIntake);

    // This function chooses which trigger value to use, prioritizing the right trigger.
    double getArmTrigger() {
        // Pulling the raw values from our triggers.
        double rightValue = gamePad.getRawAxis(appendix.triggerRight);
        double leftValue = -1 * gamePad.getRawAxis(appendix.triggerLeft);
        // Checking if either trigger is greater than the deadzone value, again prioritizing the right trigger.
        if (rightValue > appendix.deadzoneTrigger) {
            return rightValue;
        } else if (leftValue > appendix.deadzoneTrigger) {
            return leftValue;
        }
        return 0;
    }

    public void teleopPeriodic() {
        // For readibility, we'll get the quardratic value of the right joystick Y axis.
        double quadraticValue = common.quadraticSpeed(getArmTrigger());
        // Now we'll limit our quadraticSpeed to it's corresponding max speed before
        // sending the value to the intake motor. 
        intakeMotor.set(common.speedLimit(quadraticValue, appendix.maxIntakeSpeed));
    }
}
