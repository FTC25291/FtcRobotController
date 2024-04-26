package org.firstinspires.ftc.teamcode.Teleop;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

// Class to control LEDs based on arm position
public class LED {
    private RevBlinkinLedDriver ledLights;

    // Method to initialize LED lights
    public void initLights() {
        ledLights = hardwareMap.get(RevBlinkinLedDriver.class, "LED");
        ledLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
    }

    // Method to set LED pattern based on arm angle and state
    public void set_status(String state, float armAngle) {
        if (armAngle > 280 || (95 < armAngle && armAngle < 180) || (armAngle > 51 && armAngle < 84)) {
            // Red Flashing for broken state
            ledLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_RED);
        } else {
            // Set LED pattern based on state
            switch (state) {
                case "pickUp":
                    ledLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
                    break;
                case "place":
                    ledLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
                    break;
                case "stowed":
                    ledLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_BREATH_SLOW);
                    break;
                default:
                    // Handle invalid state
                    break;
            }
        }
    }
}

/*
    Set LED colors based on Arm Position
    Placement-Green (180-280) Degrees
    Pickup-Blue (40-50) Degrees
    Stowed- Slow Flashing Yellow (85-95) Degrees
    Broken-RED (0-39)(51-84)(96-179)(281-360) Degrees
    If red allow overide code
    Fetch code from arm.java
 */