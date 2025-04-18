package org.firstinspires.ftc.teamcode;

import static com.sun.tools.doclint.Entity.beta;
import static com.sun.tools.doclint.Entity.pi;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Alpha;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(group = "Primary")
@Config
public class Limelight extends LinearOpMode {

    Limelight3A limelight;

    private FtcDashboard dashboard; // Add dashboard instance
    public DcMotor frontLeftMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;

    public static double kP = 0.002;
    public static double kI = 0.0;
    public static double kD = 0.001;
    public static double max = 15;
    public static double min = -15;
    public static double error;
    private double tx;
    private double ty;
    private double ta;

    private static double Alpha = 20;
    private static double Beta = 80;


    @Override
    public void runOpMode() throws InterruptedException {

        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();

        limelight.pipelineSwitch(0);

        waitForStart();

        error = 0;
        double previousError = 0;
        double integral = 0;

        Alpha = Alpha * (Math.PI/180);
        Beta = Beta * (Math.PI/180);

        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                tx = result.getTx(); // How far left or right the target is (degrees)
                ty = result.getTy(); // How far up or down the target is (degrees)
                ta = result.getTa(); // How big the target looks (0%-100% of the image)
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
            integral += error;
            double derivative = error - previousError;
            double correction = (kP * error) + (kI * integral) + (kD * derivative);
            previousError = error;

            correction = (float) Math.max(-0.5, Math.min(0.5, correction));

            telemetry.addData("kP", kP);
            telemetry.addData("kI", kI);
            telemetry.addData("kD", kD);
            telemetry.addData("max", max);
            telemetry.addData("min", min);
            telemetry.update();

            if (result.isValid()) {
                dashboardTelemetry.addData("Target X", tx);
                dashboardTelemetry.addData("Target Y", ty);
                dashboardTelemetry.addData("Target Area", ta);
                dashboardTelemetry.addData("ERROR", error);
                dashboardTelemetry.addData("correction", correction);
                dashboardTelemetry.addData("Final X", xW);
                dashboardTelemetry.addData("Final Y", yW);
                dashboardTelemetry.addData("Final Z", zW);
            } else {
                dashboardTelemetry.addData("Limelight", "No Targets");
            }

            dashboardTelemetry.update();

        }

    }
}
