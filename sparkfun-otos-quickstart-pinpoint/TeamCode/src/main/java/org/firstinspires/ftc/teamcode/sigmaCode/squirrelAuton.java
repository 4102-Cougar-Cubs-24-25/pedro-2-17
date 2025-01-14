package org.firstinspires.ftc.teamcode.sigmaCode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriverRR;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;

@Autonomous(name = "3-spec skibidi aura, ready up!")
public class squirrelAuton extends LinearOpMode{
    public class VerticalSlide {
        private DcMotorEx vSlide;
        public VerticalSlide(HardwareMap hardwareMap) {
            vSlide = hardwareMap.get(DcMotorEx.class, "vSlide");
            vSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vSlide.setDirection(DcMotorSimple.Direction.FORWARD);
            vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        public class SlidesUp implements Action {
            private boolean on = false;
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!on) {
                    vSlide.setTargetPosition(2350);
                    vSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    vSlide.setPower(-0.9);
                    on = true;
                }
                telemetry.addData("slides",vSlide.getCurrentPosition());
                telemetry.update();
                double pos = vSlide.getCurrentPosition();
                packet.put("liftPos", pos);
                if (vSlide.getCurrentPosition() < 2350) {
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    vSlide.setPower(0);
                    vSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    return false;
                }
            }
        }
        public class SlidesUpHalf implements Action {
            private boolean on = false;
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!on) {
                    vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    vSlide.setTargetPosition(1700);
                    vSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    vSlide.setPower(-0.9);
                    on = true;
                }
                telemetry.addData("slides",vSlide.getCurrentPosition());
                telemetry.update();
                double pos = vSlide.getCurrentPosition();
                packet.put("liftPos", pos);
                if (vSlide.getCurrentPosition() < 1700) {
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    vSlide.setPower(0);
                    vSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    return false;
                }
            }
        }

        public class SlidesDown implements Action {
            private boolean on = false;
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!on) {
                    vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    vSlide.setTargetPosition(-2350);
                    vSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    vSlide.setPower(0.7);
                    on = true;
                }
                telemetry.addData("slides",vSlide.getCurrentPosition());
                telemetry.update();
                double pos = vSlide.getCurrentPosition();
                packet.put("liftPos", pos);
                if (vSlide.getCurrentPosition() > -2350) {
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    vSlide.setPower(0);
                    vSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    return false;
                }
            }
        }
        public Action slidesUp(){
            return new SlidesUp();
        }
        public Action slidesDown(){
            return new SlidesDown();
        }
        public Action slidesUpHalf(){
            return new SlidesUpHalf();
        }
    }
    public class HorizontalWrist {
        private Servo rhWrist;
        private Servo lhWrist;
        public HorizontalWrist(HardwareMap hardwareMap){
            lhWrist = hardwareMap.get(Servo.class, "lhWrist");
            rhWrist = hardwareMap.get(Servo.class, "rhWrist");
        }
        public class WristUp implements Action{
            @Override
            //.35 is rh
            public boolean run(@NonNull TelemetryPacket packet){
                rhWrist.setPosition(.35);
                lhWrist.setPosition(0);
                return false;
            }
        }
        public Action wristUp(){
            return new WristUp();
        }
    }
    public class VerticalWrist {
        private Servo rvWrist;
        private Servo lvWrist;
        public VerticalWrist(HardwareMap hardwareMap) {
            rvWrist = hardwareMap.get(Servo.class, "rvWrist");
            lvWrist = hardwareMap.get(Servo.class, "lvWrist");
        }
        public class WristBack implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rvWrist.setPosition(.66);
                lvWrist.setPosition(0);
                return false;
            }
        }
        public Action wristForward() {
            return new WristForward();
        }

        public class WristForward implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rvWrist.setPosition(0);
                lvWrist.setPosition(.66);
                return false;
            }
        }
        public Action wristBack() {
            return new WristBack();
        }
    }
    public class VerticalClaw {
        private Servo vClaw;
        public VerticalClaw(HardwareMap hardwareMap) {
            vClaw = hardwareMap.get(Servo.class, "vClaw");
        }
        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                vClaw.setPosition(0.8);
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }
        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                vClaw.setPosition(0);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }
    }

    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(11, -66.5, Math.toRadians(90));
        PinpointDrive drive = new PinpointDrive(hardwareMap, initialPose);
        VerticalSlide vSlide = new VerticalSlide(hardwareMap);
        VerticalClaw vClaw = new VerticalClaw(hardwareMap);
        VerticalWrist vWrist = new VerticalWrist(hardwareMap);
        HorizontalWrist hWrist = new HorizontalWrist(hardwareMap);
        Actions.runBlocking(vClaw.closeClaw());
        Actions.runBlocking(hWrist.wristUp());

        TrajectoryActionBuilder scorePreload = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(11,-34));
        //preload scored
        TrajectoryActionBuilder pushSamples = drive.actionBuilder(new Pose2d(11,-31, Math.toRadians(90)))
                .strafeTo(new Vector2d(11,-41))
                .strafeTo(new Vector2d(33, -41))
                .splineToConstantHeading(new Vector2d(44, -18),Math.toRadians(90))
                .strafeTo(new Vector2d(48,-18))
                .strafeTo(new Vector2d(48,-58))
                //1 sample in human player zone
                .strafeTo(new Vector2d(48,-18))
                .strafeTo(new Vector2d(60, -18))
                .strafeTo(new Vector2d(60, -58))
                //2 samples in human player zone
                .strafeTo(new Vector2d(60, -52))
                //wait for human player
                .waitSeconds(.25)
                .strafeTo(new Vector2d(60, -68));
        TrajectoryActionBuilder spec2 = drive.actionBuilder(new Pose2d(60, -68, Math.toRadians(90)))
                //spec 2 picked up
                .splineToConstantHeading(new Vector2d(-1, -32), Math.toRadians(90), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-35, 60));
        //spec 2 scored
        TrajectoryActionBuilder spec2scored = drive.actionBuilder(new Pose2d(-1, -32, Math.toRadians(90)))
                .strafeTo(new Vector2d(-1, -36))
                .splineToConstantHeading(new Vector2d(52, -45), Math.toRadians(270), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-35, 60))
                .strafeTo(new Vector2d(52, -68));
        TrajectoryActionBuilder spec3 = drive.actionBuilder(new Pose2d(52, -68, Math.toRadians(90)))
                //spec 3 picked up
                .splineToConstantHeading(new Vector2d(7, -34.5), Math.toRadians(90), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-35, 60));
        //spec 3 scored
        TrajectoryActionBuilder spec3scored = drive.actionBuilder(new Pose2d(7, -34.5, Math.toRadians(90)))
                .strafeTo(new Vector2d(7, -41))
                .splineToConstantHeading(new Vector2d(52, -66), Math.toRadians(270), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-35, 60));
        TrajectoryActionBuilder spec4 = drive.actionBuilder(new Pose2d(52, -66, Math.toRadians(90)))
                //spec 4 picked up
                .splineToConstantHeading(new Vector2d(3, -37), Math.toRadians(90), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-35, 60));
        //spec 4 scored
        TrajectoryActionBuilder spec4scored = drive.actionBuilder(new Pose2d(3, -37, Math.toRadians(90)))
                .strafeTo(new Vector2d(3, -41))
                .splineToConstantHeading(new Vector2d(52, -66), Math.toRadians(270), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-35, 60));
        //parked
        TrajectoryActionBuilder wait = drive.actionBuilder(new Pose2d(52, -66, Math.toRadians(90)))
                .waitSeconds(1);
        TrajectoryActionBuilder waitLess = drive.actionBuilder(new Pose2d(52, -66, Math.toRadians(90)))
                .waitSeconds(.5);


        //steps: scorePreload, pushSamples, spec2, spec2scored, spec3, spec3scored, spec4, spec4scored
        waitForStart();
        Action preload = scorePreload.build();
        Action push = pushSamples.build();
        Action s2pick = spec2.build();
        Action s2score = spec2scored.build();
        Action s3pick = spec3.build();
        Action s3score = spec3scored.build();
        Action s4pick = spec4.build();
        Action s4score = spec4scored.build();
        Action w = wait.build();
        Action wl = waitLess.build();
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                preload,
                                vSlide.slidesUpHalf()
                        ),
                        vSlide.slidesUp(),
                        new ParallelAction(
                                vSlide.slidesDown(),
                                push,
                                vClaw.openClaw(),
                                vWrist.wristBack()
                        ),
                        vClaw.closeClaw(), wl,
                        new ParallelAction(
                                vSlide.slidesUpHalf(),
                                s2pick,
                                vWrist.wristForward()
                        ),
                        vSlide.slidesUp(),
                        new ParallelAction(
                                vSlide.slidesDown(),
                                vClaw.openClaw(),
                                s2score,
                                vWrist.wristBack()
                        ),
                        new ParallelAction(
                          vClaw.closeClaw(),
                          w
                        ),
                        new ParallelAction(
                                vSlide.slidesUpHalf(),
                                s3pick,
                                vWrist.wristForward()
                        ),
                        vSlide.slidesUp(),
                        new ParallelAction(
                                vSlide.slidesDown(),
                                vClaw.openClaw(),
                                s3score,
                                vWrist.wristBack()
                        ),
                        w, vClaw.closeClaw(), w,
                        new ParallelAction(
                                vSlide.slidesUpHalf(),
                                s4pick,
                                vWrist.wristForward()
                        ),
                        vSlide.slidesUp(),
                        new ParallelAction(
                                vSlide.slidesDown(),
                                vClaw.openClaw(),
                                //s4score,
                                vWrist.wristBack()
                        )
                )
        );
    }
}
