package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.LanderLatch;

@Autonomous(name = "Encoder Test", group = "Test")
public class EncoderTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        LanderLatch landerLatch = new LanderLatch(hardwareMap, true);

        waitForStart();

        telemetry.addData("state", "before raiseLift");
        telemetry.update();

        landerLatch.raiseLiftAuto();

        telemetry.addData("state", "after raiseLift");
        telemetry.update();

        landerLatch.lowerLiftAuto(LanderLatch.TOTAL_REVOLUTIONS);

        telemetry.addData("state", "after lowerLift");
        telemetry.update();
    }

}
