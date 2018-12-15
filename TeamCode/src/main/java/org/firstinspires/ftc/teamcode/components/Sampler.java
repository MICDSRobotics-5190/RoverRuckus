package org.firstinspires.ftc.teamcode.components;

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

        // Gold minerals hue is ~40°
        return hsv[0] > 40;
    }

    public void bumpMineral() {
        servo.setPosition(0.2);
        // Move back to center position
        servo.setPosition(0.5);
    }

}
