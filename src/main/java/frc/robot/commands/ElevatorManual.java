package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;

/**
 * This file is yours to modify or remove if you so choose.
 * It is currently set as the default command for the Elevator
 * subsystem.
 */
public class ElevatorManual extends Command
{
    public ElevatorManual ()
    {
        addRequirements(Elevator.getInstance());
    }

    @Override
    public void initialize ()
    {

    }

    @Override
    public void execute ()
    {
        if (ElevatorSim.position > 0)
        {
            ElevatorSim.voltageApplied = 0.1;
            // System.out.println(ElevatorSim.voltageApplied);
        }
    }

    @Override
    public void end (boolean interrupted)
    {
        
    }
}
