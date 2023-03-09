package frc.robot.helpers;

public class common {
        public static double quadraticDrive(double inputAxis) {
            double magnitude = Math.pow(inputAxis, 2);
            return inputAxis < 0 ? -magnitude : magnitude;
        }

        public static double quadraticDrive(double inputAxis, double speedModifier) {
            double magnitude = Math.pow(inputAxis, 2) * speedModifier;
            return inputAxis < 0 ? -magnitude : magnitude;
        }
    
        public static boolean withinDeadzone(double axisValue, double axisDeadzone) {
            return Math.abs(axisValue) < axisDeadzone;
        }
    
        public static boolean withinDeadzone(double firstAxis, double secondAxis, double axisDeadzone) {
            boolean axis1 = Math.abs(firstAxis) < axisDeadzone;
            boolean axis2 = Math.abs(secondAxis) < axisDeadzone;
            return axis1 || axis2;
        }
}