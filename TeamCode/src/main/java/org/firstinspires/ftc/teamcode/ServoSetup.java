package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class ServoSetup extends LinearOpMode {
    Servo rotate, push, hood;
    public void init(HardwareMap hardwareMap){
        rotate = hardwareMap.get(Servo.class, "rotate");
        push = hardwareMap.get(Servo.class, "push");
        hood = hardwareMap.get(Servo.class, "hood");
        rotate.setPosition(0);
        push.setPosition(0);
        hood.setPosition(0);
    }

    public void test(Gamepad gamepad1, Telemetry telemetry){
        if (gamepad1.dpadUpWasPressed())
            push.setPosition(push.getPosition()+0.1);
        if (gamepad1.dpadDownWasPressed())
            push.setPosition(push.getPosition()-0.1);
        if (gamepad1.dpadRightWasPressed())
            rotate.setPosition(rotate.getPosition()+0.1);
        if (gamepad1.dpadLeftWasPressed())
            rotate.setPosition(rotate.getPosition()-0.1);
        if (gamepad1.xWasPressed())
            hood.setPosition(hood.getPosition()+0.1);
        if (gamepad1.circleWasPressed())
            hood.setPosition(hood.getPosition()-0.1);
        telemetry.addData("Push - ", push.getPosition());
        telemetry.addData("Rotate - ", rotate.getPosition());
        telemetry.addData("Hood - ", hood.getPosition());
        telemetry.update();
    }

    @Override
    public void runOpMode(){
        init(hardwareMap);
        while(!isStopRequested()&&opModeIsActive()){
            test(gamepad1, telemetry);
        }
    }
}
