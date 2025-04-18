package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {
    private Servo Extend_one;
    private Servo Extend_two;
    private Servo flip;
    private CRServo intake;
    private CRServo hopper;

    private float extend_angle = 1f;
    private float rotate_angle = 1f;

    private HardwareMap hwMap;
    private ElapsedTime automationTimer = new ElapsedTime();

    private int automationState = 0;  // State variable for automation

    public Intake(HardwareMap hardMap) {
        this.hwMap = hardMap;
    }

    public void initintake() {
        Extend_one = hwMap.get(Servo.class, "servo1");
        Extend_two = hwMap.get(Servo.class, "servo2");
        flip = hwMap.get(Servo.class, "servo3");
        intake = hwMap.get(CRServo.class, "servo4");
        hopper = hwMap.get(CRServo.class, "hop");
    }

    public void intake_servos(Gamepad gamepad) {
        // Manual Controls
        if (gamepad.left_trigger > 0.5) {
            extend_angle += 0.01;
        } else if (gamepad.right_trigger > 0.5) {
            extend_angle -= 0.01;
        }

        if (gamepad.left_bumper) {
            rotate_angle += 0.01;
        } else if (gamepad.right_bumper) {
            rotate_angle -= 0.01;
        }

        extend_angle = Math.max(0.82f, Math.min(1, extend_angle));
        rotate_angle = Math.max(0, Math.min(1, rotate_angle));

        if (gamepad.a && gamepad.b) {
            intake.setPower(1.0);
            hopper.setPower(1);
            automationState = 0;
        } else if (gamepad.b) {
            intake.setPower(-1.0);
            automationState = 0;
            hopper.setPower(-1);
        } else if (automationState == 0) {
            intake.setPower(0);
        }

        if (gamepad.dpad_up) {
            hopper.setPower(-1);
            automationState = 0;
        } else if (gamepad.dpad_down) {
            hopper.setPower(1);
            automationState = 0;
        } else if (automationState == 0) {
            hopper.setPower(0);
        }

        // AUTOMATION: Start Sequence When "Y" is Pressed
        if (gamepad.y) {
            automationState = 1;
            automationTimer.reset();
        }

        if (gamepad.x) {
            automationState = 6;
            //automationTimer.reset();
        }

        System.out.println(automationState);
        System.out.println(automationTimer);
        // AUTOMATION STATE MACHINE
        switch (automationState) {

            case 1: // Step 1: Retract & Rotate
                extend_angle = 1F;
                rotate_angle = 1F;
                intake.setPower(-0.5);
                automationState = 2;
                automationTimer.reset();

                Extend_one.setPosition(extend_angle);
                Extend_two.setPosition(1 - extend_angle);
                flip.setPosition(rotate_angle);

                break;

            case 2: // Step 2: Wait for 500ms, then stop intake
                if (automationTimer.milliseconds() > 500) {
                    intake.setPower(0);
                    hopper.setPower(-1);
                    automationState = 3;
                    automationTimer.reset();

                    Extend_one.setPosition(extend_angle);
                    Extend_two.setPosition(1 - extend_angle);
                    flip.setPosition(rotate_angle);

                }
                break;

            case 3: // Step 3: Wait for 1000ms, then start intake forward
                if (automationTimer.milliseconds() > 1000) {
                    intake.setPower(1);
                    automationState = 4;
                    automationTimer.reset();

                    Extend_one.setPosition(extend_angle);
                    Extend_two.setPosition(1 - extend_angle);
                    flip.setPosition(rotate_angle);

                }
                break;

            case 4: // Step 4: Wait for 1000ms, then retract servos
                if (automationTimer.milliseconds() > 1000) {
                    Extend_one.setPosition(extend_angle);
                    Extend_two.setPosition(1 - extend_angle);
                    automationState = 5;
                    automationTimer.reset();

                    Extend_one.setPosition(extend_angle);
                    Extend_two.setPosition(1 - extend_angle);
                    flip.setPosition(rotate_angle);

                }
                break;

            case 5: // Step 5: Wait for 6000ms, then stop hopper
                if (automationTimer.milliseconds() > 6000) {
                    hopper.setPower(0);
                    automationState = 0; // Reset state

                    Extend_one.setPosition(extend_angle);
                    Extend_two.setPosition(1 - extend_angle);
                    flip.setPosition(rotate_angle);

                }
                break;

            case 6:
                extend_angle = 0.82F; //Extend Angle
                rotate_angle = 0.5F; // rotate bucket
                intake.setPower(-1.0); // rotation speed

                Extend_one.setPosition(extend_angle);
                Extend_two.setPosition(1 - extend_angle);
                flip.setPosition(rotate_angle);
                break;
        }

        // Update Servo Positions
        Extend_one.setPosition(extend_angle);
        Extend_two.setPosition(1 - extend_angle);
        flip.setPosition(rotate_angle);
    }
}
