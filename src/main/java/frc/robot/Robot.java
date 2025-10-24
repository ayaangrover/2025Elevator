// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;

public class Robot extends TimedRobot 
{
	private Command autonomousCommand;


	public Robot() 
	{
		RobotContainer.getInstance();
		Elevator.getInstance();
		
	}

	@Override
	public void robotPeriodic() 
	{
		Telemetry.update();
		CommandScheduler.getInstance().run();
		if (ElevatorSim.wear > 1.0)
		{
			Telemetry.table.getStringTopic("You Are Dead").publish().set("Indeed");
			endCompetition();
		}
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void disabledExit() {}

	@Override
	public void autonomousInit() 
	{
		autonomousCommand = RobotContainer.getInstance().getAutonomousCommand();

		if (autonomousCommand != null) 
		{
			autonomousCommand.schedule();
		}
	}

	@Override
	public void autonomousPeriodic() {}

	@Override
	public void autonomousExit() {}

	@Override
	public void teleopInit() 
	{
		if (autonomousCommand != null) 
		{
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {}

	@Override
	public void teleopExit() {}

	@Override
	public void testInit() 
	{
		CommandScheduler.getInstance().cancelAll();
	}

	@Override
	public void testPeriodic() {}

	@Override
	public void testExit() {}
}
