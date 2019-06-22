package org.firstinspires.ftc.teamcode.teleops;

import android.media.MediaPlayer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.micdsrobotics.robotplus.gamepadwrapper.Controller;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.components.*;

@TeleOp(name = "Full Demo")
public class FullDemo extends OpMode {

    private Robot robot = new RoverRuckusRobot();
    private MecanumDrive drivetrain;
    private Controller player1;
    private LanderLatch landerLatch;
    private Sampler sampler;
    private Arm arm;
    private TeamMarker teamMarker;

    private MediaPlayer beepPlayer;
    private MediaPlayer screamPlayer;
    private MediaPlayer beepWhistlePlayer;

    @Override
    public void init() {
        robot.initHardware(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        player1 = new Controller(gamepad1);
        landerLatch = new LanderLatch(hardwareMap, false);
        sampler = new Sampler(hardwareMap);
        arm = new Arm(hardwareMap);
        teamMarker = new TeamMarker(hardwareMap);

        beepPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.beep);
        screamPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.scream);
        beepWhistlePlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.beep_whistle);
    }

    @Override
    public void loop() {
        drivetrain.complexDrive(gamepad1, telemetry);

        // User 1 controls

        if (gamepad1.a) {
            landerLatch.lowerLift();
        } else if (gamepad1.b) {
            landerLatch.raiseLift();
        } else {
            landerLatch.stop();
        }

        if (gamepad1.x) {
            sampler.moveOut();
        } else if (gamepad1.y) {
            sampler.moveCenter();
        }

        if (gamepad1.left_bumper) {
            teamMarker.resetMarker();
        } else if (gamepad1.right_bumper) {
            teamMarker.dumpMarker();
        }

        // User 2 controls

        if (gamepad2.a) {
            arm.pullDown();
        } else if (gamepad2.b) {
            arm.pullUp();
        } else if (gamepad2.x) {
            arm.liftDown();
        } else if (gamepad2.y) {
            arm.liftUp();
        } else {
            arm.stop();
        }

        if (gamepad2.dpad_up) {
            beepPlayer.start();
        } else if (gamepad2.dpad_left) {
            beepWhistlePlayer.start();
        } else if (gamepad2.dpad_right) {
            screamPlayer.start();
        }
    }

    @Override
    public void stop() {
        beepPlayer.release();
        screamPlayer.release();
        beepWhistlePlayer.release();
    }
}
