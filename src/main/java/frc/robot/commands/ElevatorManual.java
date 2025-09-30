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
        ElevatorSim.voltageApplied = (2.0) * RobotContainer.getInstance().controller.getLeftX();
    }

    @Override
    public boolean isFinished ()
    {
        return false;
    }

    @Override
    public void end (boolean interrupted)
    {
        
    }
}
