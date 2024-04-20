package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
    Set LED colors based on Arm Position
    Placement-Green (180-280) Degrees
    Pickup-Blue (40-50) Degrees
    Stowed- Slow Flashing Yellow (85-95) Degrees
    Broken-RED (0-39)(51-84)(96-179)(281-360) Degrees
    If red allow overide code
    Fetch code from arm.java
 */

//makes the class for the Led, allows for the creation of class specific variables
public class LED {
    //RevBlinkinLedDriver led_Lights;
    private String state;
    private float armangle;
    //Assigns the led_lights variable to the mapping for the led controller
    led_Lights = HardwareMap.get(RevBlinkinLedDriver.class, "lights");

    public LED (String state, float arm_Angle, int arm_angle){
        this.state = state;// Constructor that makes the state variable
        this.armangle = arm_angle;// Constructor that makes the state variable
    }

    public void set_status(String state, float arm_Angle){
        this.state = state; //Assigns the state variable to the current State
        this.armangle = arm_Angle;
        if(this.armangle > 280 || 95 < this.armangle && this.armangle < 180 || this.armangle > 51 && this.armangle < 84){ // see's if the arm is outside the ranges it should be
            // Red Flashing
            this.led_Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_RED);// if its outside it will set the leds to flashing red
        }else{
            if(this.state.equals("pickUp")){
                this.led_Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);//Compares the state and sets the color to blue
            }
            if(this.state.equals("place")){
                this.led_Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);//Compares the state and sets the color to green
            }
            if(this.state.equals("stowed")){
                this.led_Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_BREATH_SLOW);//Compares the state and sets the color to flashing yellow
            }
        }
    }

}