package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.LanderLatch;

@Autonomous(name = "Encoder Test", group = "Test")
public class EncoderTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        LanderLatch landerLatch = new LanderLatch(hardwareMap);

        waitForStart();

        telemetry.addData("state", "before lowerRobot");
        telemetry.update();

        landerLatch.lowerRobotAuto();

        telemetry.addData("state", "after lowerRobot");
        telemetry.update();

        landerLatch.raiseRobotAuto();

        telemetry.addData("state", "after raiseRobot");
        telemetry.update();
    }

}
