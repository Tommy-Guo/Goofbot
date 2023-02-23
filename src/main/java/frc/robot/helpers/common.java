package frc.robot.helpers;

public class common {

    // This allows for smoother increase of speed by quadratically increasing the speed rather than linearly.
    public static double quadraticSpeed(double inputAxis) {
        return inputAxis < 0 ? -Math.pow(inputAxis, 2) : Math.pow(inputAxis, 2);
    }

    // This function limits the speed of the motor to a certain percentage by essentially mapping the input value
    // from it's static range of [-1, 1] to a dynamic range of [-percentSpeed, percentSpeed], thus setting a speed limit.
    public static double speedLimit(double input, double percentSpeed) {
        double fractionSpeed = percentSpeed / 100;
        return ((2 * (input + 1) / 2.0) * fractionSpeed) - fractionSpeed;
    }

    // This function returns wheather or not the axis is within it's respective deadzone.
    public static boolean withinDeadzone(double axisValue, double axisDeadzone) {
        return Math.abs(axisValue) < axisDeadzone;
    }

    // This function returns wheather or not two axis are within their respective deadzone.
    public static boolean withinDeadzone(double firstAxis, double secondAxis, double axisDeadzone) {
        boolean axis1 = Math.abs(firstAxis) < axisDeadzone;
        boolean axis2 = Math.abs(secondAxis) < axisDeadzone;
        return axis1 || axis2;
    }
}
