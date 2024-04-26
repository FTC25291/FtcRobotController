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
import com.qualcomm.robotcore.hardware.Gamepad;

public class fdfdfd {
    private RevArmAngleMoter armAngle;
    private RobotArm robotArm;
    private ExtendMotor extendMotor;
    private Gamepad gamepad1;

    private DcMotor armLength;
    private DcMotor sholderMotor;

    public void initArm(){
        armLength = hardwareMap.get(DcMotor, "armlength");
    }


    //enum for this look this up
    public void controlArm() {
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
