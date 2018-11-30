package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ColorSensorWrapper;
import org.firstinspires.ftc.teamcode.robotplus.hardware.IMUWrapper;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

@Autonomous(name = "Blue Gold")
public class BlueGold extends LinearOpMode {
    private Robot robot;
    private MecanumDrive drivetrain;
    private ColorSensorWrapper colorSensor;
    private IMUWrapper imu;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();
        colorSensor = new ColorSensorWrapper(hardwareMap);
        imu = new IMUWrapper(hardwareMap);

        // TODO: Unlatch from the lander

        // Move towards sampling area
        // 44cm from unlatching position to sampling line
        moveDistanceCm(MecanumDrive.Direction.LEFT, 44);

        // TODO: Move bot according to position of color sensor along left side

        boolean isMiddle = false, isRight = false;

        // Check over middle mineral
        isMiddle = checkGoldMineral();

        // 14.5in = 36.83cm between minerals
        double betweenMinerals = 36.83;

        if (!isMiddle) {
            // Move to right mineral
            moveDistanceCm(MecanumDrive.Direction.UP, betweenMinerals);
            isRight = checkGoldMineral();

            if (!isRight) {
                // Move to left mineral
                moveDistanceCm(MecanumDrive.Direction.DOWN, 2 * betweenMinerals);
                checkGoldMineral();
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
    }

    private void moveDistanceCm(MecanumDrive.Direction direction, double distance) {
        drivetrain.complexDrive(direction.angle(), 1, 0);
        double voltage = hardwareMap.voltageSensor.get("Expansion Hub 1").getVoltage();
        sleep(TimeOffsetVoltage.calculateDistance(voltage, distance));
        robot.stopMoving();
    }

    /**
     * @return whether the gold mineral is in front of the color sensor
     */
    private boolean checkGoldMineral() {
        // TODO
        return true;
    }
}
