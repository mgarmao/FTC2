package org.firstinspires.ftc.teamcode;
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
    private DcMotor backLeft  = null;
    private DcMotor backRight = null;
    private DcMotor elevator  = null;
    Servo   servo;

    int ServoPosition = 1;
    int elevatorZero=0;
    @Override
    public void init() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        backLeft   = hardwareMap.get(DcMotor.class, "motor1");
        backRight  = hardwareMap.get(DcMotor.class, "motor2");
        elevator   = hardwareMap.get(DcMotor.class, "elevator");
        elevatorZero =elevator.getCurrentPosition();
        servo = hardwareMap.get(Servo.class, "Servo1");

        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        double drive  = gamepad1.left_stick_y;
        drive=drive*-1;
        double twist  = gamepad1.right_stick_x;


        if(gamepad2.a){
            ServoPosition=1;
            servo.setPosition(0.2);
        }

        if(gamepad2.b){
            ServoPosition=0;
            servo.setPosition(-0.7);
        }



        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        double[] speeds = {
                (drive - twist),
                (drive + twist),
        };


        backLeft.setPower(speeds[0]);
        backRight.setPower(speeds[1]);


        // 300 units per rotation
        float elavatorPower=(gamepad2.right_trigger-gamepad2.left_trigger);
        elevator.setPower(elavatorPower);

        telemetry.addData("Gampad1 Left Y",gamepad1.left_stick_y);
        telemetry.addData("Gampad2 Left X",gamepad2.left_stick_x);
        telemetry.update();
    }
}

