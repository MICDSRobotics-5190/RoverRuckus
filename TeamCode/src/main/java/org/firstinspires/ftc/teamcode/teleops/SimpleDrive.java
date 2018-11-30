package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

@TeleOp(name = "Simple Drivetrain")
public class SimpleDrive extends OpMode {

    private Robot robot;
    private MecanumDrive drivetrain;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
    }

    @Override
    public void loop() {
        drivetrain.complexDrive(gamepad1, telemetry);
    }

}
