package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Movement;

@TeleOp(name = "Auto3")
public class Auto3 extends LinearOpMode {
    // Declare the movement object
    private Movement movement;
    // Override the runOpMode() method to define the robot's behavior
    @Override
    public void runOpMode()
    {
        // Initialize the movement object
        movement = new Movement(hardwareMap);

        // Wait for the start button to be pressed
        waitForStart();

        // Move the robot forward 1 foot
        movement.moveForward();

        // Move the robot backwards 1 foot
        movement.moveBackward();
    }
}
