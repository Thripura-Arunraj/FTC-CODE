package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MyFirstOpMode extends LinearOpMode {

    static final int MOTOR_TICK_COUNTS = 1120;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor left;
        DcMotor right;


        telemetry.addData( "Status", "initializing");
        telemetry.update();

        left = hardwareMap.get(DcMotor.class, "motorLeft");
        right = hardwareMap.get(DcMotor.class, "motorRight");

        //Drive forward 18 inches
        waitForStart();
        while (opModeIsActive())
        {
            telemetry.addData( "Status", "running");
            telemetry.update();
            left.setDirection(DcMotorSimple.Direction.FORWARD);

            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

           // How many turns do you need to get to 18 inches
            double circumference = 3.14*2.938;//Pi x diameter of wheel
            double rotationsNeeded = 18/circumference;
            int encodeDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS);

            //set target position
            left.setTargetPosition(encodeDrivingTarget);
            right.setTargetPosition(encodeDrivingTarget);

            // set power
            left.setPower(.5);
            right.setPower(.5);

            //set the motors to RUN_TO_POSITION
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            while (left.isBusy() || right.isBusy()) {
                telemetry.addData("Path", "Driving 18 inches");
                telemetry.update();

            }


        //Stop all motion
            left.setPower(0);
            right.setPower(0);
            
        }
    }
}
