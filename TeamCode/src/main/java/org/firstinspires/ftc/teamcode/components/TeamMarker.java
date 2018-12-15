package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TeamMarker {

    private Servo servo;

    public TeamMarker(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "teamMarkerServo");
    }

    public void dumpMarker() {
        servo.setPosition(1);
        // Move back to center position
        servo.setPosition(0.5);
    }

}
