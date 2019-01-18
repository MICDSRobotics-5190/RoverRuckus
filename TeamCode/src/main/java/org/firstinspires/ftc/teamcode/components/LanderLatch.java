package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robotplus.hardware.Motor;

/**
 * @author Nick Clifford
 * @since 12/14/18
 */
public class LanderLatch {

    private static final double TOTAL_REVOLUTIONS = 18.5;

    private DcMotor elevator;

    public LanderLatch(HardwareMap hardwareMap) {
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        stop();
    }

    public void lowerRobotAuto() {
        elevator.setTargetPosition((int) -(TOTAL_REVOLUTIONS * Motor.CORE_HEX.getCountsPerRevolution()));
        lowerRobot();
        while (elevator.isBusy()) { }
        stop();
    }

    public void raiseRobotAuto() {
        elevator.setTargetPosition((int) (TOTAL_REVOLUTIONS * Motor.CORE_HEX.getCountsPerRevolution()));
        raiseRobot();
        while (elevator.isBusy()) { }
        stop();
    }

    public void lowerRobot() {
        elevator.setPower(-1);
    }

    public void raiseRobot() {
        elevator.setPower(1);
    }

    public void stop() {
        elevator.setPower(0);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
