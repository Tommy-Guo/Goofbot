package frc.robot.helpers;

// This class is used to store all the values and IDs of the controllers, motors, and other things that are used in the code.
// It is the appendix of our robot. All values & IDs are stored here for better readibility and easier access.

public class appendix {

    // Values & IDs of controllers //
    public static int driveControllerID = 0;
    public static int actionControllerID = 0;

    // Values & IDs of inputs on controller //
    public static int buttonX = 3;
    public static int buttonY = 4;
    public static int buttonA = 1;
    public static int buttonB = 2;
 
    public static int buttonRight = 5;
    public static int buttonLeft = 6;
 
    public static int triggerLeft = 2;
    public static int triggerRight = 3;
 
    public static int axisLeftX = 0;
    public static int axisLeftY = 1;
    public static int axisRightX = 4;
    public static int axisRightY = 5;
 
    // Trigger & Joyarea Deadzones //
    public static double joyAreaDeadzone = 0.05;
    public static double triggerDeadzone = 0.05;

    // Values & IDs of drive base motors //
    public static int motorLeft1 = 3;
    public static int motorLeft2 = 4;
    public static int motorRight1 = 1;
    public static int motorRight2 = 2;
 
    public static double maxDriveBaseSpeed = 1;
 
    // Values & IDs of intake motors //
    public static int motorIntake = 7;
    public static double maxIntakeSpeed = 0.4;

    // Values & IDs of Arm motors //
    public static int motorArm1 = 5;
    public static int motorArm2 = 6;
    public static double maxArmSpeed = 0.4;
}
