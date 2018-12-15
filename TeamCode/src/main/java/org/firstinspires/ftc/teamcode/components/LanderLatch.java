package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @author Nick Clifford
 * @since 12/14/18
 */
public class LanderLatch {

    private DcMotor elevator;

    public LanderLatch(HardwareMap hardwareMap) {
        elevator = hardwareMap.get(DcMotor.class, "elevator");
    }

    public void lower() {
        elevator.setPower(-1);
    }

    public void raise() {
        elevator.setPower(1);
    }

    public void stop() {
        elevator.setPower(0);
    }

}
