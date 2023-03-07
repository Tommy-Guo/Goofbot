package frc.robot.subsystems;

import frc.robot.helpers.appendix;
import frc.robot.helpers.common;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class SparkNeoTests {
    Joystick gamePad = new Joystick(appendix.driveControllerID);


    CANSparkMax neoMotor = new CANSparkMax(17, MotorType.kBrushless);
    RelativeEncoder neoEncoder = neoMotor.getEncoder();


    public SparkNeoTests() {
        // neoMotor.restoreFactoryDefaults();
    }
    
    public void teleopPeriodic() {
        neoMotor.set(gamePad.getRawAxis(appendix.axisLeftY));

        print(neoEncoder.getPosition());
        print(neoEncoder.getVelocity());
        print("---------------------------------");
    }

    public void print(Object n) {
        System.out.println(n.toString());
    }
}
