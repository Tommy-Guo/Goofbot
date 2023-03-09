package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class NeoArmSubsystem {
    Joystick gamePad = new Joystick(appendix.driveControllerID);

    CANSparkMax neoMotor = new CANSparkMax(17, MotorType.kBrushless);
    
    public void teleopPeriodic() {
        neoMotor.set(gamePad.getRawAxis(appendix.axisLeftY) * -0.45);
    }
}