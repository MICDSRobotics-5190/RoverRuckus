package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ColorSensorWrapper;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

@Autonomous(name = "Blue Gold")
public class BlueGold extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);
        MecanumDrive drivetrain = (MecanumDrive) robot.getDrivetrain();
        ColorSensorWrapper colorSensor = new ColorSensorWrapper(hardwareMap);

        // TODO: Unlatch from the lander

        // Move towards sampling area
        drivetrain.complexDrive(MecanumDrive.Direction.LEFT.angle(), 1, 0);
        double voltage = hardwareMap.voltageSensor.get("Expansion Hub 1").getVoltage();
        // TODO: Measure correct distance
        sleep(TimeOffsetVoltage.calculateDistance(voltage, 80));
        robot.stopMoving();

        // Start moving
    }
}
