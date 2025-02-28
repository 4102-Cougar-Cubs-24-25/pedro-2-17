package sigmaCode.oldStuff.oldAutons;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous(name = "aquatic andro", group = "Examples")

public class androCrying extends OpMode{
    private final Pose startPose = new Pose(7, 65, Math.toRadians(0));
    private final Pose rizz1a = new Pose(40.3, 65, Math.toRadians(0));
    private final Pose huzz1a = new Pose(3.2, 37, Math.toRadians(0));
    private final Pose huzz1b = new Pose(88.4, 28.5, Math.toRadians(0));
    private final Pose huzz1c = new Pose(89.1, 21.5, Math.toRadians(0));
    private final Pose huzz1d = new Pose(14.8, 22.9, Math.toRadians(0));
    private final Pose huzz2a = new Pose(82.2, 19.7, Math.toRadians(0));
    private final Pose huzz2b = new Pose(80.6, 10.4, Math.toRadians(0));
    private final Pose huzz2c = new Pose(15.3, 13.2, Math.toRadians(0));
    private final Pose huzz3a = new Pose(78.5, 13.7, Math.toRadians(0));
    private final Pose huzz3b = new Pose(88.7, 11, Math.toRadians(0));
    private final Pose huzz3c = new Pose(13.2, 10, Math.toRadians(0));
    private final Pose huzz4a = new Pose(45.6, 25, Math.toRadians(0));
    private final Pose huzz4b = new Pose(7.2, 31.9, Math.toRadians(0));
    private final Pose rizz2a = new Pose(40.3, 47.2, Math.toRadians(0));
    private final Pose rizz2b = new Pose(40.3, 64.8, Math.toRadians(0));
    private final Pose sigma1 = new Pose(7.2, 31.9, Math.toRadians(0));
    private Timer pathTimer, actionTimer, opmodeTimer;
    private Follower follower;
    private Path rizz1, sigma;
    private PathChain huzz1, huzz2, huzz3, huzz4, rizz2;
    private int pathState;
    public void buildPaths(){
        rizz1 = new Path(new BezierLine(new Point(startPose), new Point(rizz1a)));
        rizz1.setConstantHeadingInterpolation(Math.toRadians(0));
        huzz1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(rizz1a), new Point(huzz1a), new Point(huzz1b), new Point(huzz1c), new Point(huzz1d)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        huzz2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(huzz1d), new Point(huzz2a), new Point(huzz2b), new Point(huzz2c)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        huzz3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(huzz2c), new Point(huzz3a), new Point(huzz3b), new Point(huzz3c)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        huzz4 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(huzz3c), new Point(huzz4a), new Point(huzz4b)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        rizz2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(huzz4b), new Point(rizz2a), new Point(rizz2b)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        sigma = new Path(new BezierLine(new Point(rizz2b), new Point(sigma1)));
        sigma.setConstantHeadingInterpolation(Math.toRadians(0));
    }
    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(rizz1);
                setPathState(1);
                break;

            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(huzz1, true);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(huzz2, true);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(huzz3, true);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(huzz4, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(rizz2, true);
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(sigma, true);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    setPathState(-1);
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
    @Override
    public void init() {
        pathTimer = new Timer();
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        telemetry.addData("Path State", pathState);
        telemetry.addData("Position", follower.getPose().toString());
        telemetry.update();
    }
}
