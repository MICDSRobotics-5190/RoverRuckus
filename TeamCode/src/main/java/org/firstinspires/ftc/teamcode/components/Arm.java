package org.firstinspires.ftc.teamcode.components;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private DcMotor robotShoulder;
    private CRServo robotReel;

    public Arm (HardwareMap hardwareMap) {
        robotShoulder = hardwareMap.get(DcMotor.class, "robotShoulder");
        robotReel = hardwareMap.get(CRServo.class, "robotReel");
    }

    public void pullUp()
    {
        robotReel.setPower(1);
    }

    public void pullDown()
    {
        robotReel.setPower(-1);
    }

    public void liftUp()
    {
        robotShoulder.setPower(1);
    }

    public void liftDown()
    {
        robotShoulder.setPower(-1);
    }

    public void stop()
    {
        robotShoulder.setPower(0);
        robotReel.setPower(0);
    }
}
