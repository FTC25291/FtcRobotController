package org.firstinspires.ftc.teamcode.Teleop;
//Imports for the motors

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmTemp {
    private DcMotor armShoulder;

    private HardwareMap hwMap;

    public ArmTemp(HardwareMap hardMap){
        this.hwMap = hardMap;
    }

    public void initArm() {
        armShoulder = hwMap.dcMotor.get("MotorPivot");
    }

    //Add in manual control of the shoulder angle using the Dpad on the controller
    public void controlArm(Gamepad gamepad) throws InterruptedException {
        if (gamepad.left_trigger == 1) {
            armShoulder.setPower(1);
            sleep(100);
            armShoulder.setPower(0);
        } else if (gamepad.right_trigger == 1) {
            armShoulder.setPower(-1);
            sleep(100);
            armShoulder.setPower(0);

        }else {
            armShoulder.setPower(0);
        }
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
