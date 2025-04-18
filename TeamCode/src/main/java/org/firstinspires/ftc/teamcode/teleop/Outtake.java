package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Outtake {

    //States
    private int arm_state = 0;

    //Motors
    private DcMotor Lift_one;
    private DcMotor Lift_two;
    private int MV_one;
    private int MV_two;
    

    // V4B servos
    private Servo VBar_Rotation;
    private float VBar_Rotation_Angle = 0.5f;
    private Servo Claw_Rotation;
    private float Claw_Rotation_Angle = 0.645f;

    // Claw
    private Servo Claw;
    private float Claw_Angle = 0.7f;

    // Hardware Map and Telemetry
    private HardwareMap hwMap;
    private Telemetry telemetry;

    // Constructor
    public Outtake(HardwareMap hardMap, Telemetry telemetry) {
        this.hwMap = hardMap;
        this.telemetry = telemetry;
    }

    // Names given to the Servos and Motors on the CH and EH
    public void initOuttake() {
        // Servos
        VBar_Rotation = hwMap.get(Servo.class, "VBR");
        Claw_Rotation = hwMap.get(Servo.class, "Claw_R");
        Claw = hwMap.get(Servo.class, "claw");

        // Motors
        Lift_one = hwMap.dcMotor.get("liftright");
        Lift_two = hwMap.dcMotor.get("liftleft");
        Lift_one.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift_one.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift_two.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift_two.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void Update_Outtake(Gamepad gamepad) {
        // Clamp angles to their respective ranges
        Claw_Angle = (float) Math.max(0.4, Math.min(0.7, Claw_Angle));
        VBar_Rotation_Angle = (float) Math.max(0, Math.min(0.5, VBar_Rotation_Angle));
        Claw_Rotation_Angle = (float) Math.max(0, Math.min(1, Claw_Rotation_Angle));

        int encoderValue = Lift_one.getCurrentPosition();
        int encoderValue_two = Lift_two.getCurrentPosition();


        // Manual controls for the servos
        if (gamepad.left_trigger > 0.5) {
            VBar_Rotation_Angle -= 0.005F;
        }
        if (gamepad.right_trigger > 0.5) {
            VBar_Rotation_Angle += 0.005F;
        }

        if (gamepad.left_bumper) {
            Claw_Rotation_Angle -= 0.005F;
        }
        if (gamepad.right_bumper) {
            Claw_Rotation_Angle += 0.005F;
        }

        if (gamepad.dpad_left) {
            Claw_Angle -= 0.1F;
        }
        if (gamepad.dpad_right) {
            Claw_Angle += 0.1F;
        }

        if (gamepad.dpad_down && encoderValue < -10){
            Lift_two.setPower(-0.8);
            Lift_two.setPower(0.8);
        }else if (gamepad.dpad_up && encoderValue > -7400){
            Lift_two.setPower(0.8);
            Lift_two.setPower(-0.8);
        } else {
            Lift_two.setPower(0);
            Lift_one.setPower(0);
        }

        // Motor max = 7500
        //Motor Min 0
        if (gamepad.a) {
            arm_state = 1;
        }
        if (gamepad.x) {
            arm_state = 4;
        }

        switch (arm_state){
            case 1:
                Claw_Angle = 0.7f;
                VBar_Rotation_Angle = 0.5f;
                Claw_Rotation_Angle = 0.645f;

            case 2:
                if (encoderValue_two < -300) {
                    // Set target position
                    Lift_two.setTargetPosition(-500);
                    Lift_one.setTargetPosition(-500);

                    // Set the run mode to RUN_TO_POSITION
                    Lift_one.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Lift_two.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    // Set the power (must be positive)
                    Lift_one.setPower(-1);
                    Lift_two.setPower(1);

                    // Move to the next state
                    arm_state = 7;
                }
                break;

            case 7:
                // Check if motors have reached their target
                if (!Lift_one.isBusy() && !Lift_two.isBusy()) {
                    // Stop motors
                    Lift_one.setPower(0);
                    Lift_two.setPower(0);

                    // Change mode back to normal
                    Lift_one.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    Lift_two.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                    // Move to the next state or reset
                    arm_state = 3;
                }
                else {
                    Lift_two.setTargetPosition(-500);
                    Lift_one.setTargetPosition(-500);

                    // Set the power (must be positive)
                    Lift_one.setPower(-1);
                    Lift_two.setPower(1);

                }
                break;

            case 3:
                Claw_Angle = 0.4f;
                arm_state = 0;
                break;

            case 4:
                Claw_Angle = 0.7f;

                Lift_two.setTargetPosition(-5300);
                Lift_one.setTargetPosition(-5300);

                Lift_one.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Lift_two.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                Lift_one.setPower(-1);
                Lift_two.setPower(1);

                // Move to the next state so the OpMode doesn't block
                arm_state = 6;
                break;

            case 6:
                // Check if motors are still moving
                if (!Lift_one.isBusy() && !Lift_two.isBusy()) {
                    Lift_one.setPower(0);
                    Lift_two.setPower(0);

                    Lift_one.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    Lift_two.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                    // Move to the next state
                    arm_state = 5;
                }
                else{
                    Lift_two.setTargetPosition(-5300);
                    Lift_one.setTargetPosition(-5300);
                    Lift_one.setPower(-1);
                    Lift_two.setPower(1);
                }
                break;


            case 5:
                VBar_Rotation_Angle = 0.115f;
                Claw_Rotation_Angle = 0.88f;
                Claw_Angle = 0.7f;
                arm_state = 0;
                break;

        }

        // Set servo positions
        VBar_Rotation.setPosition(VBar_Rotation_Angle);
        Claw_Rotation.setPosition(Claw_Rotation_Angle);
        Claw.setPosition(Claw_Angle);

        // Telemetry output
        telemetry.addData("VBar Rotation Angle", VBar_Rotation_Angle);
        telemetry.addData("Claw Rotation Angle", Claw_Rotation_Angle);
        telemetry.addData("Claw Angle", Claw_Angle);

        telemetry.addData("Arm_one", encoderValue);
        telemetry.addData("Arm_two", encoderValue_two);

        telemetry.addData("Arm State", arm_state);

        telemetry.update();
    }

}
