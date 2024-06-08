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
//5 inch acceleration for forward so subtract 5 from desired location
//4 inch acceleration for backward so subtract 4 from desired location
        autodrive.update_Drive("left", 14);


    }

    public void initHardware(){
        //status_lights.initLights();
        //robotArm.initArm();
        //robotClaw.initClaw();

        autodrive.initDrive();

    }
}