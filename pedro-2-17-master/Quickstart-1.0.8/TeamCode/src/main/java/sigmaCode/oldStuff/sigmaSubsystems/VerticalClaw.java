package sigmaCode.oldStuff.sigmaSubsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

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
    @Override
    public void initialize() {
        claw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "vClaw");
    }

}
