package org.firstinspires.ftc.teamcode.Teleop;/*
Imports

- Arm angle = 60 degrees - Arm Class angle input function
- Extend to set position & let RT and LT extend and retract the arm - Arm Class set position function


- Arm angle = 90 degrees - Arm Class angle input function
- Fully retracted - Arm Class set position function


- Arm angle = 230 degrees - Arm Class angle input function
Extend arm to set position & let RT and LT extend and retract the arm - Arm Class




Extending Arm - Arm class button input function
Retracting Arm - Arm class button input function
Open Claw - Claw class button input function
Close Claw - Claw class button input function
*/


//Imports for the motors

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private RevArmAngleMoter armAngle;
    private RobotArm robotArm;
    private ExtendMotor extendMotor;
    private Gamepad gamepad1;

    private final int pickup=40;
    private final int pickup_length=10;
    private final int stowed=90;
    private final int stowed_length=0;
    private final int place=200;
    private final int place_length=15;
    private DcMotor armLength;
    private DcMotor armShoulder;
    public void initArm() {
        armLength = hardwareMap.get(DcMotor.class, "LengthMotor");
        armShoulder = hardwareMap.get(DcMotor.class, "ShoulderAngle");
    }

    public void controlArm(String state, gamepad1) {
        if (gamepad1.left_trigger){
            armLength.setPower(1);
            wait(0.1);
            armLength.setPower(0);
        } else if (gamepad1.right_trigger) {
            armLength.setPower(-1);
            wait(0.1);
            armLength.setPower(0);
        } else {
            switch(state){
                case"pickup":
                    setArmAngle(pickup);
                    setArmLength(pickup_length);
                case"stowed":
                    setArmAngle(stowed);
                    setArmLength(stowed_length);
                case"place":
                    setArmAngle(place);
                    setArmLength(place_length);
            }
        }
        if (gamepad1.y) {
            armAngle.setAngle(RevArmAngleMoter.ArmAngle.degrees90);
            robotArm.stow();
        }

        if (gamepad1.b) {
            armAngle.setAngle(RevArmAngleMoter.ArmAngle.degrees230);
            robotArm.place();
        }

        if (gamepad1.x) {
            armAngle.setAngle(RevArmAngleMoter.ArmAngle.degrees60);
            robotArm.pick();
        }

        // Code for the extension and retraction of the telescoping arm
        if (gamepad1.right_trigger > 0) {
            extendMotor.setPower(1);
            robotArm.extend();
        }

        if (gamepad1.left_trigger > 0) {
            extendMotor.setPower(-1);
            robotArm.retract();
        }
    }
}
