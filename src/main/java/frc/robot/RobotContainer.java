// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ElevatorManual;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;

/**
 * Add all command bindings here or in ElevatorManual.java
 */
public class RobotContainer 
{
	private static RobotContainer instance;

	public XboxController controller = new XboxController(0);
	public RobotContainer() 
	{
		configureBindings();
		Elevator.getInstance().setDefaultCommand(new ElevatorManual());
	}

	private void configureBindings() 
	{
	}

	public Command getAutonomousCommand() 
	{
		return Commands.print("No autonomous command configured");
	}

	public static RobotContainer getInstance ()
	{
		if (instance == null) instance = new RobotContainer();
		return instance;
	}
}
