//declaring a fucntion for open1 this is used for one side of the claw
//preset button for open
//preset button for closed
//declaring a function for open2 this is used for one side of the claw
//preset button for open
//preset button for closed
//declaring a function for rotation
//using the arm rotation calculator in the arm file, make the arm parallel to the ground or make the arm paralel to the pixel board in order to make optimal arm positions
//if rotation is over 180 degrees it means that the robot needs to put it on the mode that the claw wants to put the pixel on the board
//anything less means that that the claw needs to be turned so that it is parallel to the floor
//coding override for a few positions






import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="ClawController")
public class ClawController extends LinearOpMode {
    private DcMotor motorOpen1;
    private DcMotor motorOpen2;
    private DcMotor motorWrist;

    private boolean manualOwnership = false;

    @Override
    public void runOpMode() {
        // Initialize motors
        motorOpen1 = hardwareMap.get(DcMotor.class, "motor_open1");
        motorOpen2 = hardwareMap.get(DcMotor.class, "motor_open2");
        motorWrist = hardwareMap.get(DcMotor.class, "motor_wrist");

        // Set zero power behavior to brake for all motors
        motorOpen1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorOpen2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            // Control the motors based on gamepad input if not in manual ownership
            if (!manualOwnership) {
                double openPower = -gamepad1.left_stick_y;
                double wristPower = gamepad1.right_stick_x;

                // Set motor powers
                motorOpen1.setPower(openPower);
                motorOpen2.setPower(openPower);
                motorWrist.setPower(wristPower);

                telemetry.addData("Open Power", openPower);
                telemetry.addData("Wrist Power", wristPower);
            } else {
                telemetry.addData("Manual Control", "ENABLED");
                telemetry.addData("Instructions", "Use manual controls to operate motors");
            }

            // Check if gamepad buttons are pressed to toggle manual ownership
            if (gamepad1.a) {
                manualOwnership = true;
            } else if (gamepad1.b) {
                manualOwnership = false;
            }

            telemetry.addData("Manual Ownership", manualOwnership ? "ENABLED" : "DISABLED");
            telemetry.update();
        }
    }
}




//I've added a boolean variable manualOwnership to indicate whether manual ownership is enabled.
//Inside the main loop, if manualOwnership is false, the program reads gamepad inputs and controls the motors accordingly. If true, it displays a message 
//indicating manual control is enabled.
//Gamepad buttons A and B are used to toggle manualOwnership. Pressing A enables manual control, while pressing B disables it.
//Telemetry messages are updated to display the status of manual ownership and provide instructions when manual control is enabled.
//The left stick on gamepad1 is used to control the opening of the claw. By moving the left stick up or down (gamepad1.left_stick_y), 
//the claw motors (motorOpen1 and motorOpen2) are powered in opposite directions to open or close the claw.
//The right stick on gamepad1 is used to control the rotation of the wrist. By moving the right stick left or right (gamepad1.right_stick_x), the wrist motor (motorWrist) is powered to rotate the wrist in the desired direction.