package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;
import frc.robot.Constants;
import frc.robot.Robot;


public class ElevatorButton extends Command
{
    Boolean goingUp;
    double factor;
    Boolean hitEnd;
    Boolean changed;
    double previousPosition;
    public ElevatorButton (Boolean up)
    {
        addRequirements(Elevator.getInstance());
        goingUp = up;
        factor = 6;
        hitEnd=false;
    }

    @Override
    public void execute ()
    {
        if (goingUp && (ElevatorSim.position+0.1) <= Constants.Elevator.MAX_HEIGHT && (ElevatorSim.position+0.1) >= Constants.Elevator.MIN_HEIGHT)
        {
            ElevatorSim.voltageApplied = factor*(0.1);
        }
        else if (!goingUp && (ElevatorSim.position-0.1) >= Constants.Elevator.MIN_HEIGHT && (ElevatorSim.position-0.1) <= Constants.Elevator.MAX_HEIGHT)
        {
            ElevatorSim.voltageApplied = factor*(-0.1);
        }
        else
        {
            ElevatorSim.voltageApplied=0.0;
        }
    }

    public void checkEnd (int neg)
    {
        changed = previousPosition != ElevatorSim.position;
        previousPosition=ElevatorSim.position;
        ElevatorSim.voltageApplied = factor*(0.1)*neg;
        if (!changed && ElevatorSim.voltageApplied < 0.0)
        {
            ElevatorSim.position = 0.0;
            ElevatorSim.voltageApplied = 0.0;
        }
    }

    @Override
    public boolean isFinished ()
    {
        return false;
    }
}
