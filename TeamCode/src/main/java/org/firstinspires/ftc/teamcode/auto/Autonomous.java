package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends LinearOpMode {

    private autodrive autodrive;

    @Override
    public void runOpMode() throws InterruptedException {

        HardwareMap hwMap = hardwareMap;

        autodrive = new autodrive(hwMap);

        String state = "Stowed";
        String clawState = "Stowed";
        float arm_angle = 90;

        initHardware();

        waitForStart();

        autodrive.update_Drive("forward", 700);
        autodrive.update_Drive("backward", 700);
        autodrive.update_Drive("right", 700);
        autodrive.update_Drive("left", 700);
        autodrive.update_Drive("frontrightstrafe", 700);
        autodrive.update_Drive("backleftstrafe", 700);
        autodrive.update_Drive("frontleftstrafe", 700);
        autodrive.update_Drive("backrightstrafe", 700);
        autodrive.update_Drive("turnright90", 1000);
        autodrive.update_Drive("turnleft90", 1000);

    }

    public void initHardware(){
        //status_lights.initLights();
        //robotArm.initArm();
        //robotClaw.initClaw();

        autodrive.initDrive();

    }
}