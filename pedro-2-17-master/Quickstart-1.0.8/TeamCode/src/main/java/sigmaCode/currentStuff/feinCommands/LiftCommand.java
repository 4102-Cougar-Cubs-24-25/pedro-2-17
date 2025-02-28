package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.Lift;

public class LiftCommand extends InstantCommand {
    private Lift lift;
    private Lift.liftState state;
    public LiftCommand(Lift lift, Lift.liftState state){
        this.lift = lift;
        this.state = state;
        addRequirements(lift);
    }
    public void execute(){
        lift.setTarget(state);
    }
}
