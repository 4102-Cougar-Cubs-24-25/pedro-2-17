package sigmaCode.oldStuff.oldAutons;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.GeneratedPaths;

@Autonomous(name = "pedro generated auto", group = "Examples")
public class noAuraAutonGen extends OpMode {
    private Timer pathTimer, actionTimer, opmodeTimer;
    private Follower follower;
    private int pathState;
    private DcMotorEx vSlide;
    private Servo rhWrist;
    private Servo lhWrist;
    private Servo rvWrist;
    private Servo lvWrist;
    private Servo vClaw;
    private final Pose startPose = new Pose(7.2, 63, Math.toRadians(0));
    //todo: change above value to desired starting pose
    private PathChain line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13;

    //todo: add as many lines as are used in the path
    //todo: go to GeneratedPaths file
    public void buildPaths() {
        line1 = GeneratedPaths.line1;
        line2 = GeneratedPaths.line2;
        line3 = GeneratedPaths.line3;
        line4 = GeneratedPaths.line4;
        line5 = GeneratedPaths.line5;
        line6 = GeneratedPaths.line6;
        line7 = GeneratedPaths.line7;
        line8 = GeneratedPaths.line8;
        line9 = GeneratedPaths.line9;
        line10 = GeneratedPaths.line10;
        line11 = GeneratedPaths.line11;
        line12 = GeneratedPaths.line12;
        line13 = GeneratedPaths.line13;
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(line1);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(line2, true);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(line3, true);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(line4, true);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(line5, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(line6, true);
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(line7, true);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    follower.followPath(line8, true);
                    setPathState(8);
                }
                break;
            case 8:
                if (!follower.isBusy()) {
                    follower.followPath(line9, true);
                    setPathState(9);
                }
                break;
            case 9:
                if (!follower.isBusy()) {
                    follower.followPath(line10, true);
                    setPathState(10);
                }
                break;
            case 10:
                if (!follower.isBusy()) {
                    follower.followPath(line11, true);
                    setPathState(11);
                }
                break;
            case 11:
                if (!follower.isBusy()) {
                    follower.followPath(line12, true);
                    setPathState(12);
                }
                break;
            case 12:
                if (!follower.isBusy()) {
                    follower.followPath(line13, true);
                    setPathState(13);
                }
                break;
            case 13:
                if (!follower.isBusy()) {
                    setPathState(-1);
                }
                break;

            /* todo: if you are using more than 7 lines,
             *   modify this method to include more cases,
             *   but make sure the last case has no path
             *   and sets the path state to -1.*/
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