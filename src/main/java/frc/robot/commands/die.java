package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;
import frc.robot.Constants;
import frc.robot.Robot;


/**
 * This file is yours to modify or remove if you so choose.
 * It is currently set as the default command for the Elevator
 * subsystem.
 */
public class die extends Command
{
    int level;
    double[] levelHeights;
    double previousPosition;
    double voltage;
    public die ()
    {
        this.levelHeights = new double[] { Constants.Elevator.NET_HEIGHT, Constants.Elevator.MIN_HEIGHT, Constants.Elevator.LEVEL_1_HEIGHT, Constants.Elevator.LEVEL_2_HEIGHT, Constants.Elevator.LEVEL_3_HEIGHT, Constants.Elevator.LEVEL_4_HEIGHT, Constants.Elevator.MAX_HEIGHT, Constants.Elevator.ALGAE_LOW_HEIGHT, Constants.Elevator.ALGAE_HIGH_HEIGHT};
        addRequirements(Elevator.getInstance());
    }
    
    @Override
    public void initialize ()
    {  
    }

    public void checkForBottom ()
    {
        ElevatorSim.voltageApplied = (voltage)*(ElevatorSim.position);
    }

    @Override
    public void execute ()
    {
        ElevatorSim.voltageApplied = -30.0;    
    }

    @Override
    public boolean isFinished ()
    {
        // System.out.println("[ISFINISHED] Elevator Position: " + ElevatorSim.position + "and also " + Math.abs(ElevatorSim.position - levelHeights[level]));
        return Math.abs(ElevatorSim.position - levelHeights[level]) < 0.1; 
        // return false;
    }

    @Override
    public void end (boolean interrupted)
    {
        //ElevatorSim.voltageApplied = 0.;
    }
}