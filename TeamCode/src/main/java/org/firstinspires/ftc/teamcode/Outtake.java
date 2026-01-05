package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Outtake {
    DcMotor power;
    Servo hood, help;
    CRServo rotate;
    double hp=0.0;
    public void Init(HardwareMap hardwareMap){
        power = hardwareMap.get(DcMotor.class, "power");
        rotate = hardwareMap.get(CRServo.class, "rotate");
        hood = hardwareMap.get(Servo.class, "hood");
        help = hardwareMap.get(Servo.class, "help");
        power.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        help.setDirection(Servo.Direction.REVERSE);
        rotate.setPower(0);
        hood.setPosition(0);
        help.setPosition(0);
    }
    public void rotation(Gamepad gamepad1, Telemetry telemetry){
        if (gamepad1.right_trigger!=0){
            rotate.setPower(gamepad1.right_trigger);
        } else if (gamepad1.left_trigger!=0) {
            rotate.setPower(-gamepad1.left_trigger);
        }
        telemetry.addData("Rotate pos - ", rotate.getPower());
    }

    public void DaHood(Gamepad gamepad1, Telemetry telemetry){
        if (gamepad1.dpadDownWasPressed()&&hp<=0.5)
            hp+=0.1;
        if (gamepad1.dpadDownWasPressed())
            hp-=0.1;
        hood.setPosition(hp);
        telemetry.addData("Hood pos - ", hood.getPosition());
    }

    public void fireinthehole(Gamepad gamepad1, Telemetry telemetry){
        if (gamepad1.touchpadWasPressed()){
            power.setPower(1);
            help.setPosition(0.5);
            telemetry.addData("Fire - ", 1);
            telemetry.addData("Help - ", 0.5);
        }
        if (gamepad1.touchpadWasReleased()){
            power.setPower(0);
            help.setPosition(0);
            telemetry.addData("Fire - ", 0);
            telemetry.addData("Help - ", 0);
        }
    }
    // autonomous part here
    public void setrotation(float RotationPos,Telemetry telemetry){

        rotate.setPower(RotationPos);


        telemetry.addData("Rotate pos - ", rotate.getPower());
        telemetry.update();
    }
    public void AngleHood(float HoodPosition,Telemetry telemetry){
        hood.setPosition(HoodPosition);
        telemetry.addData("Hood pos - ", hood.getPosition());
        telemetry.update();
    }
    public void FireBall(Telemetry telemetry){

        power.setPower(1);
        help.setPosition(0);
        telemetry.addData("Fire - ", 1);
        telemetry.addData("Help - ", 0);
        telemetry.update();

    }
    public void StopNextBall(Telemetry telemetry){

        help.setPosition(0.5);
        telemetry.addData("Fire - ", 0);
        telemetry.addData("Help - ", 0.5);
        telemetry.update();
    }
}
