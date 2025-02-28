package sigmaCode.currentStuff.freakySubsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LiftWrist extends SubsystemBase {
    private Servo rvWrist, lvWrist;
    private wristState ws;
    public enum wristState{
        FORWARD, BACK;
    }
    public LiftWrist(Servo rvWrist, Servo lvWrist){
        this.rvWrist = rvWrist;
        this.lvWrist = lvWrist;
    }
    public void go(wristState state){
        ws = state;
        switch(ws){
            case BACK:
                rvWrist.setPosition(.66);
                lvWrist.setPosition(0);
                break;
            case FORWARD:
                rvWrist.setPosition(0);
                lvWrist.setPosition(.66);
                break;
        }
    }
}
