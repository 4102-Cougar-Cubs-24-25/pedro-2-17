package sigmaCode.oldStuff.sigmaSubsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class LVWrist extends Subsystem {
    public static final LVWrist INSTANCE = new LVWrist();
    public Servo lvWrist;
    private LVWrist() { }
    public Command wristBack() {
        return new ServoToPosition(lvWrist, 0, this);
    }
    public Command wristForward() {
        return new ServoToPosition(lvWrist, 0.66, this);
    }
    @Override
    public void initialize() {
        lvWrist = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "lvWrist");
    }

}
