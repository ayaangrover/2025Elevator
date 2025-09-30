package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

/**
 * 
 * Stop!
 * 
 * You shouldn't need to reference this file.
 * 
 * So you shouldn't have it open.
 * 
 * 
 * 
 * Here's a poem to fill up the space:
 * 
 *        In my beginning is my end. In succession
Houses rise and fall, crumble, are extended,
Are removed, destroyed, restored, or in their place
Is an open field, or a factory, or a by-pass.
Old stone to new building, old timber to new fires,
Old fires to ashes, and ashes to the earth
Which is already flesh, fur and faeces,
Bone of man and beast, cornstalk and leaf.
Houses live and die: there is a time for building
And a time for living and for generation
And a time for the wind to break the loosened pane
And to shake the wainscot where the field-mouse trots
And to shake the tattered arras woven with a silent motto.

       In my beginning is my end. Now the light falls
Across the open field,, leaving the deep lane
Shuttered with branches, dark in the afternoon,
Where you lean against a bank while a van passes,
And the deep lane insists on the direction
Into the village, in the elctric heat
Hypnotised. In a warm haze the sultry light
Is absorbed, not refracted, by grey stone.
The dahlias sleep in the empty silence.
Wait for the early owl.

                                             In that open field
If you do not come too close, if you do not come too close,
On a summer midnight, you can hear the music
Of the weak pipe and the little drum
And see them dancing around the bonfire
the association of man and woman
In daunsinge, signifying matrimonie˜
A dignified and commodious sacrament.
Two and two, necessarye coniunction,
Holding eche other by the hand or the arm
Whiche betokeneth concorde. Round and round the fire
Leaping through the flames, or joined in circles,
Rustically solemn or in rustic laughter
Lifting heavy feet in clumsy shoes,
Earth feet, loam feet, lifted in country mirth
Mirth of those long since under earth
Nourishing the corn. Keeping time,
Keeping the rhythm in their dancing
As in their living in the living seasons
The time of the seasons and the constellations
The time of milking and the time of harvest
The time of the coupling of man and woman
And that of beasts. Feet rising and falling.
Eating and drinking. Dung and death.

       Dawn points, and another day
Prepares for heat and silence. Out at sea the dawn wind
Wrinkles and slides. I am here
Or there, or elsewhere. In my beginning.
 * 
 */
public class ElevatorSim 
{
    public class Constants
    {
        public class Elevator
        {
            public static final double ELEVATOR_HEIGHT_M = 1.0;

            public static final double KG = 0.5;
            public static final double KS = 0.0;
            public static final double KV = 1.0;
            public static final double KA = 0.5;

            public static final double MAX_VELOCITY = 1.5;
            public static final double MIN_VELOCITY = -2.5;

            public static final int IMAGE_HEIGHT = 852;
            public static final int IMAGE_WIDTH = 469;

            public static final double CARRIAGE_INRADIUS = 0.762;

            public static final int X_OFFSET_CENTER = IMAGE_WIDTH - 180;

            public static final int Y_MIN_HEIGHT = 751;
            public static final int Y_MAX_HEIGHT = 75;
            public static final double SCALING_FACTOR = (double) (Y_MAX_HEIGHT - Y_MIN_HEIGHT) / (double) IMAGE_HEIGHT;
            public static final double SECOND_SCALING_FACTOR = 10.0;
        }
    }
    public static double voltageApplied;
    public static double position;
    public static double wear = 0.0;

    

    private static long timeLastIteration;
    private static double realPosition;
    private static double velocity;

    /**
     * In case you've forgotten, don't call this method.
     */
    public static Pose2d getAsPose()
    {
        double x = (double)Constants.Elevator.X_OFFSET_CENTER / (double)Constants.Elevator.IMAGE_HEIGHT;
        double y = (double)Constants.Elevator.Y_MIN_HEIGHT / (double)Constants.Elevator.IMAGE_HEIGHT
                    - Constants.Elevator.CARRIAGE_INRADIUS / 10.0 / 2.0
                    + (Constants.Elevator.SCALING_FACTOR * realPosition);
        return new Pose2d(new Translation2d(
            (double)x * Constants.Elevator.SECOND_SCALING_FACTOR, 
            (double)y * Constants.Elevator.SECOND_SCALING_FACTOR), new Rotation2d(-Math.PI / 2.0));
    }

    public static void update ()
    {
        double dt = (System.currentTimeMillis() - timeLastIteration) / 1000.0;

        realPosition += velocity * dt;
        position += velocity * dt;
        double rand = Math.random();
        if (rand < 0.1)
        {
            realPosition -= 0.01;
        } else if (rand >= 0.1 && rand < 0.2)
        {
            realPosition += 0.005;
        }
        if (realPosition >= 1.0) 
        {
            wear += 0.0005 * (realPosition - 1.0);
            position -= velocity * dt;
            realPosition = 1.0;
            velocity = 0.0;
        }
        else if (realPosition < 0.0)
        {
            position -= velocity * dt;
            realPosition = 0.0;
            velocity = 0.0;
        }

        double adjustedVoltage = voltageApplied - Constants.Elevator.KG;
        adjustedVoltage -= Constants.Elevator.KS * (velocity > 0.0 ? 1.0 : -1.0);
        adjustedVoltage -= Constants.Elevator.KV * velocity;
        velocity += adjustedVoltage / Constants.Elevator.KA;
        if (velocity > Constants.Elevator.MAX_VELOCITY) 
        {
            wear += 0.001 * (velocity - Constants.Elevator.MAX_VELOCITY); // gears skipping
            velocity = Constants.Elevator.MAX_VELOCITY;
        } else if (velocity < Constants.Elevator.MIN_VELOCITY)
        {
            wear += 0.001 * (Constants.Elevator.MIN_VELOCITY - velocity); // gears skipping
            velocity = Constants.Elevator.MIN_VELOCITY;
        }
        if (voltageApplied < 0.0 && realPosition == 0.0)
        {
            wear += 0.0001 * (-voltageApplied);
        }
        if (adjustedVoltage / Constants.Elevator.KA > 0.1)
        {
            wear += 0.0001 * (adjustedVoltage / Constants.Elevator.KA - 0.1);
        }

        timeLastIteration = System.currentTimeMillis();
    }
}
