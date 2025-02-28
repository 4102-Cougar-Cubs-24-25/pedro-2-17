package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class PIDF extends OpMode {
    private PIDController controller;
    public static double p = 0, i = 0, d = 0;
    public static double f = 0;
    public static int target = 0;
    private final double ticks_in_degrees = 700 / 180.0;
    private DcMotorEx vSlide;

    @Override
    public void init() {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        vSlide = hardwareMap.get(DcMotorEx.class,"vSlide");
    }

    @Override
    public void loop() {
        controller.setPID(p, i, d);
        int pos = vSlide.getCurrentPosition();
        double pid = controller.calculate(pos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degrees)) * f;
        double power = pid + ff;
        vSlide.setPower(power);
        telemetry.addData("pos", pos);
        telemetry.addData("target", target);
        telemetry.update();
    }
}
