package sigmaCode.oldStuff;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class AuraliciousActions {
    private DcMotorEx vSlide;
    private Servo rhWrist, lhWrist, rvWrist, lvWrist, vClaw;
    private Telemetry telemetry;
    public AuraliciousActions(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        vSlide = hardwareMap.get(DcMotorEx.class, "vSlide");
        vSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lhWrist = hardwareMap.get(Servo.class, "lhWrist");
        rhWrist = hardwareMap.get(Servo.class, "rhWrist");
        rvWrist = hardwareMap.get(Servo.class, "rvWrist");
        lvWrist = hardwareMap.get(Servo.class, "lvWrist");
        vClaw = hardwareMap.get(Servo.class, "vClaw");
    }

    public void slidesUpHalf(){
        vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vSlide.setTargetPosition(1200);
        vSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        vSlide.setPower(-1);
        while(vSlide.getCurrentPosition() < 1200){
            telemetry.addData("slides",vSlide.getCurrentPosition());
            telemetry.update();
        }
        vSlide.setPower(0);
        vSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void slidesUp(){
        vSlide.setTargetPosition(1500);
        vSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        vSlide.setPower(-1);
        while(vSlide.getCurrentPosition() < 1500){
            telemetry.addData("slides",vSlide.getCurrentPosition());
            telemetry.update();
        }
        vSlide.setPower(0);
        vSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void slidesDown(){
        vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vSlide.setTargetPosition(-1500);
        vSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        vSlide.setPower(1);
        while(vSlide.getCurrentPosition() > -1500){
            telemetry.addData("slides",vSlide.getCurrentPosition());
            telemetry.update();
        }
        vSlide.setPower(0);
        vSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void wristUp(){
        rhWrist.setPosition(.35);
        lhWrist.setPosition(0);
    }

    public void wristBack(){
        rvWrist.setPosition(.66);
        lvWrist.setPosition(0);
    }

    public void wristForward(){
        rvWrist.setPosition(0);
        lvWrist.setPosition(.66);
    }

    public void closeClaw(){
        vClaw.setPosition(0.8);
    }

    public void openClaw(){
        vClaw.setPosition(0);
    }
}