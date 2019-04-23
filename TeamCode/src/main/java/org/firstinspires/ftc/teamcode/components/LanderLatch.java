package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Motor;

/**
 * @author Nick Clifford
 * @since 12/14/18
 */
public class LanderLatch {

    public static final double TOTAL_REVOLUTIONS = 18;

    private DcMotor elevator;
    private boolean usingEncoders;

    public LanderLatch(HardwareMap hardwareMap, boolean encoders) {
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        usingEncoders = encoders;
        if (usingEncoders) {
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        stop();
    }

    public void raiseLiftAuto() {
        elevator.setTargetPosition((int) -(TOTAL_REVOLUTIONS * Motor.CORE_HEX.getCountsPerRevolution()));
        raiseLift();
        while (elevator.isBusy()) { }
        stop();
    }

    // We're only moving it down a little bit so we don't have to raise it all the way during teleop
    public void lowerLiftAuto(double revolutions) {
        elevator.setTargetPosition((int) (revolutions * Motor.CORE_HEX.getCountsPerRevolution()));
        lowerLift();
        while (elevator.isBusy()) { }
        stop();
    }

    public void raiseLift() {
        elevator.setPower(-1);
    }

    public void lowerLift() {
        elevator.setPower(1);
    }

    public void stop() {
        elevator.setPower(0);
        if (usingEncoders) {
            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

}
