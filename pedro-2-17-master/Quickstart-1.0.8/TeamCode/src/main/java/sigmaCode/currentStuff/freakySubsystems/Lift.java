package sigmaCode.currentStuff.freakySubsystems;

import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.DOWN;
import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.MIDDLE;
import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.UP;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Lift extends SubsystemBase {
    private DcMotorEx vSlide;
    private liftState ls;
    private PIDController controller;
    private int target;
    private double p = .06, i = 0, d = .0002;
    private static double f = 0.03;
    private final double ticks_in_degrees = 700 / 180.0;
    public enum liftState{
        UP, MIDDLE, DOWN;
    }
    public Lift(DcMotorEx vSlide){
        this.vSlide = vSlide;
        controller = new PIDController(p, i, d);
        controller.setTolerance(10);
    }
    public void setTarget(liftState state){
        ls = state;
        switch (ls){
            case UP:
                target = 1500;
                break;
            case MIDDLE:
                target = 1200;
                break;
            case DOWN:
                target = 0;
                break;
        }
    }
    public boolean busy(){
        return vSlide.isBusy();
    }
    public void periodic(){
        controller.setPID(p, i, d);
        int pos = vSlide.getCurrentPosition();
        double pid = controller.calculate(pos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degrees)) * f;
        double power = pid + ff;
        vSlide.setPower(power);
    }
}
