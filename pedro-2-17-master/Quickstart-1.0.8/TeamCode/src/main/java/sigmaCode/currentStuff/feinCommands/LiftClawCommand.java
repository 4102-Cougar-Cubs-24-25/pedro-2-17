package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import sigmaCode.currentStuff.freakySubsystems.LiftClaw;

public class LiftClawCommand extends CommandBase {
    private LiftClaw liftClaw;
    private LiftClaw.clawState state;
    public LiftClawCommand(LiftClaw liftClaw, LiftClaw.clawState state){
        this.liftClaw = liftClaw;
        this.state = state;
        addRequirements(liftClaw);
        liftClaw.go(LiftClaw.clawState.CLOSE);
    }
    public void execute(){
        liftClaw.go(state);
    }
    public boolean isFinished(){
        return true;
    }
}
