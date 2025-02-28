package sigmaCode.currentStuff.freakySubsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LiftClaw extends SubsystemBase {
    private Servo vClaw;
    private clawState cs;
    public enum clawState{
        OPEN, CLOSE;
    }
    public LiftClaw(Servo vClaw){
        this.vClaw = vClaw;
    }
    public void go(clawState state){
        cs = state;
        switch(cs){
            case OPEN:
                vClaw.setPosition(0);
                break;
            case CLOSE:
                vClaw.setPosition(0.8);
                break;
        }
    }
}
