package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.components.RoverRuckusRobot;
import org.firstinspires.ftc.teamcode.components.Sampler;

@TeleOp(name = "Just Sampler Servo")
public class JustSamplerServo extends OpMode {
    private RoverRuckusRobot robot = new RoverRuckusRobot();
    private MecanumDrive drivetrain;
    private Sampler sampler;

    @Override
    public void init() {
        robot.initHardware(hardwareMap);
        drivetrain = robot.getDrivetrain();
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
