package org.firstinspires.ftc.teamcode.teleop;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Math.tan;
import static java.lang.Thread.sleep;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Centering{

    Limelight3A limelight;
    private FtcDashboard dashboard; // Add dashboard instance
    public DcMotor frontLeftMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;

    public static double kP = 0.03;
    public static double kD = 0.02;
    public static double max = 2;
    public static double min = -2;
    public static double error;
    private double tx;
    private double ty;
    private HardwareMap hwMap;
    //private Telemetry dashboardTelemetry;
    private Telemetry telemetry;
    private static double Alpha = 20;
    private static double Beta = 80;

    public Centering(HardwareMap hardMap, Telemetry telemetry, FtcDashboard dashboard){
        this.hwMap = hardMap;
        //this.dashboardTelemetry = telemetry;
        //this.dashboard = dashboard;
        this.telemetry = telemetry;
    }

    public void initcentering(){
        //dashboardTelemetry = dashboard.getTelemetry();

        backLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();

        limelight.pipelineSwitch(0);

        Alpha = Alpha * (Math.PI/180);
        Beta = Beta * (Math.PI/180);
    }
    public void center() throws InterruptedException {

        error = 0;
        double previousError = 0;

        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid()) {
            tx = result.getTx(); // How far left or right the target is (degrees)
            ty = result.getTy(); // How far up or down the target is (degrees)
        }

        //Transational Data Transfer
        double x_rad = tx * (Math.PI/180);
        double y_rad = ty * (Math.PI/180);

        double V_z = -(Math.cos(Beta) * Math.sin(y_rad) * Math.sin(x_rad)) + Math.sin(Beta) * Math.cos(y_rad);
        double r = -300/V_z;

        double vx = -Math.sin(Alpha) * Math.sin(y_rad) * Math.cos(x_rad) - Math.cos(Alpha) * Math.sin(Beta) * Math.sin(y_rad) * Math.sin(x_rad) + Math.cos(Alpha) * Math.cos(Beta) * Math.cos(y_rad);

        double vy = Math.cos(Alpha) * Math.sin(y_rad) * Math.cos(x_rad) - Math.sin(Alpha) * Math.sin(Beta) * Math.sin(y_rad) * Math.sin(x_rad) + Math.sin(Alpha) * Math.cos(Beta) * Math.cos(y_rad);

        // Calculate world coordinates
        double xW = r * vx;
        double yW = r * vy;
        double zW = 0.0; // Assuming the object is on the ground (z=0)

        error = xW + 23;
        // PID calculations
        double derivative = error - previousError;
        double correction = (kP * error) + (kD * derivative);
        previousError = error;
        correction = (float) Math.max(-0.5, Math.min(0.5, (-1 * correction)));

        telemetry.addData("xW", xW);
        telemetry.addData("error", error);
        telemetry.addData("correction", correction);
        telemetry.update();

        if (error < min) {
            frontLeftMotor.setPower(correction);
            backLeftMotor.setPower(-correction);
            frontRightMotor.setPower(-correction);
            backRightMotor.setPower(correction);
        } else if (error > max) {
            frontLeftMotor.setPower(correction);
            backLeftMotor.setPower(-correction);
            frontRightMotor.setPower(-correction);
            backRightMotor.setPower(correction);
        } else if (error > min || error < max) {
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
        }
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);

    }
}
