package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class ServoSetup extends LinearOpMode {
    Servo push, hood;
    CRServo rotate;
    public void init(HardwareMap hardwareMap){
        rotate = hardwareMap.get(CRServo.class, "rotate");
        push = hardwareMap.get(Servo.class, "push");
        hood = hardwareMap.get(Servo.class, "hood");
        push.setDirection(Servo.Direction.FORWARD);
        rotate.setPower(0);
        push.setPosition(0); /// 0.5 final
        hood.setPosition(0);
    }

    public void test(Gamepad gamepad1, Telemetry telemetry){
        if (gamepad1.dpadUpWasPressed())
            push.setPosition(push.getPosition()+0.01);
        if (gamepad1.dpadDownWasPressed())
            push.setPosition(push.getPosition()-0.01);
        if (gamepad1.dpadRightWasPressed())
            rotate.setPower(rotate.getPower()+0.01);
        if (gamepad1.dpadLeftWasPressed())
            rotate.setPower(rotate.getPower()-0.01);
        if (gamepad1.rightBumperWasPressed())
            hood.setPosition(hood.getPosition()+0.01);
        if (gamepad1.leftBumperWasPressed())
            hood.setPosition(hood.getPosition()-0.01);
        telemetry.addData("Push - ", push.getPosition());
        telemetry.addData("Rotate - ", rotate.getPower());
        telemetry.addData("Hood - ", hood.getPosition());
        telemetry.update();
    }

    @Override
    public void runOpMode(){
        init(hardwareMap);
        waitForStart();
        while(!isStopRequested()&&opModeIsActive()){
            test(gamepad1, telemetry);
        }
    }
}
