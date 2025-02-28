package sigmaCode.oldStuff.oldAutons;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.pedro.FollowPath;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.GeneratedPaths;
import sigmaCode.oldStuff.sigmaSubsystems.LVWrist;
import sigmaCode.oldStuff.sigmaSubsystems.RVWrist;
import sigmaCode.oldStuff.sigmaSubsystems.Slides;
import sigmaCode.oldStuff.sigmaSubsystems.VerticalClaw;

@Autonomous(name = "2 pedro generated auto", group = "1")
public class autonPath extends PedroOpMode {
    public autonPath() {
        super(Slides.INSTANCE, VerticalClaw.INSTANCE, LVWrist.INSTANCE, RVWrist.INSTANCE);
    }
    private final Pose startPose = new Pose(7.2, 63, Math.toRadians(0));
    //todo: change above value to desired starting pose
    private PathChain line1;//, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13;
    //todo: add as many lines as are used in the path
    //todo: go to GeneratedPaths file
    public void buildPaths() {
        line1 = GeneratedPaths.line1;
        /*
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
        line13 = GeneratedPaths.line13;*/
    }
    public Command auton(){
        return new SequentialGroup(
                new ParallelGroup(
                        new SequentialGroup(
                                new Delay(.8),
                                new FollowPath(line1)
                        ),
                        Slides.INSTANCE.half()
                ),
                new Delay(2),
                Slides.INSTANCE.up(),
                new ParallelGroup(
                        Slides.INSTANCE.down(),
                        //new FollowPathCommand(line2),
                        VerticalClaw.INSTANCE.open(),
                        LVWrist.INSTANCE.wristBack(),
                        RVWrist.INSTANCE.wristBack()
                )
                //new FollowPathCommand(line3)
        );
    }

    public void onUpdate(){
        telemetry.addData("slide pos", Slides.INSTANCE.lift.getCurrentPosition());
        telemetry.addData("target", Slides.INSTANCE.controller.getTarget());
        telemetry.update();
    }

    public Command liftTest(){
        return new SequentialGroup(
                Slides.INSTANCE.half(),
                Slides.INSTANCE.up()
        );
    }

    @Override
    public void onInit() {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        VerticalClaw.INSTANCE.close().invoke();
        buildPaths();
    }
    @Override
    public void onStartButtonPressed() {
        auton().invoke();
    }
}