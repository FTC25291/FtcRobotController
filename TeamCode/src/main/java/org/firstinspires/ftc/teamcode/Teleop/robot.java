/*
Imports

creation class

when button pressed
    activate process


Driver Joysticks
    Call the drive.java
        - Current Joystick Values

Drive A Button

Driver B Button
    PickUp
        - Arm angle = 60 degrees - Arm Class angle input function
        - Claw angle 90 degrees - Claw Class angle input function
        - Extend to set position & let RT and LT extend and retract the arm - Arm Class set position function
        - Turn on the corresponding LED light - LED Class sets the led lights to a sequence based off of arm angle (Arm class angle output)
            - Blue

Driver Y Button
    Stow
        - Arm angle = 90 degrees - Arm Class angle input function
        - Claw angle = 180 degrees - Claw Class angle input function
        - Fully retracted - Arm Class set position function
        - Turn on the corresponding LED light - LED Class sets the led lights to a sequence based off of arm angle (Arm class angle output)
            - Flashing Yellow

Driver X Button
    Placing
        - Arm angle = 230 degrees - Arm Class angle input function
        - Claw angle = 180 degrees - Claw Class angle input function
        - Extend arm to set position & let RT and LT extend and retract the arm - Arm Class
        - Allow claw angle to be changed & let RB and LB change that angle
            - Green
Driver LT
    Extending Arm - Arm class button input function

Driver RT
    Retracting Arm - Arm class button input function

Driver LB
    Open Claw - Claw class button input function


Driver RB
    Close Claw - Claw class button input function

 */

package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Teleop.LED;

@TeleOp(group = "Primary")
public class robot extends LinearOpMode {
    private final LED status_lights = new LED();
    private String state = "Stowed";
    private float arm_angle = 90;
    //This is most likly going to move to a seperate file


    @Override
    public void runOpMode() throws InterruptedException {
        initHardwear();
        while(!isStarted()){}
        waitForStart();
        while(opModeIsActive()){
            //This is whre running code is added
        }
    }

    //Add all of the inits for the different subsystems here, allows for them to know what hardwear they are getting

    public void initHardwear(){
        status_lights.initLights();
    }
}

