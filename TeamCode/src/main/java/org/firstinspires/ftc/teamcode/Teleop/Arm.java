/*
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

public class Arm {



    if (gamepad1.y) {
        // Code to move into stowed position
        // Lock
        robotArm.stow();
    }

        if (gamepad1.b) {
        // Code to move into placing position
        // Unlock
        robotArm.place();
    }

        if (gamepad1.x) {
        // Code for moving into picking up position
        // Unlock
        robotArm.pick();
    }

// Code for the extension and retraction of the telescoping arm
        while (gamepad1.right_trigger == 1) {
        ExtendMotor.setPower(1);
    }

        while (gamepad1.left_trigger == 1) {
        ExtendMotor.setPower(-1);
    }


}
