package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(group = "Primary")
public class robot extends LinearOpMode {
    private HardwareMap hwMap;
    private FtcDashboard dashboard;
    private Drive robotDrive;
    private Intake robotIntake;
    private Outtake robotOuttake;
    private Centering Centering;

    @Override
    public void runOpMode() throws InterruptedException {

        HardwareMap hwMap = hardwareMap;
        dashboard = FtcDashboard.getInstance();
        robotDrive = new Drive(hwMap);
        robotIntake = new Intake(hwMap);
        robotOuttake = new Outtake(hwMap, telemetry);
        Centering = new Centering(hwMap, telemetry, dashboard);

        initHardware(hwMap);

        while (!isStarted()) {}
        waitForStart();

        while (opModeIsActive()) {
            // Intake system controlled by gamepad1
            robotIntake.intake_servos(gamepad1);

            // Drive system controlled by gamepad1
            robotDrive.update_Drive(gamepad1);

            // Outtake system controlled by gamepad2
            robotOuttake.Update_Outtake(gamepad2);

            if (gamepad1.a){
                Centering.center();
            }

            // Add telemetry for key variables
        }
    }

    public void initHardware(HardwareMap hwMap) {
        robotDrive.initDrive();
        robotIntake.initintake();
        robotOuttake.initOuttake();
        Centering.initcentering();
    }
}
