package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor FrM, BM;

    int urmom = 0;
    boolean lastSquare = false;

    public void Init(HardwareMap hardwareMap){
        FrM = hardwareMap.get(DcMotor.class, "FrM");
        BM = hardwareMap.get(DcMotor.class, "BM");
        FrM.setDirection(DcMotorSimple.Direction.REVERSE);
        BM.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void take(Gamepad gamepad1) {
        boolean square = gamepad1.square;
        if (square && !lastSquare)
        {
            if (urmom == 0) {
                FrM.setPower(0.5);
                BM.setPower(0.5);
                urmom = 1;
            } else {
                FrM.setPower(0);
                BM.setPower(0);
                urmom = 0;
            }
        }
        lastSquare = square;
    }
}
