package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.LanderLatch;
import org.firstinspires.ftc.teamcode.components.Sampler;
import org.firstinspires.ftc.teamcode.components.TeamMarker;
import org.firstinspires.ftc.teamcode.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.teamcode.robotplus.hardware.IMUWrapper;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

@Autonomous(name = "Gold")
public class Gold extends LinearOpMode {
    private Robot robot;
    private MecanumDrive drivetrain;
    private IMUWrapper imu;
    private LanderLatch landerLatch;
    private Sampler sampler;
    private TeamMarker teamMarker;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        imu = new IMUWrapper(hardwareMap);
        landerLatch = new LanderLatch(hardwareMap);
        sampler = new Sampler(hardwareMap);
        teamMarker = new TeamMarker(hardwareMap);

        waitForStart();

        // Unlatch from the lander
//        landerLatch.lowerRobotAuto();
        moveDistanceCm(MecanumDrive.Direction.DOWN, 15);
//        landerLatch.raiseRobotAuto();
        moveDistanceCm(MecanumDrive.Direction.UP, 20);

        // Move towards sampling area
        // 44cm from unlatching position to sampling line
        moveDistanceCm(MecanumDrive.Direction.LEFT, 64);

        // Move bot according to position of color sensor along left side

        boolean isMiddle = false, isRight = false;

        // Check over middle mineral
        isMiddle = checkAndBumpGoldMineral();

        // 14.5in = 36.83cm between minerals
        double betweenMinerals = 33;

        if (!isMiddle) {
            // Move to right mineral
            moveDistanceCm(MecanumDrive.Direction.UP, betweenMinerals);
            isRight = checkAndBumpGoldMineral();

            if (!isRight) {
                // Move to left mineral
                moveDistanceCm(MecanumDrive.Direction.DOWN, 2 * betweenMinerals);
                sampler.bumpMineral(this);
            }
        }

        // Move and rotate bot to edge of field
        double multiplier = 0;
        if (isMiddle) multiplier = 1;
        if (isRight)  multiplier = 1.7;
        moveDistanceCm(MecanumDrive.Direction.DOWN, (multiplier * betweenMinerals) + 35);
        // 45° angle from sample line to field edge
        drivetrain.setAngle(imu, -(Math.PI / 4));

        // Move to edge, 13cm from rotated left position
        moveDistanceCm(MecanumDrive.Direction.DOWN, 13);

        // Move down to depot, approximately 2 tiles length (4ft = 121.92cm)
        moveDistanceCm(MecanumDrive.Direction.LEFT, 121.92);

        // Drop team marker
        teamMarker.dumpMarker();
    }

    private void moveDistanceCm(MecanumDrive.Direction direction, double distance) {
        if (distance <= 0) return;
        drivetrain.complexDrive(direction.angle(), 1, 0);
        double voltage = hardwareMap.voltageSensor.get("Expansion Hub 2").getVoltage();
        long sleepTime = TimeOffsetVoltage.calculateDistance(voltage, distance);
        Log.d("OpMode", "sleep time: " + sleepTime);
        sleep(sleepTime);
        robot.stopMoving();
    }

    /**
     * @return whether the gold mineral is in front of the color sensor
     */
    private boolean checkAndBumpGoldMineral() {
        sleep(1000);
        boolean isGold = sampler.checkForGold();
        Log.d("OpMode", "is gold: " + isGold);
        if (isGold) sampler.bumpMineral(this);

        return isGold;
    }
}
