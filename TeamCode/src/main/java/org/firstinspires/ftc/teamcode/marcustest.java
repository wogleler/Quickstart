package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;






@TeleOp
public class marcustest extends LinearOpMode {

    DcMotor fl;


    @Override
    public void runOpMode(){
        fl = hardwareMap.get(DcMotor.class,"fr");
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while(!isStopRequested()&&opModeIsActive())
        {
            fl.setPower(0.5);
        }
    }
}
