package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;
import frc.robot.Constants;
import frc.robot.Robot;


public class ElevatorZero extends Command
{
    public ElevatorZero ()
    {
        addRequirements(Elevator.getInstance());
    }
    double previousPosition;
    boolean changed;
    @Override
    public void execute ()
    {
        changed = previousPosition != ElevatorSim.position;
        previousPosition=ElevatorSim.position;
        if(ElevatorSim.position > 0.0)
            ElevatorSim.voltageApplied = (-9.0)*(ElevatorSim.position);
        if (!changed && ElevatorSim.voltageApplied < 0.0)
        {
            ElevatorSim.position = 0.0;
            ElevatorSim.voltageApplied = 0.0;

        }
    }
}
