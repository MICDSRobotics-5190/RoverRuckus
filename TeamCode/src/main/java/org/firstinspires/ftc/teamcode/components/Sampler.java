package org.firstinspires.ftc.teamcode.components;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robotplus.hardware.ColorSensorWrapper;

public class Sampler {

    private Servo servo;
    private ColorSensorWrapper colorSensor;

    public Sampler(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "samplerServo");
        colorSensor = new ColorSensorWrapper(hardwareMap);
    }

    public boolean checkForGold() {
        colorSensor.capHSVData();
        float[] hsv = colorSensor.getHsvValues();

        Log.d("Sampler", "hue: " + hsv[0] + ", saturation: " + hsv[1] + ", value: " + hsv[2]);

        return hsv[0] < 26;
    }

    public void moveOut() {
        servo.setPosition(1);
    }

    public void moveCenter() {
        servo.setPosition(0.5);
    }

    public void bumpMineral(LinearOpMode opMode) {
        Log.d("Sampler", "moving out");
        moveOut();
        // Allow time to move
        opMode.sleep(1500);
        // Move back to center position
        Log.d("Sampler", "moving center");
        moveCenter();
    }

}
