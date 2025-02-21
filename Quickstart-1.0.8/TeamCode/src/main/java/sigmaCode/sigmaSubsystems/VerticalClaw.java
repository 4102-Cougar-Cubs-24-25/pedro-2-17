package sigmaCode.sigmaSubsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
public class VerticalClaw extends Subsystem{
    public static final VerticalClaw INSTANCE = new VerticalClaw();
    public Servo claw;
    private VerticalClaw() { }
    public Command open() {
        return new ServoToPosition(claw, 0, this);
    }
    public Command close() {
        return new ServoToPosition(claw, 0.8, this);
    }
    public void initialize() {
        claw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "vClaw");
    }

}
