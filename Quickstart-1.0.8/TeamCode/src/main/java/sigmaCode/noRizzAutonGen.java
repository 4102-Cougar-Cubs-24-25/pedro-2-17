package sigmaCode;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
@Autonomous(name = "pedro generated auto", group = "Examples")
public class noRizzAutonGen extends OpMode {
    private Timer pathTimer, actionTimer, opmodeTimer;
    private Follower follower;
    private int pathState;
 //   private AuraliciousActions action = new AuraliciousActions(hardwareMap, telemetry);
    private final Pose startPose = new Pose(7.2, 63, Math.toRadians(0));
    //todo: change above value to desired starting pose
    private PathChain line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12;

    //todo: add as many lines as are used in the path
    //todo: go to GeneratedPaths file
    public void buildPaths() {
        line1 = GeneratedPaths.line1;
        line2 = GeneratedPaths.line2;
        line3 = GeneratedPaths.line3;
        line4 = GeneratedPaths.line4;
        line5 = GeneratedPaths.line5;
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