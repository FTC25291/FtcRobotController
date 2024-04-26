package org.firstinspires.ftc.teamcode.Teleop;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
        // Initialize servos
    private Servo clawServoright;
    private Servo clawServoleft;
    private Servo wristservoright;
    private Servo wristservoleft;
    private final int closed = 0;
    private final int open = 45;
    private final int pickup_angle = 0;
    private final int stowed_angle = 90;
    private final int parallel_angle = 0;
    private final int dropping_angle = 200;

    public void initClaw() {
        clawServoright = hardwareMap.get(Servo.class, "servo1");
        clawServoleft = hardwareMap.get(Servo.class, "servo2");
        wristservoright = hardwareMap.get(Servo.class, "servo3");
        wristservoleft = hardwareMap.get(Servo.class, "servo4");
    }

    public void claw_servos(String state, String claw_State){
            if (state == "stowed") {
                clawServoright.setPosition(closed);
                clawServoleft.setPosition(closed);
            } else {
                switch (claw_State) {
                    case "right_open":
                        clawServoright.setPosition(open);
                        break;
                    case "right_close":
                        clawServoright.setPosition(closed);
                }
            }
        }

    public void wrist_servos(String state,String claw_State) {
        switch (state) {
            case "stowed":
                wristservoright.setPosition(stowed_angle);
            case "parallel_angle":
                wristservoleft.setPosition(parallel_angle);
                wristservoright.setPosition(parallel_angle);
        }
    }
}

/*

    Pickup

    Stowed

    Placing
        - Placing angle - 220 - Snapps to this angle.

    manual angle update, -> from the rt and lt to change the angle.


 */

//declaring a fucntion for open1 this is used for one side of the claw
//preset button for open
//preset button for closed
//declaring a function for open2 this is used for one side of the claw
//preset button for open
//preset button for closed
//declaring a function for rotation
//using the arm rotation calculator in the arm file, make the arm parallel to the ground or make the arm paralel to the pixel board in order to make optimal arm positions
//if rotation is over 180 degrees it means that the robot needs to put it on the mode that the claw wants to put the pixel on the board
//anything less means that that the claw needs to be turned so that it is parallel to the floor
//coding override for a few positions








//I've added a boolean variable manualOwnership to indicate whether manual ownership is enabled.
//Inside the main loop, if manualOwnership is false, the program reads gamepad inputs and controls the motors accordingly. If true, it displays a message 
//indicating manual control is enabled.
//Gamepad buttons A and B are used to toggle manualOwnership. Pressing A enables manual control, while pressing B disables it.
//Telemetry messages are updated to display the status of manual ownership and provide instructions when manual control is enabled.
//The left stick on gamepad1 is used to control the opening of the claw. By moving the left stick up or down (gamepad1.left_stick_y), 
//the claw motors (motorOpen1 and motorOpen2) are powered in opposite directions to open or close the claw.
//The right stick on gamepad1 is used to control the rotation of the wrist. By moving the right stick left or right (gamepad1.right_stick_x), the wrist motor (motorWrist) is powered to rotate the wrist in the desired direction.