package org.firstinspires.ftc.teamcode.auto;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class autodrive{

    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private HardwareMap hwMap;

    public autodrive(HardwareMap hardMap){
        this.hwMap = hardMap;
    }

    public void initDrive(){
        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void update_Drive(String direction, double DistanceInInches) {
        // Wheel diameter (in inches)
        double wheelDiameterInInches = 4.0; // Example value, replace with your actual wheel diameter

        // Calculate the circumference of the wheels
        double wheelCircumferenceInInches = Math.PI * wheelDiameterInInches;

        // Constant speed (adjust as needed)
        double speed = 2.6;

        // Calculate the time (in milliseconds) based on the constant speed
        int time = (int) (DistanceInInches / wheelCircumferenceInInches / speed * 1000);

        if (direction.equals("forward")) {
            frontLeftMotor.setPower(0.5);
            frontRightMotor.setPower(0.5);
            backLeftMotor.setPower(0.5);
            backRightMotor.setPower(0.5);
            sleep(time);
        } else if (direction.equals("backward")) {
            frontLeftMotor.setPower(-0.5);
            frontRightMotor.setPower(-0.5);
            backRightMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            sleep(time);
        } else if (direction.equals("right")) {
            frontLeftMotor.setPower(0.5);
            backRightMotor.setPower(0.5);
            frontRightMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            sleep(time);
        } else if (direction.equals("left")) {
            backRightMotor.setPower(-0.5);
            backLeftMotor.setPower(0.5);
            frontRightMotor.setPower(0.5);
            frontLeftMotor.setPower(-0.5);
            sleep(time);
        } else if (direction.equals("frontrightstrafe")) {
            backRightMotor.setPower(0.5);
            frontRightMotor.setPower(0);
            frontLeftMotor.setPower(0.5);
            backLeftMotor.setPower(0);
            sleep(time);
        } else if (direction.equals("backleftstrafe")) {
            backRightMotor.setPower(-0.5);
            frontRightMotor.setPower(0);
            frontLeftMotor.setPower(-0.5);
            backLeftMotor.setPower(0);
            sleep(time);
        } else if (direction.equals("frontleftstrafe")) {
            backRightMotor.setPower(0);
            frontRightMotor.setPower(0.5);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0.5);
            sleep(time);
        } else if (direction.equals("backrightstrafe")) {
            backRightMotor.setPower(0);
            frontRightMotor.setPower(-0.5);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(-0.5);
            sleep(time);
        } else if (direction.equals("turnright90")) {
            backRightMotor.setPower(-0.5);
            backLeftMotor.setPower(0.5);
            frontRightMotor.setPower(-0.5);
            frontLeftMotor.setPower(0.5);
            sleep(time);
        }else if (direction.equals("turnleft90")) {
            backRightMotor.setPower(0.5);
            backLeftMotor.setPower(-0.5);
            frontRightMotor.setPower(0.5);
            frontLeftMotor.setPower(-0.5);
            sleep(time);
        }
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}