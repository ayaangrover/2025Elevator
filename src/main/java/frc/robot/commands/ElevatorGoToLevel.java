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
public class ElevatorGoToLevel extends Command
{
    int level;
    double[] levelHeights;
    double previousPosition;
    double voltage;
    public ElevatorGoToLevel (int level, double strength)
    {
        this.level = level;
        this.levelHeights = new double[] { Constants.Elevator.NET_HEIGHT, Constants.Elevator.MIN_HEIGHT, Constants.Elevator.LEVEL_1_HEIGHT, Constants.Elevator.LEVEL_2_HEIGHT, Constants.Elevator.LEVEL_3_HEIGHT, Constants.Elevator.LEVEL_4_HEIGHT, Constants.Elevator.MAX_HEIGHT, Constants.Elevator.ALGAE_LOW_HEIGHT, Constants.Elevator.ALGAE_HIGH_HEIGHT};
        addRequirements(Elevator.getInstance());
        voltage = strength;
    }
    
    @Override
    public void initialize ()
    {  
    }

    public void checkForBottom ()
    {
        boolean changed = previousPosition != ElevatorSim.position;
        previousPosition=ElevatorSim.position;
        ElevatorSim.voltageApplied = (voltage)*(ElevatorSim.position);
        if (!changed && ElevatorSim.voltageApplied < 0.0)
        {
            System.out.println("Elevator Position Is Being Set to 0 because position is " + ElevatorSim.position + " and voltage is " + ElevatorSim.voltageApplied);
            ElevatorSim.position = 0.0;
            ElevatorSim.voltageApplied = 0.0;

        }
    }

    @Override
    public void execute ()
    {
        
        //checkForBottom();
        // System.out.println("Finished Zeroing Out");
        // System.out.println("Elevator Position: " + ElevatorSim.position);
        // System.out.println("Elevator Voltage: " + ElevatorSim.voltageApplied);
        ElevatorSim.voltageApplied = 5.0*(levelHeights[level] - ElevatorSim.position);

        // ElevatorSim.voltageApplied = Math.min(1.0,Math.abs((levelHeights[level] - ElevatorSim.position)*5.0))*Math.signum(levelHeights[level] - ElevatorSim.position);
    
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