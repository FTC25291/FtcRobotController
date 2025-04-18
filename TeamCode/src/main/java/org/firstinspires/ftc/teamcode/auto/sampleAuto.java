package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;

@Autonomous
public class sampleAuto extends LinearOpMode{
    public DcMotor frontLeftMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;

    //Motors
    private DcMotor Lift_one;
    private DcMotor Lift_two;
    private int MV_one;
    private int MV_two;


    // V4B servos
    private Servo VBar_Rotation;
    private float VBar_Rotation_Angle = 0.5f;
    private Servo Claw_Rotation;
    private float Claw_Rotation_Angle = 0.87f;

    // Claw
    private Servo Claw;
    private float Claw_Angle = 0.7f;

    public int AutoStage;

    @Override
    public void runOpMode() throws InterruptedException {

        AutoStage = 0;

        HardwareMap hwMap = hardwareMap;

        backLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        VBar_Rotation = hwMap.get(Servo.class, "VBR");
        Claw_Rotation = hwMap.get(Servo.class, "Claw_R");
        Claw = hwMap.get(Servo.class, "claw");

        // Motors
        Lift_one = hwMap.dcMotor.get("liftright");
        Lift_two = hwMap.dcMotor.get("liftleft");
        Lift_one.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift_two.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        waitForStart();

        Claw_Angle = (float) Math.max(0.4, Math.min(0.7, Claw_Angle));
        VBar_Rotation_Angle = (float) Math.max(0, Math.min(0.5, VBar_Rotation_Angle));
        Claw_Rotation_Angle = (float) Math.max(0, Math.min(1, Claw_Rotation_Angle));

        int encoderValue = Lift_one.getCurrentPosition();
        int encoderValue_two = Lift_two.getCurrentPosition();



        Claw_Angle = 0.7f;

        VBar_Rotation.setPosition(VBar_Rotation_Angle);
        Claw_Rotation.setPosition(Claw_Rotation_Angle);
        Claw.setPosition(Claw_Angle);

        if (opModeIsActive()){
            sleep(1000);
        }

        Lift_two.setTargetPosition(-5500);
        Lift_one.setTargetPosition(-5500);

        Lift_one.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Lift_two.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Lift_one.setPower(-1);
        Lift_two.setPower(1);

        // Check if motors are still moving
        while (Lift_one.isBusy() && Lift_two.isBusy() && opModeIsActive()) {
            sleep(1);

        }

        Lift_one.setPower(0);
        Lift_two.setPower(0);

        if (opModeIsActive()){
            sleep(100);
        }

        VBar_Rotation_Angle = 0.115f;
        Claw_Rotation_Angle = 0.88f;
        Claw_Angle = 0.7f;

        VBar_Rotation.setPosition(VBar_Rotation_Angle);
        Claw_Rotation.setPosition(Claw_Rotation_Angle);
        Claw.setPosition(Claw_Angle);

        if (opModeIsActive()){
            sleep(500);
        }

        // Drive forward
        frontRightMotor.setPower(-0.8);
        frontLeftMotor.setPower(-0.8);
        backLeftMotor.setPower(-0.8);
        backRightMotor.setPower(-0.8);

        sleep(380);

        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        backRightMotor.setPower(0.5);
        backLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(0.5);
        frontLeftMotor.setPower(-0.5);

        sleep(50);

        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        if (opModeIsActive()){
            sleep(700);
        }

        Claw_Angle = 0.4f;
        Claw.setPosition(Claw_Angle);

        if (opModeIsActive()){
            sleep(700);
        }

        backRightMotor.setPower(-0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(-0.5);
        frontLeftMotor.setPower(0.5);

        sleep(50);

        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        if (opModeIsActive()){
            sleep(200);
        }

        frontRightMotor.setPower(0.8);
        frontLeftMotor.setPower(0.8);
        backLeftMotor.setPower(0.8);
        backRightMotor.setPower(0.8);

        sleep(350);

        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        if (opModeIsActive()){
            sleep(700);
        }

        Claw_Angle = 0.7f;
        VBar_Rotation_Angle = 0.5f;
        Claw_Rotation_Angle = 0.645f;

        VBar_Rotation.setPosition(VBar_Rotation_Angle);
        Claw_Rotation.setPosition(Claw_Rotation_Angle);
        Claw.setPosition(Claw_Angle);

        if (opModeIsActive()){
            sleep(60);
        }

        // Set target position
        Lift_two.setTargetPosition(-500);
        Lift_one.setTargetPosition(-500);

        Lift_one.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Lift_two.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the power (must be positive)
        Lift_one.setPower(-1);
        Lift_two.setPower(1);


        // Check if motors have reached their target
        while (Lift_one.isBusy() && Lift_two.isBusy() && opModeIsActive()) {

        }

        Lift_one.setPower(0);
        Lift_two.setPower(0);

        if (opModeIsActive()){
            sleep(60);
        }

        // Set servo positions
        VBar_Rotation.setPosition(VBar_Rotation_Angle);
        Claw_Rotation.setPosition(Claw_Rotation_Angle);
        Claw.setPosition(Claw_Angle);
        

    }
}