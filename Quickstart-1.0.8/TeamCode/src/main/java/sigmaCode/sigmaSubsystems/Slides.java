package sigmaCode.sigmaSubsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
public class Slides extends Subsystem {
    public static final Slides INSTANCE = new Slides();
    public MotorEx lift;
    public PIDFController controller = new PIDFController(new PIDCoefficients(0.005, 0.0, 0.0));
    private Slides() { }
    public Command down(){
        return new RunToPosition(lift, -1500, controller, this);
    }
    public Command up(){
        return new RunToPosition(lift, 1500, controller, this);
    }
    public Command half(){
        return new RunToPosition(lift, 1200, controller, this);
    }
    @Override
    public void initialize(){
        lift = new MotorEx("vSlide");
    }
}
