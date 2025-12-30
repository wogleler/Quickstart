package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class MarcusAu extends LinearOpMode{

    private DcMotor FrM, BM;
    private Follower follower;
    private Timer pathTimer, opmodeTimer;
    Outtake Out = new Outtake();
    public enum PathState{
        //Startpos - End Pos

        DRIVE_BALLPOS1GET,
        DRIVE_BALLPOS2GET,
        DRIVE_BALLPOS3GET,
        DRIVE_SHOOTPOS1,
        DRIVE_SHOOTPOS2,
        DRIVE_SHOOTPOS3,
        SHOOT_PRELOAD
    }

    PathState pathState;

    private final Pose startpose = new Pose(56.370106761565836,8.370106761565841,Math.toRadians(90));
    private final Pose GetFirstBall1 = new Pose(56.370106761565836,35.871886120996436,Math.toRadians(180));
    private final Pose GetFirstBall2 = new Pose(18.790035587188612,35.871886120996436,Math.toRadians(180));
    private final Pose GetSecondBall1 = new Pose(56.370106761565836,59.103202846975094,Math.toRadians(180));
    private final Pose GetSecondBall2 = new Pose(18.790035587188612,59.103202846975094,Math.toRadians(180));
    private final Pose GetThirdBall1 = new Pose(56.370106761565836,83.35943060498221,Math.toRadians(180));
    private final Pose GetThirdBall2 = new Pose(18.790035587188612,83.35943060498221,Math.toRadians(180));
    private final Pose ShootPose = new Pose(57.56583629893238,85.75088967971531,Math.toRadians(45));
    int rowcounter = 1;
    int shoottimer = 0;
    private PathChain driveStartPosGetFirstBall,driveShootPos1,driveShootPosGetSecondBall,driveShootPos2,driveShootPosGetThirdBall,driveShootPos3;


    public void buildPaths(){
        driveStartPosGetFirstBall = follower.pathBuilder()
                .addPath(new BezierLine(startpose,GetFirstBall1))
                .setLinearHeadingInterpolation(startpose.getHeading(),GetFirstBall1.getHeading())
                .addPath(new BezierLine(GetFirstBall1,GetFirstBall2))
                .setLinearHeadingInterpolation(GetFirstBall1.getHeading(),GetFirstBall2.getHeading())
                .build();
        driveShootPos1 = follower.pathBuilder()
                .addPath(new BezierLine(GetFirstBall2,ShootPose))
                .setLinearHeadingInterpolation(GetFirstBall2.getHeading(),ShootPose.getHeading())
                .build();
        driveShootPosGetSecondBall = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose,GetSecondBall1))
                .setLinearHeadingInterpolation(ShootPose.getHeading(),GetSecondBall1.getHeading())
                .addPath(new BezierLine(GetSecondBall1,GetSecondBall2))
                .setLinearHeadingInterpolation(GetSecondBall1.getHeading(),GetSecondBall2.getHeading())
                .build();
        driveShootPos2 = follower.pathBuilder()
                .addPath(new BezierLine(GetSecondBall2,ShootPose))
                .setLinearHeadingInterpolation(GetSecondBall2.getHeading(),ShootPose.getHeading())
                .build();
        driveShootPosGetThirdBall = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose,GetThirdBall1))
                .setLinearHeadingInterpolation(ShootPose.getHeading(),GetThirdBall1.getHeading())
                .addPath(new BezierLine(GetThirdBall1,GetThirdBall2))
                .setLinearHeadingInterpolation(GetThirdBall1.getHeading(),GetThirdBall2.getHeading())
                .build();
        driveShootPos3 = follower.pathBuilder()
                .addPath(new BezierLine(GetThirdBall2,ShootPose))
                .setLinearHeadingInterpolation(GetThirdBall2.getHeading(),ShootPose.getHeading())
                .build();
    }

    public void statePathUpdate(){
        switch(pathState) {
            case DRIVE_BALLPOS1GET:
                follower.followPath(driveStartPosGetFirstBall, true);
                setPathState(PathState.DRIVE_SHOOTPOS1);
                break;
            case DRIVE_BALLPOS2GET:
                follower.followPath(driveShootPosGetSecondBall, true);
                setPathState(PathState.DRIVE_SHOOTPOS2);
                break;
            case DRIVE_BALLPOS3GET:
                follower.followPath(driveShootPosGetThirdBall, true);
                setPathState(PathState.DRIVE_SHOOTPOS3);
                break;
            case DRIVE_SHOOTPOS1:
                follower.followPath(driveShootPos1, true);
                setPathState(PathState.SHOOT_PRELOAD);
                rowcounter = 2;
                break;
            case DRIVE_SHOOTPOS2:
                follower.followPath(driveShootPos2, true);
                setPathState(PathState.SHOOT_PRELOAD);
                rowcounter = 3;
                break;
            case DRIVE_SHOOTPOS3:
                follower.followPath(driveShootPos3, true);
                setPathState(PathState.SHOOT_PRELOAD);
                rowcounter = 0;
                break;
            case SHOOT_PRELOAD:
                //shooting stuff goes here
                if(!follower.isBusy()) {
                    if (shoottimer < 30) {
                        shoottimer += 1;
                        Out.AngleHood(0.5f, telemetry);
                        Out.setrotation(0.5f, telemetry);
                        Out.FireBall(telemetry);
                    } else {
                        Out.StopNextBall(telemetry);
                        shoottimer = 0;
                        if(rowcounter == 2){
                            setPathState(PathState.DRIVE_BALLPOS2GET);
                        } else if (rowcounter == 3) {
                            setPathState(PathState.DRIVE_BALLPOS3GET);
                        }
                    }
                }
                break;
        }
    }

    public void setPathState(PathState newState){
        pathState = newState;
        pathTimer.resetTimer();
    }

    public void inititk(HardwareMap hardwareMap){
        FrM = hardwareMap.get(DcMotor.class, "FrM");
        BM = hardwareMap.get(DcMotor.class, "BM");
        FrM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void itkon(){
        FrM.setPower(0.75);
        BM.setPower(0.75);
    }

    @Override
    public void runOpMode(){


                inititk(hardwareMap);
                rowcounter = 1;
                pathState = PathState.DRIVE_BALLPOS1GET;
                pathTimer = new Timer();
                opmodeTimer = new Timer();
                opmodeTimer.resetTimer();
                follower = Constants.createFollower(hardwareMap);

                buildPaths();
                follower.setPose(startpose);
                waitForStart();
                while(!isStopRequested()&&opModeIsActive())
                {
                    itkon();
                  follower.update();
                  statePathUpdate();
                  telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
                  telemetry.update();
                }
            }
    }
