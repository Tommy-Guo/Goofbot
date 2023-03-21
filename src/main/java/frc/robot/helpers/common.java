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

        public static double mapOneRangeToAnother(double sourceNumber, double fromA, double fromB, double toA, double toB, int decimalPrecision ) {
            double deltaA = fromB - fromA;
            double deltaB = toB - toA;
            double scale  = deltaB / deltaA;
            double negA   = -1 * fromA;
            double offset = (negA * scale) + toA;
            double finalNumber = (sourceNumber * scale) + offset;
            int calcScale = (int) Math.pow(10, decimalPrecision);
            return (double) Math.round(finalNumber * calcScale) / calcScale;
        }
}