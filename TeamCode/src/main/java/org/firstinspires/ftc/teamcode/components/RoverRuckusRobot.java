package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;

public class RoverRuckusRobot extends Robot {
    @Override
    public void initHardware(HardwareMap hardwareMap) {
        DcMotor main1 = hardwareMap.dcMotor.get("main1");
        DcMotor main2 = hardwareMap.dcMotor.get("main2");
        DcMotor minor1 = hardwareMap.dcMotor.get("minor1");
        DcMotor minor2 = hardwareMap.dcMotor.get("minor2");

        MecanumDrive mecanumDrive = new MecanumDrive(main1, main2, minor1, minor2);

        setDrivetrain(mecanumDrive);

        //change mecanum drive to have sides
        main2.setDirection(DcMotorSimple.Direction.REVERSE);
        minor2.setDirection(DcMotorSimple.Direction.REVERSE);

        main1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        main2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        minor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        minor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //minor1.setDirection(DcMotorSimple.Direction.REVERSE); for some reason after changing the motor this isn't needed

        mecanumDrive.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public double voltageDistanceAdapter(double v) {
        return (5.6417 * v) - 33.162;
    }
}
