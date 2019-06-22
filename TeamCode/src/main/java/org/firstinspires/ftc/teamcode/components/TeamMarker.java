package org.firstinspires.ftc.teamcode.components;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TeamMarker {

    private Servo servo;

    public TeamMarker(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "teamMarkerServo");
    }

    public void dumpMarker() {
        servo.setPosition(0.9);
    }

    public void resetMarker() {
        servo.setPosition(0.6);
    }

    public void dumpMarkerAuto(LinearOpMode opMode) {
        Log.d("TeamMarker", "dumping");
        dumpMarker();
        // Move back to center position
        opMode.sleep(1000);
        resetMarker();
    }

}
