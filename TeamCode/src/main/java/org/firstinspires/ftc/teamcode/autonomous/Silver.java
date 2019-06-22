package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.micdsrobotics.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.IMUWrapper;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;
import org.firstinspires.ftc.teamcode.components.LanderLatch;
import org.firstinspires.ftc.teamcode.components.RoverRuckusRobot;
import org.firstinspires.ftc.teamcode.components.Sampler;
import org.firstinspires.ftc.teamcode.components.TeamMarker;

@Autonomous(name = "Silver")
public class Silver extends LinearOpMode {
    private Robot robot = new RoverRuckusRobot();
    private MecanumDrive drivetrain;
    private IMUWrapper imu;
    private LanderLatch landerLatch;
    private Sampler sampler;
    private TeamMarker teamMarker;

    @Override
    public void runOpMode() {
        robot.initHardware(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        imu = new IMUWrapper(hardwareMap);
        landerLatch = new LanderLatch(hardwareMap, true);
        sampler = new Sampler(hardwareMap);
        teamMarker = new TeamMarker(hardwareMap);

        waitForStart();

        // Unlatch from the lander
        landerLatch.raiseLiftAuto();
        moveDistanceCm(MecanumDrive.Direction.DOWN, 17);
        moveDistanceCm(MecanumDrive.Direction.LEFT, 20);
//        landerLatch.lowerLiftAuto(7);
        moveDistanceCm(MecanumDrive.Direction.UP, 22);

        // Move towards sampling area
        // 44cm from unlatching position to sampling line
        moveDistanceCm(MecanumDrive.Direction.LEFT, 63);

        // Move bot according to position of color sensor along left side

        boolean isMiddle, isRight = false;

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
        drivetrain.complexDrive(0, 0, 1);
        sleepDistance(66);

        // Move to edge, 13cm from rotated left position
        moveDistanceCm(MecanumDrive.Direction.UP, 20);

        // Move down to depot, approximately 2 tiles length (4ft = 121.92cm)
        moveDistanceCm(MecanumDrive.Direction.LEFT, 131.92);

        //rotate 180 degrees to aim marker dump at depot
        //drivetrain.complexDrive(0, 0, -1);
        //sleepDistance(88);

        // Drop team marker
        teamMarker.dumpMarkerAuto(this);
    }

    private void sleepDistance(double distance) {
        double voltage = hardwareMap.voltageSensor.get("Expansion Hub 2").getVoltage();
        long sleepTime = TimeOffsetVoltage.calculateDistance(robot, voltage, distance);
        Log.d("OpMode", "sleep time: " + sleepTime);
        sleep(sleepTime);
        robot.stopMoving();
    }

    private void moveDistanceCm(MecanumDrive.Direction direction, double distance) {
        if (distance <= 0) return;
        drivetrain.complexDrive(direction.angle(), 1, 0);
        sleepDistance(distance);
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
