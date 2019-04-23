package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;
import org.firstinspires.ftc.teamcode.components.RoverRuckusRobot;

@TeleOp(name = "Simple Drivetrain")
public class SimpleDrive extends OpMode {

    private Robot robot = new RoverRuckusRobot();
    private MecanumDrive drivetrain;

    @Override
    public void init() {
        robot.initHardware(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
    }

    @Override
    public void loop() {
        drivetrain.complexDrive(gamepad1, telemetry);
    }

}
