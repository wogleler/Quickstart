package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor FrM, BM;
    int urmom=0;
    public void Init(HardwareMap hardwareMap){
        FrM = hardwareMap.get(DcMotor.class, "FrM");
        BM = hardwareMap.get(DcMotor.class, "BM");
        FrM.setDirection(DcMotorSimple.Direction.REVERSE);
        BM.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void take(Gamepad gamepad1){
        if (gamepad1.squareWasPressed()&&urmom==0){
            FrM.setPower(0.75);
            BM.setPower(0.75);
            urmom++;
        } else if (gamepad1.squareWasPressed() && urmom==1) {
            FrM.setPower(0);
            BM.setPower(0);
            urmom++;
        }
    }
}
