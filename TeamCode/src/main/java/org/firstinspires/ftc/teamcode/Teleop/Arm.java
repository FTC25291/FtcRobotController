import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MultiAxisArmOpMode extends OpMode {

    private DcMotor rotateMotor;
    private ElapsedTime runtime = new ElapsedTime();

    // Constants for controlling the arm
    private static final double ROTATE_POWER = 0.5; // Adjust as needed
    private static final double ROTATE_TIME = 1.0; // Adjust as needed

    @Override
    public void init() {
        // Initialize motors
        rotateMotor = hardwareMap.get(DcMotor.class, "rotatemotor");

        // Set motor direction if needed (e.g., if the motor is mounted in reverse)
        rotateMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motor mode
        rotateMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        // Rotate the first part of the arm for a certain time
        if (runtime.seconds() < ROTATE_TIME) {
            rotateMotor.setPower(ROTATE_POWER);
        } else {
            rotateMotor.setPower(0.0); // Stop the motor after the specified time
        }
    }

    @Override
    public void stop() {
        // Stop all motors when the OpMode is stopped
        rotateMotor.setPower(0.0);
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







//declaring a function for rotation
//rotating the arm to set positions using the buttons on the controllers
//manual mode in case of failure or optimization (Controlled by codriver)
//making a function to get position. This is going to give the claw the values