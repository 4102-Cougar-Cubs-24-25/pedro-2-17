package sigmaCode;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.pedro.FollowPath;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.sigmaSubsystems.LVWrist;
import sigmaCode.sigmaSubsystems.RVWrist;
import sigmaCode.sigmaSubsystems.Slides;
import sigmaCode.sigmaSubsystems.VerticalClaw;

@Autonomous(name = "pedro generated auto", group = "Examples")
public class noRizzAutonGen extends PedroOpMode {
    private Timer pathTimer, actionTimer, opmodeTimer;
    private Follower follower;
    private int pathState;
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
    public noRizzAutonGen() {
        super(Slides.INSTANCE, VerticalClaw.INSTANCE, LVWrist.INSTANCE, RVWrist.INSTANCE);
    }
    public Command sigmaAuto(){
        return new SequentialGroup(
                new ParallelGroup(
                        new FollowPath(line1),
                        Slides.INSTANCE.half()
                ), Slides.INSTANCE.up(),
                new ParallelGroup(
                        Slides.INSTANCE.down(),
                        new FollowPath(line2),
                        VerticalClaw.INSTANCE.open(),
                        LVWrist.INSTANCE.wristBack(),
                        RVWrist.INSTANCE.wristBack()
                ), new FollowPath(line3)
        );
    }
    public void onInit() {
        VerticalClaw.INSTANCE.close();
        pathTimer = new Timer();
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }
    @Override
    public void onStartButtonPressed() {
        sigmaAuto().invoke();

        telemetry.addData("Path State", pathState);
        telemetry.addData("Position", follower.getPose().toString());
        telemetry.update();
    }


}