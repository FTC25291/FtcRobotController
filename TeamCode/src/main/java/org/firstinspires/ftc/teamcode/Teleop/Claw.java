package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
        // Initialize servos
    private Servo servo1;
    public void initservos(){
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");
    //Add servo hardware creation

    public void set_angle(String state, float arm_Angle){
        if(arm_Angle > 280 || 95 < arm_Angle && arm_Angle < 180 || arm_Angle > 51 && arm_Angle < 84){ // see's if the arm is outside the ranges it should be
            // Red Flashing
            this.claw_angle.setangle(revClawAngleMoter.Clawangle.0 Degrees);
        }else{
            if(this.state.equals("pickUp")){
                this.claw_angle.setangle(revClawAngleMoter.Clawangle.0 Degrees);
            }
            if(this.state.equals("place")){
                this.claw_angle.setangle(revClawAngleMoter.Clawangle.120 Degrees);
            }
            if(this.state.equals("stowed")){
                this.claw_angle.setangle(revClawAngleMoter.Clawangle.90 Degrees);
        }
    }

    public void manual_angle(int Rt, int Lt){
             if(this.state.equals("Lt")) {
                 this.claw_angle.setangle(revPivotClawAngleMoter.Clawangle.x - 2 Degrees);
             }
             if(this.state.equals("Rt")){
                     this.claw_angle.setangle(revPivotClawAngleMoter.Clawangle.x + 2 Degrees);
            }
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