package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Vacuum {

    private DcMotor intake;
    private DcMotor extend;
    private Servo angle;
    private ColorSensor sampleColor;
    private HardwareMap hwMap;

    private Vacuum(HardwareMap hardMap){
        this.hwMap = hardMap;
    }

    public void initVacuum(){
        intake = hwMap.dcMotor.get("intake");
        extend = hwMap.dcMotor.get("extend");
        angle = hwMap.get(Servo.class, "angle");
        sampleColor = hwMap.colorSensor.get("sampleColor");
    }

    public void updateVacuum(Gamepad gamepadOne){
        // if statement for each command calls a function to run that command subset
        if (gamepadOne.a){
            pickUp();
        }

    }

    public void pickUp(){

    }
}
