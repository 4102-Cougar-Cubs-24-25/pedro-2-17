package sigmaCode.currentStuff;

import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.MIDDLE;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.pedropathing.util.Constants;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.feinCommands.FollowPathCommand;
import sigmaCode.currentStuff.feinCommands.Izzy;
import sigmaCode.currentStuff.feinCommands.LiftClawCommand;
import sigmaCode.currentStuff.feinCommands.LiftCommand;
import sigmaCode.currentStuff.freakySubsystems.Lift;
import sigmaCode.currentStuff.freakySubsystems.Lift.liftState;
import sigmaCode.currentStuff.freakySubsystems.LiftClaw;


@Autonomous(name = "sig")
public class SigmaRamenAuton extends LinearOpMode {
    private ElapsedTime timer;
    private final Pose startPose = new Pose(7.2, 63, Math.toRadians(0));
    Izzy izzy;
    private PathChain line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13;
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
    public void runOpMode() {
        timer = new ElapsedTime();
        timer.reset();
        CommandScheduler.getInstance().reset();
        Constants.setConstants(FConstants.class, LConstants.class);
        izzy = new Izzy(hardwareMap, startPose);
        //buildPaths();
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                //new FollowPathCommand(izzy.follower, line1),
                                new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.OPEN),
                                new LiftCommand(izzy.lift, Lift.liftState.MIDDLE),
                                new WaitCommand(3000)
                                ),
                        new LiftCommand(izzy.lift, Lift.liftState.UP),
                        new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.CLOSE),
                        new LiftCommand(izzy.lift, Lift.liftState.DOWN),
                        new WaitCommand(1000)
                        //new FollowPathCommand(izzy.follower, line2)
                )
        );
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            izzy.run();
        }
        CommandScheduler.getInstance().reset();
    }
}
