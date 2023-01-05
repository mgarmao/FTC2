package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Movement;

@Autonomous(name = "Auto3")
public class Auto3 extends LinearOpMode {
    // Override the runOpMode() method to define the robot's behavior
    @Override
    public void runOpMode()
    {
        // Initialize and declare the movement object
        Movement movement = new Movement(hardwareMap);

        // Wait for the start button to be pressed
        waitForStart();

        // Move robot forward 1 foot
        movement.moveForward();
        //Stays there for 1.5 seconds
        sleep(1500);
        // Move backwards 1 foot
        movement.moveBackward();
    }
}
