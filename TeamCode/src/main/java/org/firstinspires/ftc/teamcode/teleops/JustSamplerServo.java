package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.components.Sampler;
import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

@TeleOp(name = "Just Sampler Servo")
public class JustSamplerServo extends OpMode {
    private Robot robot;
    private MecanumDrive drivetrain;
    private Controller player1;
    private Sampler sampler;


    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        player1 = new Controller(gamepad1);
        sampler = new Sampler(hardwareMap);
    }

    @Override
    public void loop() {
        drivetrain.complexDrive(gamepad1, telemetry);

        telemetry.addData("X button", gamepad1.x);
        telemetry.addData("Y button", gamepad1.y);
        if (gamepad1.x) {
            sampler.moveOut();
        } else if (gamepad1.y) {
            sampler.moveCenter();
        }
    }
}
