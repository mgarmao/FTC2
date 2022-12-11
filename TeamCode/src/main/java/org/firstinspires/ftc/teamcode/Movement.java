package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Class for the robot's movement
public class Movement {
    // Declare the motors
    private final DcMotor frontLeftMotor;
    private final DcMotor backLeftMotor;
    private final DcMotor frontRightMotor;
    private final DcMotor backRightMotor;

    // Distance per revolution of the robot's wheels
    private static final double DISTANCE_PER_REVOLUTION = 3 * Math.PI;
    private static final int ticksPerRevolution = 420;
    // Distance the robot should move
    private static final double DISTANCE = 1.0; // 1 foot

    // Constructor for the movement class
    public Movement(HardwareMap hardwareMap) {
        // Initialize the motors
        frontLeftMotor = hardwareMap.dcMotor.get("motor_left");
        backLeftMotor = hardwareMap.dcMotor.get("motor_left");
        frontRightMotor = hardwareMap.dcMotor.get("motor_left");
        backRightMotor = hardwareMap.dcMotor.get("motor_right");

        // Set the direction of the motors
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    // Method to move the robot forward 1 foot
    public void moveForward() {
        // Calculate the number of revolutions the wheels need to turn
        int revolutions = (int) ((DISTANCE/DISTANCE_PER_REVOLUTION)*ticksPerRevolution);

        // Set the target position for the motors
        frontLeftMotor.setTargetPosition(revolutions);
        backLeftMotor.setTargetPosition(revolutions);
        frontRightMotor.setTargetPosition(revolutions);
        backRightMotor.setTargetPosition(revolutions);

        // Set the motors to run to the target position
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the speed of the motors
        frontLeftMotor.setPower(-0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backRightMotor.setPower(- 0.5);

        // Wait for the motors to stop moving
        while (frontLeftMotor.isBusy() && frontRightMotor.isBusy()) {

        }

        // stop applying power
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    // Method to move the robot backwards 1 foot
    public void moveBackward() {
        // Calculate the number of revolutions the wheels need to turn
        int revolutions = (int) ((DISTANCE / DISTANCE_PER_REVOLUTION)*420);

        // Set the target position for the motors
        frontLeftMotor.setTargetPosition(-revolutions);
        backLeftMotor.setTargetPosition(-revolutions);
        frontRightMotor.setTargetPosition(-revolutions);
        backRightMotor.setTargetPosition(-revolutions);

        // Set the motors to run to the target position
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the speed of the motors
        frontLeftMotor.setPower(-0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backRightMotor.setPower(- 0.5);

        // Wait for the motors to stop moving
        while (frontLeftMotor.isBusy() && frontRightMotor.isBusy()) {

        }

        // stop applying power
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}