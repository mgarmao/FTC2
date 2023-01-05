package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Mecanum Drive", group="Iterative Opmode")
public class Mecanum extends OpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize four DcMotors.
    private DcMotor front_left  = null;
    private DcMotor front_right = null;
    private DcMotor back_left   = null;
    private DcMotor back_right  = null;
    private DcMotor elevator  = null;
    Servo   servo;

    int ServoPosition = 1;
    int elevatorZero=0;
    @Override
    public void init() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        front_left   = hardwareMap.get(DcMotor.class, "FrontL0");
        front_right  = hardwareMap.get(DcMotor.class, "BackL2");
        back_left    = hardwareMap.get(DcMotor.class, "FrontR1");
        back_right   = hardwareMap.get(DcMotor.class, "BackR3");
        elevator = hardwareMap.get(DcMotor.class,"elevator");
        elevatorZero =elevator.getCurrentPosition();
        servo = hardwareMap.get(Servo.class, "ServoL");

        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);

        front_right.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
//        double drive  = -Math.pow(gamepad1.left_stick_x,5.0/3);
//        double strafe = Math.pow(gamepad1.left_stick_y,5.0/3);
//        double twist  = -Math.pow(gamepad1.right_stick_x,5.0/3);

        double drive  = -gamepad1.left_stick_x;
        double strafe = gamepad1.left_stick_y;
        double twist  = gamepad1.right_stick_x;


        if(gamepad1.a){
            ServoPosition=1;
            servo.setPosition(0.25);
        }

        if(gamepad1.b){
            ServoPosition=0;
            servo.setPosition(0.08);
        }



        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        double[] speeds = {
                (drive + strafe + twist),
                (drive - strafe - twist),
                (drive - strafe + twist),
                (drive + strafe - twist)
        };

        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        // If and only if the maximum is outside of the range we want it to be,
        // normalize all the other speeds based on the given speed value.
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        front_left.setPower(speeds[0]);
        front_right.setPower(speeds[1]);
        back_left.setPower(speeds[2]);
        back_right.setPower(speeds[3]);


        // 300 units per rotation
        float elavatorPower=(gamepad1.right_trigger-gamepad1.left_trigger);
        elevator.setPower(elavatorPower*0.50);
//        if(elevator.getCurrentPosition()<=800&&elevator.getCurrentPosition()>=0){
//            elevator.setPower(elavatorPower);
//        }
//        if(elevator.getCurrentPosition()>=800){
//            elevator.setPower(-gamepad1.left_trigger);
//        }
//        if(elevator.getCurrentPosition()<=0){
//            elevator.setPower(gamepad1.right_trigger);
//        }

        telemetry.addData("driveValure",drive);
        telemetry.addData("strafe",strafe);
        telemetry.addData("twist",twist);
        telemetry.addData("A Value:",gamepad1.a);
        telemetry.addData("B Value:",gamepad1.b);
        telemetry.addData("Commanded Servo Position:",ServoPosition);
        telemetry.addData("Servo Position:",servo.getPosition());
        telemetry.addData("Elevator:",elevator.getCurrentPosition());
        telemetry.update();
    }
}

