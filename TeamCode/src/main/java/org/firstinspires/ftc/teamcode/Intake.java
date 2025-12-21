package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor IM;
    public void Init(HardwareMap hardwareMap){
        IM = hardwareMap.get(DcMotor.class, "IM");
    }
    public void take(Gamepad gamepad1){
        if (gamepad1.xWasPressed()){
            IM.setPower(0.75);
        }
    }
}
