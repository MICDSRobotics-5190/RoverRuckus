package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.LanderLatch;
import org.firstinspires.ftc.teamcode.components.Sampler;
import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

@TeleOp(name = "Latcher/Sampler Servo")
public class LatcherSamplerServo extends OpMode {

    private Robot robot;
    private MecanumDrive drivetrain;
    private Controller player1;
    private LanderLatch landerLatch;
    private Sampler sampler;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        player1 = new Controller(gamepad1);
        landerLatch = new LanderLatch(hardwareMap);
        sampler = new Sampler(hardwareMap);
    }

    @Override
    public void loop() {
        drivetrain.complexDrive(gamepad1, telemetry);

        if (player1.a.isDown()) {
            landerLatch.raise();
        } else if (player1.b.isDown()) {
            landerLatch.lower();
        } else {
            landerLatch.stop();
        }

        if (player1.x.isDown()) {
            sampler.moveOut();
        } else if (player1.y.isDown()) {
            sampler.moveCenter();
        }
    }

}
