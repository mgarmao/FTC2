package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="STA Drive", group="Iterative Opmode")
public class STA extends OpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize four DcMotors.
    private DcMotor frontLeft  = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft  = null;
    private DcMotor backRight = null;
    private DcMotor elevator  = null;
    Servo   servo0;
    Servo   servo1;

    int ServoPosition = 1;
    int elevatorZero=0;
    @Override
    public void init() {
        frontLeft   = hardwareMap.get(DcMotor.class, "motor1");
        frontRight  = hardwareMap.get(DcMotor.class, "motor2");
        backRight  = hardwareMap.get(DcMotor.class, "motor3");
        backRight  = hardwareMap.get(DcMotor.class, "motor4");

        servo0 = hardwareMap.get(Servo.class, "Servo0");
        servo1 = hardwareMap.get(Servo.class, "Servo1");

        elevator   = hardwareMap.get(DcMotor.class, "elevator");
        elevatorZero =elevator.getCurrentPosition();

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        //Drivetrain Section
        double drive;
        double strafe;
        double twist;

        if(gamepad1.right_bumper){
            drive = gamepad1.left_stick_x;
            strafe  = gamepad1.left_stick_y;
            twist  = gamepad1.right_stick_x;
        }
        else{
            drive = gamepad1.left_stick_x*0.5;
            strafe  = gamepad1.left_stick_y*0.5;
            twist  = gamepad1.right_stick_x*0.5;
        }

        double[] speeds = {
                (drive + strafe + twist),
                (drive - strafe - twist),
                (drive - strafe +twist),
                (drive + strafe - twist)
        };

        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        frontLeft.setPower(speeds[0]);
        frontRight.setPower(speeds[1]);
        backLeft.setPower(speeds[2]);
        backRight.setPower(speeds[3]);

        //Grabber Section
        if(gamepad1.y){
            servo0.setPosition(0.25);
            servo1.setPosition(-0.30);
        }

        if(gamepad1.left_bumper){
            ServoPosition=0;
            servo0.setPosition(0.08);
            servo1.setPosition(-0.17);
        }

        //Elevator Section
        float elavatorPower=(gamepad2.right_trigger-gamepad2.left_trigger);
        elevator.setPower(elavatorPower);

        //Telemetry Section
        telemetry.addData("Gampad1 Left Y",gamepad1.left_stick_y);
        telemetry.addData("Gampad2 Left X",gamepad2.left_stick_x);
        telemetry.update();
    }
}

