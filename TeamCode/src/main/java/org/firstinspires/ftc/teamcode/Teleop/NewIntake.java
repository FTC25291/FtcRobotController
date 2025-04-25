package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//Import stuff ^

public class Intake {
    public DcMotor intakeMotor;
    private Servo Extend_one;
    private Servo Extend_two;
    private Servo screw;

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
        screw = hwMap.get(Servo.class, "servo3");
        intakeMotor = hwMap.dcMotor.get("intakeMotor");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void intake_servos(Gamepad gamepad) {
        // Manual Controls
        if (gamepad.left_trigger > 0.5) {
            extend_angle += 0.01;
        } else if (gamepad.right_trigger > 0.5) {
            extend_angle -= 0.01;
        }
        extend_angle = Math.max(0.82f, Math.min(1, extend_angle));

        if (gamepad.x) {
            intakeMotor.setPower(1);
        }
        if (gamepad.a) {
            screw.setAngle(45);
        }
    }
}
