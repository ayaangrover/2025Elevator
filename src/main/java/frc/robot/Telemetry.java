package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructPublisher;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;

public class Telemetry 
{
    private static NetworkTableInstance instance = NetworkTableInstance.getDefault();
    public static NetworkTable table = instance.getTable("Telemetry");
    
    private static DoublePublisher voltage = table.getDoubleTopic("Voltage Applied").publish();
    private static DoublePublisher position = table.getDoubleTopic("Elevator Position (Estimated)").publish();
    private static StructPublisher<Pose2d> position2d = table.getStructTopic("Elevator Position (as Pose2d)", Pose2d.struct).publish();
    private static DoublePublisher wear = table.getDoubleTopic("System Wear").publish();

    public static void update ()
    {
        voltage.set(ElevatorSim.voltageApplied);
        position.set(ElevatorSim.position);
        position2d.set(ElevatorSim.getAsPose());
        wear.set(ElevatorSim.wear);
    }
}
