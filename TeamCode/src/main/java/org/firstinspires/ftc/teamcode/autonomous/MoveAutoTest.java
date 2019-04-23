package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.micdsrobotics.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;
import org.firstinspires.ftc.teamcode.components.RoverRuckusRobot;

@Autonomous(name = "Test Auto Moving", group = "Test")
public class MoveAutoTest extends LinearOpMode {
    private Robot robot = new RoverRuckusRobot();
    private MecanumDrive drivetrain;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initHardware(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();

        waitForStart();

        moveDistanceCm(MecanumDrive.Direction.DOWN, 10);

        sleep(10000);

        moveDistanceCm(MecanumDrive.Direction.DOWN, 10);
    }

    private void moveDistanceCm(MecanumDrive.Direction direction, double distance) {
        drivetrain.complexDrive(direction.angle(), 1, 0);
        double voltage = hardwareMap.voltageSensor.get("Expansion Hub 2").getVoltage();
        sleep(TimeOffsetVoltage.calculateDistance(robot, voltage, distance));
        robot.stopMoving();
    }
}
