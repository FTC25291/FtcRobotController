//Set LED colors based on Arm Position
//Placement-Green (180-280) Degrees
//Pickup-Blue (40-50) Degrees
//Stowed- Slow Flashing Yellow (85-95) Degrees
//Broken-RED (0-39)(51-84)(96-179)(281-360) Degrees
//If red allow overide code
//Fetch code from arm.java

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MultiAxisArmOpMode extends OpMode {

    private DcMotor rotateMotor;
    private NormalizedColorSensor colorSensor;
    private ElapsedTime runtime = new ElapsedTime();

    // Constants for controlling the arm
    private static final double ROTATE_POWER = 0.5; // Adjust as needed
    @Override
    public void init() {
        // Initialize motors
        rotateMotor = hardwareMap.get(DcMotor.class, "rotatemotor");

        // Set motor direction if needed (e.g., if the motor is mounted in reverse)
        rotateMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motor mode
        rotateMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Initialize color sensor
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color_sensor");
    }

    @Override
    public void loop() {
        // Rotate the first part of the arm for a certain time
        if (runtime.seconds() < ROTATE_TIME) {
            rotateMotor.setPower(ROTATE_POWER);
        } else {
            rotateMotor.setPower(0.0); // Stop the motor after the specified time
        }

        // Get current arm angle
        double armAngle = AngleCalculator.calculateAngle(rotateMotor);

        // Set LED color based on arm position
        setLEDColor(armAngle);
    }

    @Override
    public void stop() {
        // Stop all motors when the OpMode is stopped
        rotateMotor.setPower(0.0);
    }

    private void setLEDColor(double armAngle) {
        // Placement - Green (180-280) Degrees
        if (armAngle >= 180 && armAngle <= 280) {
            // Set LED color to green
            // code to set LED color to green
        }
        // Pickup - Blue (40-50) Degrees
        else if (armAngle >= 40 && armAngle <= 50) {
            // Set LED color to blue
            // code to set LED color to blue
        }
        // Stowed - Slow Flashing Yellow (85-95) Degrees
        else if (armAngle >= 85 && armAngle <= 95) {
            // Set LED color to slow flashing yellow
            // code to set LED color to slow flashing yellow
        }
        // Broken - RED (0-39), (51-84), (96-179), (281-360) Degrees
        else {
            // Set LED color to red
            // code to set LED color to red
        }
    }
}

class AngleCalculator {

    // Constants for motor and gear setup
    private static final int ENCODER_COUNTS_PER_REVOLUTION = 1440; // Adjust as needed
    private static final double GEAR_RATIO = 1.5; // Adjust as needed

    public static double calculateAngle(DcMotor motor) {
        int currentPosition = motor.getCurrentPosition();
        double revolutions = currentPosition / (double) ENCODER_COUNTS_PER_REVOLUTION;
        double angle = revolutions * 360 * GEAR_RATIO;
        return angle;
    }
}


