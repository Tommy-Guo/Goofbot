package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

public class ArmGyroscope {

    private I2C accelerometer = new I2C(I2C.Port. kOnboard, 0x68);
    private byte[] buffer = new byte[6];
    
    public ArmGyroscope() {

    }

    public void ArmGyroscopeInit() {
        accelerometer.write(0x6B, 0);
    }


    public void teleopPeriodic() {
        // Read the gyro measurements (6 bytes)
        accelerometer.read(0x43, 6, buffer);
    
        // Convert the raw bytes into measurements in degrees per second
        double x = gyroFromBytes(buffer[0], buffer[1]);
        double y = gyroFromBytes(buffer[2], buffer[3]);
        double z = gyroFromBytes(buffer[4], buffer[5]);
    
        System.out.println(x + "," + y + "," + z);
    }
    
    private double gyroFromBytes(byte first, byte second) {
        short tempLow = (short) (first & 0xff);
        short tempHigh = (short) ((second << 8) & 0xff00);
        return (tempLow | tempHigh) * 0.004;
    }
}






import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

public class Gyro {
    private final byte MPU6050_ADDR = 0x68;
    private final int ACCEL_XOUT_H = 0x3B;
    private final int ACCEL_YOUT_H = 0x3D;
    private final int ACCEL_ZOUT_H = 0x3F;
    private final int GYRO_XOUT_H = 0x43;
    private final int GYRO_YOUT_H = 0x45;
    private final int GYRO_ZOUT_H = 0x47;
    private final int PWR_MGMT_1 = 0x6B;

    private I2C m_i2c;
    private byte[] m_buffer;

    public Gyro() {
        m_i2c = new I2C(I2C.Port.kOnboard, MPU6050_ADDR);
        m_buffer = new byte[14];

        m_i2c.write(PWR_MGMT_1, (byte)0x00);
        Timer.delay(0.1);
    }

    public void getMotion6(int[] data) {
        m_i2c.read(ACCEL_XOUT_H, 14, m_buffer);
        data[0] = ((m_buffer[0] << 8) | m_buffer[1]);
        data[1] = ((m_buffer[2] << 8) | m_buffer[3]);
        data[2] = ((m_buffer[4] << 8) | m_buffer[5]);
        data[3] = ((m_buffer[8] << 8) | m_buffer[9]);
        data[4] = ((m_buffer[10] << 8) | m_buffer[11]);
        data[5] = ((m_buffer[12] << 8) | m_buffer[13]);
    }
}

// calculating patch angle
// pitch = atan2(accel_y_raw, sqrt(accel_x_raw^2 + accel_z_raw^2)) * (180 / PI);