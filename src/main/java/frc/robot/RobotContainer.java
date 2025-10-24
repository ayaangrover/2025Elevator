 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ElevatorGoToLevel;
import frc.robot.commands.ElevatorZero;
import frc.robot.commands.die;
import frc.robot.commands.ElevatorButton;
import frc.robot.commands.ElevatorManual;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorSim;

/**
 * Add all command bindings here or in ElevatorManual.java
 */
public class RobotContainer 
{
    private static RobotContainer instance;

    public CommandXboxController controller = new CommandXboxController(0);
    public CommandXboxController controller2 = new CommandXboxController(1);
    public RobotContainer() 
    {
        configureBindings();
        Elevator.getInstance().setDefaultCommand(new ElevatorManual());
    }

    private void configureBindings() 
    {
        controller.button(1).onTrue(new ElevatorGoToLevel(1, -9.0));
        controller.button(2).onTrue(new ElevatorGoToLevel(2,-9.0));
        controller.button(3).onTrue(new ElevatorGoToLevel(3, -9.0));
        controller.button(4).onTrue(new ElevatorGoToLevel(4, -9.0));
        controller.button(5).onTrue(new ElevatorGoToLevel(5, -9.0));
        controller.button(6).onTrue(new ElevatorGoToLevel(6, -9.0));
        controller.button(10).onTrue(new ElevatorZero());
        controller.button(7).whileTrue(new ElevatorButton(true));
        controller.button(8).whileTrue(new ElevatorButton(false));
        controller.button(9).onTrue(new ElevatorGoToLevel(1, -0.0));
        controller2.button(1).onTrue(new die());
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

