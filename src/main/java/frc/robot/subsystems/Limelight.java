package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    public Limelight() {

    }

    public double tx;
    public double ty;
    
    public void teleopPeriodic() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");

        this.tx = tx.getDouble(0.0);
        this.ty = ty.getDouble(0.0);
    } 

    public double deltaX() {
        return tx;
    }

    public double deltaY() {
        return ty;
    }
}
