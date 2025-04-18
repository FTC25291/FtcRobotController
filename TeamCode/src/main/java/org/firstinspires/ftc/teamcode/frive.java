package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(group = "Primary")
public class frive extends LinearOpMode {

    public DcMotor frontLeftMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;


    @Override
    public void runOpMode() throws InterruptedException {

        HardwareMap hwMap = hardwareMap;

        backLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        backLeftMotor.setPower(1);
        backRightMotor.setPower(1);
        frontLeftMotor.setPower(1);
        frontRightMotor.setPower(1);

        sleep(100);

        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);

//specimen code
        // robot preloaded with one specimen
        //move robot to the bar
        //raise the slide then lower the slide to hook specimen to bar
        //Have a human player to place specimens in the net zone
        //robot will turn and go in the net zone
        //claw with open to get the specimen
        //repeat 
    }
}
