package org.firstinspires.ftc.teamcode.Core;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Interfaces.Launcher;

@Configurable
public class LauncherTester implements Launcher {
    private Robot robot;
    private final int TICKS_PER_ROTATION = 28;

//    private final ezPID front;
//    private final ezPID back;
    public LauncherTester(Robot robot) {
        this.robot = robot;
//        front = new ezPID(robot.frontLauncherWheels, TICKS_PER_ROTATION, p, i, d, f, kneecapIN, tolerance, ezPID.movementType.SPEED);
//        back = new ezPID(robot.backLauncherWheels, TICKS_PER_ROTATION, p, i, d, f, kneecapIN, tolerance, ezPID.movementType.SPEED);
    }
    public static double frontLauncherSpeed = 0, backLauncherSpeed = 0, p = 500, i = 0, d = 0, f = 20, kneecapIN = 0, tolerance = 0;
    public static boolean isFiring;
    public static double launcherServoPosition = 0.5;
    public static double frontHoodPosition = 0.9;
    public static double backHoodPosition = 0.9;
    public static double flipperPosition = 1;
    @Override
    public void fire(FireMode mode) {

    }
    @Override
    public boolean isFiring() {
        return isFiring;
    }
    @Override
    public void update() {
        robot.frontLauncherWheels.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(p, i, d, f));
        robot.backLauncherWheels.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(p, i, d, f));

        robot.frontLauncherWheels.setVelocity(frontLauncherSpeed);
        robot.backLauncherWheels.setVelocity(backLauncherSpeed);

        robot.leftLauncherServo.setPosition(launcherServoPosition);
        robot.rightLauncherServo.setPosition(1 - launcherServoPosition);

        robot.frontHoodServo.setPosition(frontHoodPosition);
        robot.backHoodServo.setPosition(backHoodPosition);

        robot.flipperLift.setPosition(flipperPosition);

        Robot.panelsTelemetry.addData("Front Velocity", robot.frontLauncherWheels.getVelocity());
        Robot.panelsTelemetry.addData("Front Target", frontLauncherSpeed);

        Robot.panelsTelemetry.addData("Back Velocity", robot.backLauncherWheels.getVelocity());
        Robot.panelsTelemetry.addData("Back Target", backLauncherSpeed);
    }

    @Override
    public void addTelemetry() {

    }
}
