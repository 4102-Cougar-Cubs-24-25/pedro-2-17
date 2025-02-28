package sigmaCode.currentStuff.feinCommands;
import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;

public class FollowPathCommand extends CommandBase {
    private final Follower follower;
    private final PathChain path;
    private boolean holdEnd = true;
    public FollowPathCommand(Follower follower, PathChain path) {
        this.follower = follower;
        this.path = path;
    }
    public FollowPathCommand(Follower follower, Path path) {
        this(follower, new PathChain(path));
    }
    public FollowPathCommand setHoldEnd(boolean holdEnd) {
        this.holdEnd = holdEnd;
        return this;
    }
    @Override
    public void initialize() {
        follower.followPath(path, holdEnd);
    }
    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }
}