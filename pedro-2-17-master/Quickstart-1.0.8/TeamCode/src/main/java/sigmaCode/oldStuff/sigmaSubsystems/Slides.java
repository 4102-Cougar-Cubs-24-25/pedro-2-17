package sigmaCode.oldStuff.sigmaSubsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.Feedforward;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

public class Slides extends Subsystem {
    public static final Slides INSTANCE = new Slides();
    public MotorEx lift;
    Feedforward kf = new StaticFeedforward(.03);
    public PIDFController controller = new PIDFController(0.0623, 0.0, 0.0001, kf, 30);
    //keirs values .01, .001
    private Slides() { }
    public Command down(){
        return new RunToPosition(lift, 0, controller, this);
    }
    public Command up(){
        return new RunToPosition(lift, 1600, controller, this);
    }
    public Command half(){
        return new RunToPosition(lift, 1400, controller, this);
    }
    public Command setPower(){
        return new SetPower(lift, 1);
    }
    @Override
    public void initialize(){
        lift = new MotorEx("vSlide");
    }
    /*
    @NonNull
    @Override
    public Command getDefaultCommand(){
        return new HoldPosition(lift, controller, this);
    }*/
}
