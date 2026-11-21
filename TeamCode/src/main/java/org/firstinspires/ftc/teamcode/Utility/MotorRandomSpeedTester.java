package org.firstinspires.ftc.teamcode.Utility;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.Random;

@Utility(name = "Ten Minute Test")
public class MotorRandomSpeedTester extends OpMode {

    private DcMotor motor;
    private Random random;
    private VoltageSensor voltageSensor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        random = new Random((long) voltageSensor.getVoltage());
        resetRuntime();
    }

    @Override
    public void loop() {
        sleep(1000);
        motor.setPower(random.nextDouble() * 2 - 1);
        if (getRuntime() >= 600) {
            requestOpModeStop();
        }
        telemetry.addData("Current Time", getRuntime());
        telemetry.addData("Remaining Time", 600 - getRuntime());
        telemetry.addData("Percent Completion", Double.toString(getRuntime() / 6) + "%");
        telemetry.addData("Current Power", motor.getPower());
    }
}
