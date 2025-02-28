package sigmaCode.oldStuff.sigmaSubsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class RVWrist extends Subsystem {
    public static final RVWrist INSTANCE = new RVWrist();
    public Servo rvWrist;
    private RVWrist() { }
    public Command wristBack() {
        return new ServoToPosition(rvWrist, 0, this);
    }
    public Command wristForward() {
        return new ServoToPosition(rvWrist, 0.66, this);
    }
    @Override
    public void initialize() {
        rvWrist = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "rvWrist");
    }
}