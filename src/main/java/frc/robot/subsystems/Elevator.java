package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystems.ElevatorSim;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/**
 * Adjust this file!
 * To get info on the elevator's position and voltage applied, reference
 * those static variables in ElevatorSim.java. The position variable
 * in ElevatorSim.java is an estimated position (analagous to the encoder
 * values) and does not reflect the actual position of the elevator necessarily; 
 * for example, it doesn't take into account random deviations from expected movement. 
 * To account for this, you should **modify** the position variable when you know that 
 * it is wrong (for example, if the value is negative).
 * 
 * Note 1: when the elevator moves to the bottom or top and cannot move any more, it is
 * called a hard limit. During a hard limit, neither the real position nor the position
 * variable are modified -- use this to find out when the elevator is at the top or the
 * bottom.
 * Note 2: Do not use the "getAsPose" method in ElevatorSim, and do not modify the
 * ElevatorSim.java file at all.
 * Note 3: Don't create any new static variables or methods; everything should be referenced
 * through the getInstance() method.
 * Note 4: As always, any actions performed on this subsystem should be in their own
 * files in the command directory or in the RobotContainer.java file, not in this class's
 * periodic() methods. Also make sure not to forget addRequirements().
 */
public class Elevator extends SubsystemBase
{
    private static Elevator instance;

    double height;
    double voltageApplied;


    private Elevator ()
    {
    }


    // this is called every 20ms, but sometimes if you are doing
    // a lot of calculations, you will get a "loop overrun" which
    // just means that the next call to periodic() is delayed by
    // a few milliseconds. To account for this, if you are doing
    // calculations based on the elapsed time (for example, for
    // finding velocity) you will want to use System.getCurrentTimeMillis()
    // or some other way for finding the current time.
    @Override
    public void periodic ()
    {
        height = ElevatorSim.position;
        voltageApplied = ElevatorSim.voltageApplied;
        ElevatorSim.update(); // make sure to keep this line either at the
                              // start or at the end of your method body
    }

    public static Elevator getInstance ()
    {
        if (instance == null) instance = new Elevator();
        return instance;
    }
}
