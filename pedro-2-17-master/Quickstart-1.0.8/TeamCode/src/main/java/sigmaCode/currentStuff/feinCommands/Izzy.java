package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Robot;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.freakySubsystems.Lift;
import sigmaCode.currentStuff.freakySubsystems.LiftClaw;
import sigmaCode.currentStuff.freakySubsystems.LiftWrist;

public class Izzy extends Robot {
    public Servo vClaw, lvWrist, rvWrist;
    public DcMotorEx vSlide;
    public Follower follower;
    public Lift lift;
    public LiftClaw liftClaw;
    public LiftWrist liftWrist;
    public Izzy(HardwareMap hardwareMap, Pose startPose){
        Constants.setConstants(FConstants.class, LConstants.class);
        vSlide = hardwareMap.get(DcMotorEx.class, "vSlide");
        vSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rvWrist = hardwareMap.get(Servo.class, "rvWrist");
        lvWrist = hardwareMap.get(Servo.class, "lvWrist");
        vClaw = hardwareMap.get(Servo.class, "vClaw");
        lift = new Lift(vSlide);
        liftClaw = new LiftClaw(vClaw);
        liftWrist = new LiftWrist(rvWrist, lvWrist);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        CommandScheduler.getInstance().registerSubsystem(lift, liftClaw, liftWrist);
    }
    public void loop(){
        CommandScheduler.getInstance().run();
        follower.update();
    }
}