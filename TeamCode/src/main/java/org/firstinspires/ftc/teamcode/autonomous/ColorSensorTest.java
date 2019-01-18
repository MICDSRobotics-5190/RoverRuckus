package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robotplus.hardware.ColorSensorWrapper;

@Autonomous(name = "Color Sensor Test", group = "Test")
public class ColorSensorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensorWrapper colorSensor = new ColorSensorWrapper(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            colorSensor.capHSVData();
            float[] hsv = colorSensor.getHsvValues();
            telemetry.addData("hue", hsv[0]);
            telemetry.addData("saturation", hsv[1]);
            telemetry.addData("value", hsv[2]);
            telemetry.update();
        }
    }
}
