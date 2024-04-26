package org.firstinspires.ftc.teamcode.Teleop;
//Imports for the motors

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Arm {
    private DcMotor armLength;
    private DcMotor armShoulder;
    public void initArm() {
        armLength = hardwareMap.get(DcMotor.class, "LengthMotor");
        armShoulder = hardwareMap.get(DcMotor.class, "ShoulderAngle");
    }

    //Add in manual control of the shoulder angle using the Dpad on the controller
    public void controlArm(String state, Gamepad gamepad1) throws InterruptedException {
        if (gamepad1.left_trigger >= 0){
            armLength.setPower(1);
            sleep(100);
            armLength.setPower(0);
        } else if (gamepad1.right_trigger >= 0) {
            armLength.setPower(-1);
            sleep(100);
            armLength.setPower(0);
        } else {

            int pickup = 40;
            int pickup_length = 10;
            int stowed = 90;
            int stowed_length = 0;
            int place = 200;
            int place_length = 15;

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
    }

    public void setArmAngle(int angle){
        //use a rev through bore encoder to set the arm to the given angle that is necessary
    }
    public void setArmLength(int length){
        // use a rev through bore encoder to set the length of the arm to the given length
    }
}

/*
Imports
- Arm angle = 60 degrees - Arm Class angle input function
- Extend to set position & let RT and LT extend and retract the arm
- Arm Class set position function
- Arm angle = 90 degrees - Arm Class angle input function
- Fully retracted - Arm Class set position function
- Arm angle = 230 degrees - Arm Class angle input function
Extend arm to set position & let RT and LT extend and retract the arm - Arm Class
Extending Arm - Arm class button input function
Retracting Arm - Arm class button input function
Open Claw - Claw class button input function
Close Claw - Claw class button input function
*/
