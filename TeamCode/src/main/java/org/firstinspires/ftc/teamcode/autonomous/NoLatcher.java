package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.micdsrobotics.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.IMUWrapper;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;
import org.firstinspires.ftc.teamcode.components.RoverRuckusRobot;
import org.firstinspires.ftc.teamcode.components.Sampler;
import org.firstinspires.ftc.teamcode.components.TeamMarker;

@Autonomous(name = "No Latch")
public class NoLatcher extends LinearOpMode {
    private Robot robot = new RoverRuckusRobot();
    private MecanumDrive drivetrain;
    private IMUWrapper imu;
    private Sampler sampler;
    private TeamMarker teamMarker;

    @Override
    public void runOpMode() {
        robot.initHardware(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        imu = new IMUWrapper(hardwareMap);
        sampler = new Sampler(hardwareMap);
        teamMarker = new TeamMarker(hardwareMap);

        // Unlatch from the lander
//        landerLatch.lowerLift();
//        sleep(6400); // TODO: Fix times
//        landerLatch.stop();
//        moveDistanceCm(MecanumDrive.Direction.DOWN, 5);
//        landerLatch.raiseLift();
//        sleep(1000);
//        landerLatch.stop();
//        moveDistanceCm(MecanumDrive.Direction.UP, 5);

        // Move towards sampling area
        // 44cm from unlatching position to sampling line
        moveDistanceCm(MecanumDrive.Direction.LEFT, 44);

        // Move bot according to position of color sensor along left side

        boolean isMiddle, isRight = false;

        // Check over middle mineral
        isMiddle = checkAndBumpGoldMineral();

        // 14.5in = 36.83cm between minerals
        double betweenMinerals = 36.83;

        if (!isMiddle) {
            // Move to right mineral
            moveDistanceCm(MecanumDrive.Direction.UP, betweenMinerals);
            isRight = checkAndBumpGoldMineral();

            if (!isRight) {
                // Move to left mineral
                moveDistanceCm(MecanumDrive.Direction.DOWN, 2 * betweenMinerals);
                checkAndBumpGoldMineral();
            }
        }

        // Move and rotate bot to edge of field
        int multiplier = 0;
        if (isMiddle) multiplier = 1;
        if (isRight)  multiplier = 2;
        moveDistanceCm(MecanumDrive.Direction.DOWN, multiplier * betweenMinerals);
        // 45° angle from sample line to field edge
        drivetrain.setAngle(imu, -(Math.PI / 4));

        // Move to edge, 13cm from rotated left position
        moveDistanceCm(MecanumDrive.Direction.DOWN, 13);

        // Move down to depot, approximately 2 tiles length (4ft = 121.92cm)
        moveDistanceCm(MecanumDrive.Direction.LEFT, 121.92);

        // Drop team marker
        teamMarker.dumpMarkerAuto(this);
    }

    private void moveDistanceCm(MecanumDrive.Direction direction, double distance) {
        if (distance <= 0) return;
        drivetrain.complexDrive(direction.angle(), 1, 0);
        double voltage = hardwareMap.voltageSensor.get("Expansion Hub 2").getVoltage();
        sleep(TimeOffsetVoltage.calculateDistance(robot, voltage, distance));
        robot.stopMoving();
    }

    /**
     * @return whether the gold mineral is in front of the color sensor
     */
    private boolean checkAndBumpGoldMineral() {
        boolean isGold = sampler.checkForGold();
        if (isGold) sampler.bumpMineral(this);

        return isGold;
    }
}

